package com.dtn.pipeline.dsl


import com.dtn.pipeline.domain.DomainPipeline
import com.dtn.pipeline.domain.DomainStage
import com.dtn.pipeline.dsl.components.PipelineDSL

import java.nio.file.Path
import java.nio.file.Paths

/**
 * @Created 31/08/2021-16:18
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class DSL {
    static  Path path= Paths.get("home/dtn-1999/runner-working-dir");
    static  String baseDirectory = "/"+path.toString();
    static  String workingDirectory ;
    static  DomainPipeline domainPipeline;
    static  DSL INSTANCE ;
    DSL(){
        domainPipeline = DomainPipeline.builder()
                                    .running( true)
                                    .finished( false )
                                    .success( false)
                                    .build();
    }

    static void pipeline(@DelegatesTo(value = PipelineDSL, strategy = Closure.DELEGATE_ONLY) final Closure closure){
        // initialize the context
        DSL.init()

        final PipelineDSL dsl = new PipelineDSL();
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }

    static DSL init(){
        if( INSTANCE== null ){
            INSTANCE = new DSL()
        }
        return  INSTANCE
    }

    static void update(){
      //  pipelineRepository.save( domainPipeline )
    }

    static  void addNewStage(DomainStage stage){
        domainPipeline.addStage( stage)
      //  pipelineRepository.save( domainPipeline )
    }
}
