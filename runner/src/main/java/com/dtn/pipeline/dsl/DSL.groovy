package com.dtn.pipeline.dsl

import com.dtn.pipeline.dsl.components.PipelineDSL

/**
 * @Created 31/08/2021-16:18
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class DSL {
    String baseDirectory =""    ;
    static void pipeline(@DelegatesTo(value = PipelineDSL, strategy = Closure.DELEGATE_ONLY) final Closure closure){
        final PipelineDSL dsl = new PipelineDSL();
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }
}
