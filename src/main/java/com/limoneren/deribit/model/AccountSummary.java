package com.limoneren.deribit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSummary {
    @NonNull
    private BigDecimal balance;
    @NonNull
    private String currency;
    @JsonProperty("available_withdrawal_funds")
    @NonNull
    private BigDecimal availableWithdrawalFunds;
    @NonNull
    private BigDecimal reservedFunds;
}
