package com.limoneren.deribit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalHistoryResult {
    private List<WithdrawalHistory> data;
    private int count;
}
