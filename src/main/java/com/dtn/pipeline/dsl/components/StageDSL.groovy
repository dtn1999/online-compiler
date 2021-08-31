package com.dtn.pipeline.dsl.components

/**
 * @Created 31/08/2021-16:30
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class StageDSL {

    void steps(@DelegatesTo(value = StepsDSL, strategy = Closure.DELEGATE_ONLY) final Closure closure){
        final StepsDSL dsl = new StepsDSL()
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }
}
