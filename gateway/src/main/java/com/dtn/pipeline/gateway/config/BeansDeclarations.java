package com.dtn.pipeline.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-06:36
 * @Project ci-pipeline-gateway
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.dtn.pipeline.gateway")
public class BeansDeclarations {

    @Bean
    public ReactiveClientRegistrationRepository reactiveClientRegistrationRepository(){
        return createReactiveClientRegistrationRepo();
    }

    private ReactiveClientRegistrationRepository createReactiveClientRegistrationRepo(){
        return new InMemoryReactiveClientRegistrationRepository( clientRegistration() );
    }

    @Bean
    public ReactiveOAuth2AuthorizedClientService oAuth2AuthorizedClientService(){
        return new InMemoryReactiveOAuth2AuthorizedClientService( createReactiveClientRegistrationRepo() );
    }

    private ClientRegistration clientRegistration(){
        return CommonOAuth2Provider.GITHUB
                .getBuilder("github")
                .clientId("Iv1.f4d28dae012cb15c")
                .clientSecret("7ee58e7018ef04218c7da560505176a2d395e7aa")
                .redirectUri("http://localhost:8080/login/oauth2/code/github")
                .scope("public_repo","write:repo_hook","read:user","user:email")
                .build();

    }

    @Bean
    public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrationRepo,
                               ServerOAuth2AuthorizedClientRepository authorizedClientRepo){
        ServerOAuth2AuthorizedClientExchangeFilterFunction filter =
                new ServerOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepo, authorizedClientRepo);
        return WebClient.builder()
                .baseUrl("https://api.github.com")
                .filter( filter ).build();
    }
}
