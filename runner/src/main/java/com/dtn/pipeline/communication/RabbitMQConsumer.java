package com.dtn.pipeline.communication;

import org.apache.groovy.util.ScriptRunner;

import java.io.File;

/**
 * @author danyls ngongang
 * @Created 02/09/2021-12:09
 * @Project pipline-dsl-runner
 */
public class RabbitMQConsumer {

    public static void test(){
        ScriptRunner.runScript(new File("/home/dtn-1999/GITHUB/languages/dsl/pipline-dsl-runner/src/main/java/com/dtn/pipeline/PipelineDefinition.gdsl"));
    }
}
