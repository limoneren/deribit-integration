package com.limoneren.deribit.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserAccountSummary {
    private String clientId;
    private String currency;
    @EqualsAndHashCode.Exclude
    private BigDecimal balance;
    @EqualsAndHashCode.Exclude
    private BigDecimal reservedFunds;

    @EqualsAndHashCode.Include
    private Object balanceForEqHC() {
        return balance.stripTrailingZeros();
    }

    @EqualsAndHashCode.Include
    private Object reservedFundsForEqHC() {
        return reservedFunds.stripTrailingZeros();
    }
}
