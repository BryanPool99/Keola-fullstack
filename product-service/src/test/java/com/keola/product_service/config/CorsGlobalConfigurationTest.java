package com.keola.product_service.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.adapter.DefaultServerWebExchange;
import org.springframework.web.server.handler.DefaultWebFilterChain;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CorsGlobalConfigurationTest {

    @Mock
    DefaultWebFilterChain filterChain;

    @InjectMocks
    CorsGlobalConfiguration corsGlobalConfiguration;


    @Test
    @DisplayName("Return void when complete task and is options")
    void ReturnVoidWhenCompleteTaskAndIsOptions() {
        MockServerHttpRequest request = MockServerHttpRequest.options("https://example.com/")
                .header(HttpHeaders.ORIGIN, "http://example.com/")
                .build();
        MockServerHttpResponse response = new MockServerHttpResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, PUT, POST, DELETE, OPTIONS, PATCH");
        response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
        response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "UNICA-User, Content-Type");
        ServerCodecConfigurer codecConfigure = ServerCodecConfigurer.create();
        WebSessionManager sessionManager = exchange -> Mono.empty();
        AcceptHeaderLocaleContextResolver localeContextResolver = new AcceptHeaderLocaleContextResolver();
        ServerWebExchange exchange = new DefaultServerWebExchange(request, response, sessionManager, codecConfigure, localeContextResolver);

        StepVerifier.create(corsGlobalConfiguration.corsFilter().filter(exchange, filterChain))
                .verifyComplete();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("*", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
        assertEquals("GET, PUT, POST, DELETE, OPTIONS, PATCH", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS));
        assertEquals("3600", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_MAX_AGE));
        assertEquals("UNICA-User, Content-Type", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS));
    }

    @Test
    @DisplayName("Return void when complete task and is get")
    void ReturnVoidWhenCompleteTaskAndIsGet() {
        MockServerHttpRequest request = MockServerHttpRequest.get("https://example.com/")
                .header(HttpHeaders.ORIGIN, "http://example.com/")
                .build();
        MockServerHttpResponse response = new MockServerHttpResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, PUT, POST, DELETE, OPTIONS, PATCH");
        response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
        response.getHeaders().add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "UNICA-User, Content-Type");
        ServerCodecConfigurer codecConfigure = ServerCodecConfigurer.create();
        WebSessionManager sessionManager = exchange -> Mono.empty();
        AcceptHeaderLocaleContextResolver localeContextResolver = new AcceptHeaderLocaleContextResolver();
        ServerWebExchange exchange = new DefaultServerWebExchange(request, response, sessionManager, codecConfigure, localeContextResolver);

        when(filterChain.filter(exchange)).thenReturn(Mono.empty());

        StepVerifier.create(corsGlobalConfiguration.corsFilter().filter(exchange, filterChain))
                .verifyComplete();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("*", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
        assertEquals("GET, PUT, POST, DELETE, OPTIONS, PATCH", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS));
        assertEquals("3600", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_MAX_AGE));
        assertEquals("UNICA-User, Content-Type", response.getHeaders().getFirst(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS));
    }


    @Test
    @DisplayName("Return void when non cors request")
    void ReturnVoidWhenNonCorsRequest() {
        MockServerHttpRequest request = MockServerHttpRequest.get("/").build();
        MockServerHttpResponse response = new MockServerHttpResponse();
        ServerCodecConfigurer codecConfigure = ServerCodecConfigurer.create();
        WebSessionManager sessionManager = exchange -> Mono.empty();
        AcceptHeaderLocaleContextResolver localeContextResolver = new AcceptHeaderLocaleContextResolver();
        ServerWebExchange exchange = new DefaultServerWebExchange(request, response, sessionManager, codecConfigure, localeContextResolver);

        when(filterChain.filter(exchange)).thenReturn(Mono.empty());

        StepVerifier.create(corsGlobalConfiguration.corsFilter().filter(exchange, filterChain))
                .verifyComplete();

        assertNull(response.getStatusCode());
    }

}