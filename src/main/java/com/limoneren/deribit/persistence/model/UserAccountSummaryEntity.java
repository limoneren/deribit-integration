package com.limoneren.deribit.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_account_summary", schema = "deribit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="db_id")
    private Long dbId;

    @Column(name="client_id")
    private String clientId;
    private String currency;
    private BigDecimal balance;
    @Column(name="reserved_funds")
    private BigDecimal reservedFunds;
}
