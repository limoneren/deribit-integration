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
public class DepositHistory {
    @JsonProperty("updated_timestamp")
    private Timestamp updatedTimestamp;
    @JsonProperty("received_timestamp")
    private Timestamp receivedTimestamp;
    private String state;
    private String currency;
    @JsonProperty("transaction_id")
    private String transactionId;
    private String address;
    private BigDecimal amount;
}
