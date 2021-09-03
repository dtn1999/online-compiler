package com.dtn.pipeline.dsl.components

/**
 * @Created 31/08/2021-16:29
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class StagesDSL {

    List<Stage> stages = [];

    void stage(String name, @DelegatesTo(value = StageDSL, strategy = Closure.DELEGATE_ONLY)final Closure closure){
        stages << new Stage(name , closure)
    }
}
