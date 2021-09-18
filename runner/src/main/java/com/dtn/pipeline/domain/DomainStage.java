package com.dtn.pipeline.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @author danyls ngongang
 * @Created 09/09/2021-07:23
 * @Project runner
 */
@Document( collation = "stage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainStage {
    @Id
    private String id;

    @NotNull
    private String name;
    private Boolean success;
    private Boolean running;
    private Boolean finished;
    private String output;

    public static  DomainStage init(){
        return  DomainStage.builder()
                .name("")
                .output("")
                .running( true)
                .finished( false )
                .success( false )
                .build();
    }
}
