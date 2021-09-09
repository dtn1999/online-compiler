package com.dtn.pipeline.dsl.components

import com.dtn.pipeline.domain.DomainStage
import com.dtn.pipeline.dsl.utils.AppUtils

/**
 * @Created 31/08/2021-16:30
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class Stage {

    private Closure closure
    private String name

    Stage(String name, Closure closure){
        this.name = name
        this.closure = closure
    }

    void run(){
        println AppUtils.colorTextInGreen("[DomainStage] " + name + "...")
        DomainStage stage = DomainStage.name("name").build;
        final StageDSL dsl = new StageDSL()
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }
}
