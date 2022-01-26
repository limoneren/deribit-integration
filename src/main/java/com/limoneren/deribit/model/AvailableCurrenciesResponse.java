package com.limoneren.deribit.model;

import io.micronaut.core.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailableCurrenciesResponse extends BaseJsonRpcResponse {
    @NonNull
    private List<Currency> result;
}
