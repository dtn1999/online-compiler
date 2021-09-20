package com.dtn.pipeline.gateway.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-07:55
 * @Project ci-pipeline-gateway
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    private final LoginDetailsRepository loginDetailsRepository;

    @Override
    public void save(LoginDetails loginDetails) {
        loginDetailsRepository.save(loginDetails);
    }
}
