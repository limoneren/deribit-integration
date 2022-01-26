package com.limoneren.deribit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorContent {
    private String message;
    private ErrorData data;
    private int code;
}
