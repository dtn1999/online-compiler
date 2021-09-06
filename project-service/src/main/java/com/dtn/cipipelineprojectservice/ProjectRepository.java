package com.dtn.cipipelineprojectservice;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-13:50
 * @Project project-service
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
}
