package com.dtn.pipeline.gateway.security;

import org.springframework.security.web.server.authentication.RedirectServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-08:04
 * @Project ci-pipeline-gateway
 */
@Component
public class OAuth2AuthenticationFailureHandler extends RedirectServerAuthenticationFailureHandler {

    public OAuth2AuthenticationFailureHandler() {
        super("/failure");
    }
}
