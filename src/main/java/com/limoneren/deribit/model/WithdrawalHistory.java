package com.limoneren.deribit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalHistory {
    @JsonProperty("updated_timestamp")
    private Timestamp updatedTimestamp;
    @JsonProperty("created_timestamp")
    private Timestamp createdTimestamp;
    @JsonProperty("confirmed_timestamp")
    private Timestamp confirmedTimestamp;
    private String state;
    private String currency;
    @JsonProperty("transaction_id")
    private String transactionId;
    private String address;
    private BigDecimal amount;
    private BigDecimal fee;
    private Long id;
    private double priority;
}
