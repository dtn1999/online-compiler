package com.dtn.pipeline.dsl.components

import com.dtn.pipeline.dsl.DSL
import com.dtn.pipeline.dsl.utils.AppUtils
import com.dtn.pipeline.dsl.utils.OutputRedirect

/**
 * @Created 31/08/2021-16:31
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class StepsDSL {
    void sh(String script){
        Process process = script.execute([],new File("/$DSL.workingDirectory"))
        final OutputRedirect outputSource =  new OutputRedirect(Stage.domainStage);
        process.waitForProcessOutput(outputSource , outputSource)
    }

    void echo(String message){
        String output = "echo $message".execute().text;
        println AppUtils.colorTextInWhite(output)
    }
}
