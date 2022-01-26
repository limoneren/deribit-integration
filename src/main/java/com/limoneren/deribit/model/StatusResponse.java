package com.limoneren.deribit.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse extends BaseJsonRpcResponse {
    private Status result;
}
