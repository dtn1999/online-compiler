package com.dtn.pipeline.projectservice.projects;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

/**
 * @author danyls ngongang
 * @Created 06/09/2021-15:01
 * @Project project-service
 */
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServiceImpl projectService;
    private final WebClient webClient;

    @GetMapping("")
    public ResponseEntity getAllProjects(@RegisteredOAuth2AuthorizedClient("github")
                                                     OAuth2AuthorizedClient client ){
        Object[] block = webClient.get()
                .uri("users/dtn1999/repos")
                .accept(MediaType.APPLICATION_JSON)
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(Object[].class)
                .log()
                .block();
        return ResponseEntity.ok( projectService.getAllProjects() );
    }

}
