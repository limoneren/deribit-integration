package com.limoneren.deribit.model;

import io.micronaut.core.annotation.NonNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountSummaryResponse extends BaseJsonRpcResponse {
    @NonNull
    private AccountSummary result;
}
