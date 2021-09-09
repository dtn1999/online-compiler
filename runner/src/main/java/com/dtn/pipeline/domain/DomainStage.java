package com.dtn.pipeline.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author danyls ngongang
 * @Created 09/09/2021-07:23
 * @Project runner
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainStage {
    @Id
    private String id;
    private String name;
    private String output;
    private Boolean success;
}
