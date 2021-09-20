package com.dtn.pipeline.gateway.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-07:16
 * @Project ci-pipeline-gateway
 */
@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDetails {
    @Id
    private String id;

    @NotNull
    private String login;

    @NotNull
    private String profileUrl;

    @NotNull
    private  String userName;

    @NotNull
    private String provider;

    @NotNull
    private Instant expireAt;
}
