package com.example.Gateway.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/v1/accounts/**")
                        .filters(f -> f.hystrix(h -> h.setName("account-service")
                                .setFallbackUri("forward:/fallback/accountsFallback")))
                        .uri("lb://account-service")
                        .id("accountsModule"))
                .route(r -> r.path("/v1/customers/**")
                        .filters(f -> f.hystrix(h -> h.setName("customer-service")
                                .setFallbackUri("forward:/fallback/customersFallback")))
                        .uri("lb://accountHolder-service")
                        .id("customersModule"))
                .build();
    }

}
