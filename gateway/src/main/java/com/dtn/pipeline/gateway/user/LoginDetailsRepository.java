package com.dtn.pipeline.gateway.user;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-07:23
 * @Project ci-pipeline-gateway
 */
@Repository
public interface LoginDetailsRepository extends ReactiveMongoRepository<LoginDetails, String> {

}
