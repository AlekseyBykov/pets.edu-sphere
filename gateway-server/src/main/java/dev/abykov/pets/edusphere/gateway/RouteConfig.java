package dev.abykov.pets.edusphere.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining custom routes in Spring Cloud Gateway.
 * This class registers a RouteLocator bean with two routes:
 * - /edu/courses/** is routed to courses-service after removing the prefix.
 * - /edu/profiles/** is routed to profiles-service after removing the prefix.
 * Uses load balancing (lb://) via Eureka Discovery.
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "courses_route", r -> r.path("/edu/courses/**")
                                .filters(f -> f.rewritePath("/edu/courses/(?<segment>.*)", "/${segment}"))
                                .uri("lb://courses-service")
                )
                .route(
                        "profiles_route", r -> r.path("/edu/profiles/**")
                                .filters(f -> f.rewritePath("/edu/profiles/(?<segment>.*)", "/${segment}"))
                                .uri("lb://profiles-service")
                )
                .build();
    }
}
