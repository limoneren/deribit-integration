package com.limoneren.deribit.service;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import jakarta.inject.Singleton;
import lombok.Setter;
import org.reactivestreams.Publisher;

@BasicAuth
@Singleton
@Setter
public class DeribitFilter implements HttpClientFilter {

    private String clientId;
    private String clientSecret;

    public void setClientIdAndSecret(String clientId, String clientSecret) {
        setClientId(clientId);
        setClientSecret(clientSecret);
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        return chain.proceed(
                request.basicAuth(clientId, clientSecret)
        );
    }
}
