package com.wstutorial.apigateway.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * https://spring.io/blog/2019/06/18/getting-started-with-spring-cloud-gateway
 */
@SpringBootApplication
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("simple_route", r -> r
                        .path("/get") // intercept calls to the /get path
						.uri("http://httpbin.org"))// forward to httpbin
                .route("external_route", r -> r
                        .path("/external-api/**")
                        .filters(f -> f.stripPrefix(1)) // external-api should disappear from url
                        .uri("http://httpbin.org"))// forward to httpbin
                .route("host_route", r -> r
                        .host("*.apig.org")
                        .uri("http://httpbin.org"))// forward to httpbin
                .build();
    }
}
