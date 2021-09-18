package com.dtn.pipeline.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author danyls ngongang
 * @Created 18/09/2021-10:08
 * @Project runner
 */
@Repository
public interface PipelineRepository extends MongoRepository<DomainPipeline, String> {
}
