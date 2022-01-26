package com.limoneren.deribit.service;

import com.limoneren.deribit.model.*;
import com.limoneren.deribit.model.AccountSummaryResponse;
import com.limoneren.deribit.model.AvailableCurrenciesResponse;
import com.limoneren.deribit.model.Currency;
import com.limoneren.deribit.persistence.jpa.UserAccountSummaryRepository;
import com.limoneren.deribit.persistence.model.UserAccountSummaryEntity;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class DeribitExchangeService {

    private static final Logger LOG = LoggerFactory.getLogger(DeribitExchangeService.class);

    @Inject
    DeribitApiClient deribitApiClient;

    @Inject
    DeribitFilter deribitFilter;

    @Inject
    UserAccountSummaryRepository userAccountSummaryRepository;

    public List<UserAccountSummary> getAccountSummary(String clientId, String clientSecret) {
        deribitFilter.setClientIdAndSecret(clientId, clientSecret);

        List<Currency> availableCurrencies = getCurrencies().getResult();
        List<UserAccountSummary> userAccountSummariesFromApi = getUsersAccountSummariesForAllCurrencies(availableCurrencies, clientId);

        List<UserAccountSummaryEntity> userAccountSummaryEntities = userAccountSummaryRepository.findByClientId(clientId);
        List<UserAccountSummary> userAccountSummariesFromDb = userAccountSummaryEntities.stream().map(
                userAccountSummaryEntity -> UserAccountSummary.builder()
                        .clientId(userAccountSummaryEntity.getClientId())
                        .currency(userAccountSummaryEntity.getCurrency())
                        .balance(userAccountSummaryEntity.getBalance())
                        .reservedFunds(userAccountSummaryEntity.getReservedFunds()).build()).collect(Collectors.toList());
        LOG.info("[getUsersAccountSummariesFromDb] User's account summaries from db: {}", userAccountSummariesFromDb);

        List<UserAccountSummary> deltaToAdd = getDeltaToAdd(userAccountSummariesFromApi, userAccountSummariesFromDb);
        List<UserAccountSummary> deltaToRemove = getDeltaToRemove(userAccountSummariesFromApi, userAccountSummariesFromDb);

        filterInitiallyFetchedEntitiesAndRemoveTheDelta(userAccountSummaryEntities, deltaToRemove);
        addNewEntriesToTheDb(deltaToAdd);
        return userAccountSummariesFromApi;
    }

    private List<UserAccountSummary> getUsersAccountSummariesForAllCurrencies(List<Currency> availableCurrencies, String clientId) {
        List<UserAccountSummary> userAccountSummariesFromApi = new ArrayList<>();
        availableCurrencies.forEach(currency -> {
            AccountSummaryResponse accountSummaryResponse = deribitApiClient.getAccountSummary(currency.getCurrency(), true);
            userAccountSummariesFromApi.add(UserAccountSummary.builder()
                    .clientId(clientId)
                    .currency(currency.getCurrency())
                    .balance(accountSummaryResponse.getResult().getBalance())
                    .reservedFunds(accountSummaryResponse.getResult().getBalance()
                            .subtract(accountSummaryResponse.getResult().getAvailableWithdrawalFunds()))
                    .build());
        });
        LOG.info("[getUsersAccountSummariesForAllCurrencies] User's account summaries from api: {}", userAccountSummariesFromApi);
        return userAccountSummariesFromApi;
    }

    private List<UserAccountSummary> getDeltaToAdd(List<UserAccountSummary> userAccountSummariesFromApi,
                                                   List<UserAccountSummary> userAccountSummariesFromDb) {
        List<UserAccountSummary> deltaToAdd = userAccountSummariesFromApi.stream()
                .filter(element -> !userAccountSummariesFromDb.contains(element))
                .collect(Collectors.toList());
        LOG.info("[getDeltaToAdd] records to add to db: {}: ",deltaToAdd);
        return deltaToAdd;
    }

    private List<UserAccountSummary> getDeltaToRemove(List<UserAccountSummary> userAccountSummariesFromApi,
                                                   List<UserAccountSummary> userAccountSummariesFromDb) {
        List<UserAccountSummary> deltaToRemove = userAccountSummariesFromDb.stream()
                .filter(element -> !userAccountSummariesFromApi.contains(element))
                .collect(Collectors.toList());
        LOG.info("[getDeltaToRemove] records to remove from db: {}: ",deltaToRemove);
        return deltaToRemove;
    }

    private void filterInitiallyFetchedEntitiesAndRemoveTheDelta(List<UserAccountSummaryEntity> userAccountSummaryEntities,
                                                                 List<UserAccountSummary> deltaToRemove){
        userAccountSummaryEntities = userAccountSummaryEntities.stream().filter(userAccountSummaryEntity ->
                deltaToRemove.stream().map(UserAccountSummary::getCurrency).collect(Collectors.toSet()).contains(userAccountSummaryEntity.getCurrency())
        ).collect(Collectors.toList());
        LOG.info("[filterInitiallyFetchedEntitiesAndRemoveTheDelta] Removing the delta: {}: ",userAccountSummaryEntities);
        userAccountSummaryRepository.deleteAll(userAccountSummaryEntities);
    }

    private void addNewEntriesToTheDb(List<UserAccountSummary> deltaToAdd) {
        List<UserAccountSummaryEntity> entitiesToAdd = deltaToAdd.stream().map(userAccountSummary ->{
            return UserAccountSummaryEntity.builder()
                    .clientId(userAccountSummary.getClientId())
                    .currency(userAccountSummary.getCurrency())
                    .balance(userAccountSummary.getBalance())
                    .reservedFunds(userAccountSummary.getReservedFunds()).build();
        } ).collect(Collectors.toList());
        LOG.info("[addNewEntriesToTheDb] Adding the delta: {}: ",entitiesToAdd);
        userAccountSummaryRepository.saveAll(entitiesToAdd);
    }

    public StatusResponse getStatus() {
        return deribitApiClient.getStatus();
    }

    public AvailableCurrenciesResponse getCurrencies() {
        return deribitApiClient.getCurrencies();
    }

    public DepositHistoryResponse getDeposits(String clientId, String clientSecret, String currency, int count, int offset) {
        deribitFilter.setClientIdAndSecret(clientId, clientSecret);
        return deribitApiClient.getDeposits(currency, count, offset);
    }

    public WithdrawalHistoryResponse getWithdrawals(String clientId, String clientSecret, String currency, int count, int offset) {
        deribitFilter.setClientIdAndSecret(clientId, clientSecret);
        return deribitApiClient.getWithdrawals(currency, count, offset);
    }

    public WithdrawResponse withdraw(String clientId, String clientSecret, String currency,String address,
                                     BigDecimal amount, String priority){
        deribitFilter.setClientIdAndSecret(clientId, clientSecret);
        return deribitApiClient.withdraw(currency, address, amount, priority);
    }
}
