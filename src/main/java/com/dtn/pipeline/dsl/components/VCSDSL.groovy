package com.dtn.pipeline.dsl.components

import com.dtn.pipeline.dsl.utils.AppUtils
import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.codehaus.groovy.runtime.ProcessGroovyMethods

/**
 * @Created 31/08/2021-16:21
 * @Project pipeline-dsl-runner
 * @author danyls ngongang
 */
class VCSDSL {

    void github(String remoteRepository, String workingBranch){
        println AppUtils.colorTextInFadeRed("[GITHUB] cloning ")
        println AppUtils.colorTextInCian("git clone "+ remoteRepository)
        String gitCLoneCmd = "git clone $remoteRepository source-code && cd ./source-code && git checkout $workingBranch && ls -a";
        final def execute = ProcessGroovyMethods.execute(gitCLoneCmd,[],new File("."))
        DefaultGroovyMethods.inspect(execute);
        String cmd = "git checkout $workingBranch && ls ";
        println ProcessGroovyMethods.execute(cmd,[],new File("./source-code")).inspect()
    }
}
