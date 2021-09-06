package com.dtn.cipipelinegateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

/**
 * @author danyls ngongang
 * @Created 04/09/2021-08:51
 * @Project ci-pipeline-gateway
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @GetMapping("")
    public ResponseEntity test(@RegisteredOAuth2AuthorizedClient("github")
                                             OAuth2AuthorizedClient client){
        final Mono<Object[]> response = webClient.get()
                .uri("users/dtn1999/repos")
                .accept(MediaType.APPLICATION_JSON)
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(Object[].class)
                .log();
        return ResponseEntity.ok( response);
    }
}
