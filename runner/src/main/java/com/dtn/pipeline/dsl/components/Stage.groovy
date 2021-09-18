package com.dtn.pipeline.dsl.components

import com.dtn.pipeline.PipelineDSLRunnerApplication
import com.dtn.pipeline.config.BeansGetter
import com.dtn.pipeline.domain.DomainStage
import com.dtn.pipeline.domain.DomainStageRepository
import com.dtn.pipeline.dsl.DSL
import com.dtn.pipeline.dsl.utils.AppUtils
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.*


/**
 * @Created 31/08/2021-16:30
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class Stage {

    private Closure closure
    private String name
    static  DomainStage domainStage;

    Stage(String name, Closure closure){
        this.name = name
        this.closure = closure
        domainStage = DomainStage.init()
        domainStage.setName( name )
        DomainStage savedStage = BeansGetter.domainStageRepository().save( domainStage )
        DSL.addNewStage( savedStage )
    }

    void run(){
        println AppUtils.colorTextInGreen("[Stage] " + name + "...")
        updateOutPut("[Stage] " + name + "...")

        final StageDSL dsl = new StageDSL()
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }


    static void updateOutPut(String output){
        if( domainStage == null ){
           throw new RuntimeException(" The domain stage object most not be null")
        }
        domainStage.setOutput( domainStage.getOutput() + output )
        BeansGetter.domainStageRepository().save( domainStage )
    }
}
