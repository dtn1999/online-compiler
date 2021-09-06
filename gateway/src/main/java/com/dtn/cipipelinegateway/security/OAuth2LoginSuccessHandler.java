package com.dtn.cipipelinegateway.security;

import com.dtn.cipipelinegateway.user.LoginDetails;
import com.dtn.cipipelinegateway.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-06:09
 * @Project ci-pipeline-gateway
 */
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends RedirectServerAuthenticationSuccessHandler {


    private final ReactiveOAuth2AuthorizedClientService clientService ;
    private final WebClient webClient;
    private final UserService userService;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        // get user information
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        // get access and refresh token and save it for to reauthenticate user with remember me selected
        clientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),oauthToken.getName())
                .subscribe( oAuth2AuthorizedClient -> {
                    OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();
                    OAuth2RefreshToken refreshToken = oAuth2AuthorizedClient.getRefreshToken();
                    LoginDetails loginDetails = LoginDetails.builder()
                            .expireAt(accessToken.getExpiresAt())
                            .accessToken(accessToken.getTokenValue())
                            .refreshToken(refreshToken.getTokenValue())
                            .clientId( oAuth2AuthorizedClient.getClientRegistration().getClientId() )
                            .provider(oauthToken.getAuthorizedClientRegistrationId())
                            .build();
                    userService.save(loginDetails);

                });
        return super.onAuthenticationSuccess(webFilterExchange, authentication);

    }
}
