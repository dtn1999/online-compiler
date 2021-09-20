package com.dtn.pipeline.gateway.projects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danyls ngongang
 * @Created 18/09/2021-16:55
 * @Project ci-pipeline-gateway
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repository {
    private String name;
    private String url;
    private String branchesUrl;
}
