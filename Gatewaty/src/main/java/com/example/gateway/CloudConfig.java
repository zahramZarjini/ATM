package com.example.gateway;

import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class CloudConfig {
    @Bean
    public RouteLocatorBuilder routeLocatorBuilder(RouteLocatorBuilder routeBuilder){
        routeBuilder.routes().route(r -> r.path("/account/").uri("/lb://Account"));
        routeBuilder.routes().route(r->r.path("/customer/").uri("/lb://Customer"));
        return routeBuilder;
    }

}
