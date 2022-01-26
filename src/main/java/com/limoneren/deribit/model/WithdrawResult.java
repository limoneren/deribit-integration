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
public class WithdrawResult {
    private String address;
    private BigDecimal amount;
    @JsonProperty("confirmed_timestamp")
    private Timestamp confirmedTimestamp;
    @JsonProperty("created_timestamp")
    private Timestamp createdTimestamp;
    private String currency;
    private BigDecimal fee;
    private Long id;
    private int priority;
    private String state;
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("updated_timestamp")
    private Timestamp updatedTimestamp;
}
