package com.backend.gateway;

import com.backend.gateway.filters.ErrorFilter;
import com.backend.gateway.filters.PostFilter;
import com.backend.gateway.filters.PreFilter;
import com.backend.gateway.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public PostFilter postFilter() { return new PostFilter(); }

    @Bean
    public PreFilter preFilter() { return new PreFilter(); }

    @Bean
    public RouteFilter routeFilter() { return new RouteFilter(); }
}
