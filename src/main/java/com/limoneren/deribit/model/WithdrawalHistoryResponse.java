package com.limoneren.deribit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalHistoryResponse extends BaseJsonRpcResponse {
    private WithdrawalHistoryResult result;
}
