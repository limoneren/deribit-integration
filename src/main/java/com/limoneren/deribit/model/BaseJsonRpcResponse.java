package com.limoneren.deribit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseJsonRpcResponse {
    private String jsonrpc;
    private String id;
    private Long usIn;
    private Long usOut;
    private Long usDiff;
    private boolean testnet;
}
