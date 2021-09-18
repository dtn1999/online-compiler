package com.dtn.pipeline.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author danyls ngongang
 * @Created 09/09/2021-14:02
 * @Project runner
 */
public interface DomainStageRepository extends MongoRepository<DomainStage, String> {

}
