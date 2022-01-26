package com.limoneren.deribit.model;

import io.micronaut.core.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @NonNull
    private String currency;
}
