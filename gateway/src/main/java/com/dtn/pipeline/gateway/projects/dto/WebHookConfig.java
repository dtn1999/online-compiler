package com.dtn.pipeline.gateway.projects.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author danyls ngongang
 * @Created 20/09/2021-17:50
 * @Project ci-pipeline-gateway
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WebHookConfig {

    @JsonProperty("content_type")
    @NotNull
    private String contentType;

    @JsonProperty("insecure_ssl")
    @NotNull
    private int insecureSSL;

    @JsonProperty("url")
    @NotNull
    private String url;
}
