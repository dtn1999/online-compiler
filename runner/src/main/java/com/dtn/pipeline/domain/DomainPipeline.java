package com.dtn.pipeline.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * @author danyls ngongang
 * @Created 09/09/2021-07:23
 * @Project runner
 */
@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DomainPipeline {
  private String id;
  private Boolean success;
  private Boolean running;
  private Boolean finished;

  @DBRef
  private Set<DomainStage> stages = new HashSet<>();

  public void addStage(DomainStage domainStage) {
    if( stages == null ){
      stages = new HashSet<>();
    }
    stages.add( domainStage );
  }
}
