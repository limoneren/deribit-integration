package com.limoneren.deribit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {
    private String reason;
    private String param;
}
