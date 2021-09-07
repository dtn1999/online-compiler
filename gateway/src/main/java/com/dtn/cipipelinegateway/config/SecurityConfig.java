package com.dtn.cipipelinegateway.config;

import com.dtn.cipipelinegateway.security.OAuth2LoginSuccessHandler;
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

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http

                .csrf().disable()
                .authorizeExchange( exchangeSpec -> exchangeSpec.anyExchange().authenticated())
                .oauth2Login()
                .clientRegistrationRepository( clientRegistrationRepository )
                .authenticationSuccessHandler( auth2LoginSuccessHandler )
                .and()
                .oauth2Client( Customizer.withDefaults() );

        return  http.build();
    }
}
