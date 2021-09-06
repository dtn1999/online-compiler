package com.dtn.cipipelinegateway.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-07:23
 * @Project ci-pipeline-gateway
 */
@Repository
public interface UserLoginDetailsRepository extends MongoRepository<UserLoginDetails, String> {

}
