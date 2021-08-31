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
        String gitCLoneCmd = "git clone $remoteRepository source-code ";
        println ProcessGroovyMethods.execute(gitCLoneCmd,[],new File(".")).text
        String cmd = "ls -a";
        println ProcessGroovyMethods.execute(cmd,[],new File("./source-code")).text
    }
}
