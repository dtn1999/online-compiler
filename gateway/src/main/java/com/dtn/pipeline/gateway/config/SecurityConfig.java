package com.dtn.pipeline.gateway.config;

import com.dtn.pipeline.gateway.security.OAuth2AuthenticationFailureHandler;
import com.dtn.pipeline.gateway.security.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author danyls ngongang
 * @Created 04/09/2021-07:49
 * @Project ci-pipeline-gateway
 */
@EnableWebFluxSecurity
@EnableWebFlux
@RequiredArgsConstructor
public class SecurityConfig {

    private final  OAuth2LoginSuccessHandler auth2LoginSuccessHandler;
    private final ReactiveClientRegistrationRepository clientRegistrationRepository;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http

                .csrf().disable()
                .authorizeExchange( exchangeSpec -> exchangeSpec.anyExchange().authenticated())
                .oauth2Login()
                .clientRegistrationRepository( clientRegistrationRepository )
                .authenticationSuccessHandler( auth2LoginSuccessHandler )
                .authenticationFailureHandler( oAuth2AuthenticationFailureHandler )
                .and()
                .oauth2Client( Customizer.withDefaults() )

//                .oauth2ResourceServer()
//                .jwt()
        ;

        return  http.build();
    }
}
