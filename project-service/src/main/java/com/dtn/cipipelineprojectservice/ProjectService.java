package com.dtn.cipipelineprojectservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-13:50
 * @Project project-service
 */
@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
}
