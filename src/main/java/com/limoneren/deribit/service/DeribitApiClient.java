package com.limoneren.deribit.service;

import com.limoneren.deribit.model.*;
import com.limoneren.deribit.model.AccountSummaryResponse;
import com.limoneren.deribit.model.AvailableCurrenciesResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;

import java.math.BigDecimal;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.CONTENT_TYPE;

@BasicAuth
@Client("${deribit.api.url}")
public interface DeribitApiClient {
    @Get(uri = "${deribit.api.private.endpoints.get-account-summary}", consumes = MediaType.APPLICATION_JSON)
    @Header(name = CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
    @Header(name = ACCEPT, value = "*/*")
    AccountSummaryResponse getAccountSummary(@QueryValue("currency") String currency,
                                             @QueryValue("extended") boolean extended);

    @Get(uri = "${deribit.api.public.endpoints.status}")
    @Header(name = CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
    @Header(name = ACCEPT, value = "*/*")
    StatusResponse getStatus();

    @Get(uri = "${deribit.api.public.endpoints.get-currencies}")
    @Header(name = CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
    @Header(name = ACCEPT, value = "*/*")
    AvailableCurrenciesResponse getCurrencies();

    @Get(uri = "${deribit.api.private.endpoints.get-withdrawals}", consumes = MediaType.APPLICATION_JSON)
    @Header(name = CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
    @Header(name = ACCEPT, value = "*/*")
    WithdrawalHistoryResponse getWithdrawals(@QueryValue("currency") String currency,
                                             @QueryValue("count") int count,
                                             @QueryValue("offset") int offset);

    @Get(uri = "${deribit.api.private.endpoints.get-deposits}", consumes = MediaType.APPLICATION_JSON)
    @Header(name = CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
    @Header(name = ACCEPT, value = "*/*")
    DepositHistoryResponse getDeposits(@QueryValue("currency") String currency,
                                       @QueryValue("count") int count,
                                       @QueryValue("offset") int offset);

    @Get(uri = "${deribit.api.private.endpoints.withdraw}", consumes = MediaType.APPLICATION_JSON)
    @Header(name = CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
    @Header(name = ACCEPT, value = "*/*")
    WithdrawResponse withdraw(@QueryValue("currency") String currency,
                              @QueryValue("address") String address,
                              @QueryValue("amount") BigDecimal amount,
                              @QueryValue("priority") String priority);
}
