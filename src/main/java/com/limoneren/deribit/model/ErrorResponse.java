package com.limoneren.deribit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse extends BaseJsonRpcResponse {
    private ErrorContent error;
}
