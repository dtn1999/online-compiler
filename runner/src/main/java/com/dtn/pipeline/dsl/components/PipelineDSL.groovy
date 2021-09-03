package com.dtn.pipeline.dsl.components

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

/**
 * @Created 31/08/2021-16:20
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class PipelineDSL {

    ConcurrentMap<String, String> env = [:] as ConcurrentHashMap

    void vcs(@DelegatesTo(value = VCSDSL, strategy = Closure.DELEGATE_ONLY)final  Closure closure){
        final VCSDSL dsl = new VCSDSL()
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }

    void environment(@DelegatesTo(value = Map, strategy = Closure.DELEGATE_FIRST) final Closure closure){
        env.with(closure)
    }

    void stages(@DelegatesTo(value = StagesDSL , strategy = Closure.DELEGATE_ONLY) final Closure closure){
        final StagesDSL dsl = new StagesDSL()
        closure.delegate = dsl
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()

        dsl.getStages().each{ stage -> {
            stage.run()
        }}
    }


}
