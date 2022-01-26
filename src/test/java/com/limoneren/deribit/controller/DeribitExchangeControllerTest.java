package com.limoneren.deribit.controller;

import com.limoneren.deribit.model.UserAccountSummary;
import com.limoneren.deribit.service.DeribitExchangeService;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MicronautTest
public class DeribitExchangeControllerTest {
    @Inject
    DeribitExchangeController deribitExchangeController;

    @Inject
    DeribitExchangeService deribitExchangeService;

    @MockBean(DeribitExchangeService.class)
    DeribitExchangeService deribitExchangeService() {
        return mock(DeribitExchangeService.class);
    }

    private static final String CLIENT_ID = "abc";
    private static final String CLIENT_SECRET = "abc";

    @Test
    void testGetAccountSummary_Success() {
        List<UserAccountSummary> userAccountSummaries = getAccountSummariesTestData();
        Mockito.when(deribitExchangeService.getAccountSummary(Mockito.any(), Mockito.any())).thenReturn(userAccountSummaries);
        List<UserAccountSummary> returnedAccountSummaries = deribitExchangeController.getAccountSummary(CLIENT_ID, CLIENT_SECRET);
        verify(deribitExchangeService, times(1)).getAccountSummary(CLIENT_ID, CLIENT_SECRET);
        assertEquals("BTC", returnedAccountSummaries.get(0).getCurrency());
        assertEquals(BigDecimal.valueOf(2.0), returnedAccountSummaries.get(0).getBalance());
        assertEquals(BigDecimal.valueOf(0.15), returnedAccountSummaries.get(0).getReservedFunds());
    }

    List<UserAccountSummary> getAccountSummariesTestData() {
        UserAccountSummary btcBalance = UserAccountSummary.builder()
                .clientId(CLIENT_ID)
                .currency("BTC")
                .balance(BigDecimal.valueOf(2.0))
                .reservedFunds(BigDecimal.valueOf(0.15))
                .build();
        return Stream.of(btcBalance).collect(Collectors.toList());
    }

}
