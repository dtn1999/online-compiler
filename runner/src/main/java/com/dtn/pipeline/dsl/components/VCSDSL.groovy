package com.dtn.pipeline.dsl.components

import com.dtn.pipeline.dsl.DSL
import com.dtn.pipeline.dsl.utils.AppUtils
import com.dtn.pipeline.dsl.utils.StringUtils
import org.codehaus.groovy.runtime.DefaultGroovyMethods
import org.codehaus.groovy.runtime.ProcessGroovyMethods
import com.dtn.pipeline.dsl.utils.OutputRedirect

/**
 * @Created 31/08/2021-16:21
 * @Project pipeline-dsl-runner
 * @author danyls ngongang
 */
class VCSDSL {

    void github(String remoteRepository, String workingBranch){
        println AppUtils.colorTextInFadeRed("[GITHUB] cloning ")
        println AppUtils.colorTextInCian("git clone "+ remoteRepository)
        String repoName = StringUtils.extractRepoNameFromURI(remoteRepository)
        String locationClonedRepo = "$DSL.baseDirectory/$repoName"
        String gitCLoneCmd = "git clone -v $remoteRepository $repoName";

        final Process process = ProcessGroovyMethods.execute(gitCLoneCmd,[],new File("$DSL.baseDirectory/"))
        process.waitForProcessOutput( new OutputRedirect() , new OutputRedirect());
        if( process.exitValue() == 0){
            DSL.workingDirectory = locationClonedRepo
        }else {
            println process.err.text
        }

    }
}
