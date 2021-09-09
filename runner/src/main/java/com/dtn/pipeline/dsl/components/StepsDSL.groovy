package com.dtn.pipeline.dsl.components

import com.dtn.pipeline.dsl.DSL
import com.dtn.pipeline.dsl.utils.AppUtils
import org.codehaus.groovy.runtime.ProcessGroovyMethods

/**
 * @Created 31/08/2021-16:31
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class StepsDSL {
    void sh(String script){
        Process process = script.execute([],new File("/$DSL.workingDirectory"))
        process.waitForProcessOutput(System.out, System.err)
    }

    void echo(String message){
        String output = "echo $message".execute().text;
        println AppUtils.colorTextInWhite(output)
    }
}
