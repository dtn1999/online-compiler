package com.dtn.cipipelineprojectservice.projects;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danyls ngongang
 * @Created 06/09/2021-15:01
 * @Project project-service
 */
@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServiceImpl projectService;

    @GetMapping("")
    public ResponseEntity getAllProjects(){
     return ResponseEntity.ok( projectService.getAllProjects() );
    }

}
