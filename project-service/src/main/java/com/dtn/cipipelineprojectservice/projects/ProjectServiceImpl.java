package com.dtn.cipipelineprojectservice.projects;

import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-13:50
 * @Project project-service
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
