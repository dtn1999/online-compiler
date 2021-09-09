package com.dtn.pipeline.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class Pipeline {
  private String id;
  private Boolean status;

  @DBRef
  private Set<DomainStage> stages;
}
