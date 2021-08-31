package com.dtn.pipeline.dsl.components

import com.dtn.pipeline.dsl.utils.AppUtils

/**
 * @Created 31/08/2021-16:31
 * @Project pipline-dsl-runner
 * @author danyls ngongang
 */
class StepsDSL {
    void sh(String script){
        String output = script.execute().text
        println AppUtils.colorTextInWhite(output)
    }

    void echo(String message){
        String output = "echo $message".execute().text;
        println AppUtils.colorTextInWhite(output)
    }
}
