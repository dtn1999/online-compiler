package com.dtn.pipeline.gateway.user;

import reactor.core.publisher.Mono;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-07:54
 * @Project ci-pipeline-gateway
 */
public interface UserService {
    void save ( LoginDetails loginDetails);
}
