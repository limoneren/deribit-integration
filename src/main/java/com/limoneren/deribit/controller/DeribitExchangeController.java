package com.limoneren.deribit.controller;

import com.limoneren.deribit.model.*;
import com.limoneren.deribit.model.AvailableCurrenciesResponse;
import com.limoneren.deribit.service.DeribitExchangeService;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

@Controller("/deribit")
public class DeribitExchangeController {

    private static final Logger LOG = LoggerFactory.getLogger(DeribitExchangeController.class);

    @Inject
    DeribitExchangeService deribitExchangeService;

    @Get("/account-summary")
    public List<UserAccountSummary> getAccountSummary(@QueryValue String clientId, @Header String clientSecret) {
        return deribitExchangeService.getAccountSummary(clientId, clientSecret);
    }

    @Get("/status")
    public StatusResponse getStatus() {
        return deribitExchangeService.getStatus();
    }

    @Get("/currencies")
    public AvailableCurrenciesResponse getCurrencies() {
        return deribitExchangeService.getCurrencies();
    }

    @Get("/deposits")
    public DepositHistoryResponse getDeposits(@QueryValue String clientId, @Header String clientSecret,
                                              @QueryValue String currency, @QueryValue int count,
                                              @QueryValue int offset) {
        return deribitExchangeService.getDeposits(clientId, clientSecret, currency, count, offset);
    }

    @Get("/withdrawals")
    public WithdrawalHistoryResponse getWithdrawals(@QueryValue String clientId, @Header String clientSecret,
                                                    @QueryValue String currency, @QueryValue int count,
                                                    @QueryValue int offset) {
        return deribitExchangeService.getWithdrawals(clientId, clientSecret, currency, count, offset);
    }

    @Get("/withdraw")
    public WithdrawResponse withdraw(@QueryValue String clientId, @Header String clientSecret,
                                                    @QueryValue String currency, @QueryValue String address,
                                                    @QueryValue BigDecimal amount, @QueryValue String priority) {
        return deribitExchangeService.withdraw(clientId, clientSecret, currency, address, amount, priority);
    }

    @Error
    public ErrorResponse error(HttpClientResponseException ex) {
        LOG.error("Internal server error: {}", ex.getMessage());
        return ex.getResponse().getBody(ErrorResponse.class).orElse(new ErrorResponse());
    }
}
