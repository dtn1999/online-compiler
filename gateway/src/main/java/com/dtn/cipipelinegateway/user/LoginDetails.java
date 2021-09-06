package com.dtn.cipipelinegateway.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

/**
 * @author danyls ngongang
 * @Created 05/09/2021-07:16
 * @Project ci-pipeline-gateway
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDetails {
    @Id
    private String id;

    @Column( nullable = false)
    private String provider;

    @Column( nullable = false )
    private String clientId;

    @Column( nullable = false)
    private String accessToken;

    @Column( nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private Instant expireAt;
}
