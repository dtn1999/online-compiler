package com.dtn.pipeline.gateway.projects;

import com.dtn.pipeline.gateway.projects.dto.WebHookConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

/**
 * @author danyls ngongang
 * @Created 18/09/2021-16:03
 * @Project ci-pipeline-gateway
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectController {

    private final WebClient webClient ;

    @GetMapping("/repositories")
    public ResponseEntity getRepositories(@RegisteredOAuth2AuthorizedClient("github")
                                                  OAuth2AuthorizedClient client){
        Mono<ArrayNode> arrayNodeMono = webClient.get()
                .uri("users/dtn1999/repos")
                .accept(MediaType.APPLICATION_JSON)
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(ArrayNode.class);
          return ResponseEntity.ok(arrayNodeMono.flatMap( arrayNode -> {
              System.out.println( arrayNode );
              Set<Repository> repositories = new HashSet<>();

              for (Iterator itr= arrayNode.elements(); itr.hasNext(); itr.next()){
                  JsonNode jsonNode = (JsonNode) itr.next();

                  repositories.add(Repository.builder()
                          .name(jsonNode.get("name").asText())
                          .url(jsonNode.get("clone_url").asText())
                          .branchesUrl( jsonNode.get("branches_url").asText())
                          .build()
                  );
              }
              return Mono.just( repositories );
           }));
    }


    @PostMapping("/repos/{owner}/{repo}/hooks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createWebHooks(@NotNull @PathVariable String owner, @NotNull @PathVariable String repo, @Valid @RequestBody WebHookConfig request , @RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient client){
        Mono<JsonNode> jsonNodeMono = webClient.post()
                .uri(String.format("/repos/%s/%s/hooks", owner, repo))
                .accept(MediaType.APPLICATION_JSON)
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(JsonNode.class);
        return  ResponseEntity.ok( jsonNodeMono );
    }

}
