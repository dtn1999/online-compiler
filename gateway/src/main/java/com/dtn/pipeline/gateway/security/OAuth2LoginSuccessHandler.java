package com.dtn.pipeline.gateway.security;

import com.dtn.pipeline.gateway.user.LoginDetails;
import com.dtn.pipeline.gateway.user.UserService;
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
    private final UserService userService;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        // get user information
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        // get access and refresh token and save it for to reauthenticate user with remember me selected
        clientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),oauthToken.getName())
                .log()
                .subscribe( oAuth2AuthorizedClient -> {
                    OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();
                    OAuth2RefreshToken refreshToken = oAuth2AuthorizedClient.getRefreshToken();

                })
                ;
        LoginDetails loginDetails = LoginDetails.builder()
                .userName( (String)oauthToken.getPrincipal().getAttributes().get("name"))
                .login( (String)oauthToken.getPrincipal().getAttributes().get("login"))
                .profileUrl((String)oauthToken.getPrincipal().getAttributes().get("avatar_url"))
                .provider(oauthToken.getAuthorizedClientRegistrationId())
                .build();

        userService.save( loginDetails  );
        return super.onAuthenticationSuccess(webFilterExchange, authentication);

    }
}
