package com.dtn.pipeline.dsl.components

import com.dtn.pipeline.config.BeansGetter
import com.dtn.pipeline.domain.DomainStage
import com.dtn.pipeline.domain.DomainStageRepository
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
        DomainStage stage = DomainStage.builder().name("Git Clone").build()
        println AppUtils.colorTextInFadeRed("[GITHUB] cloning")
        println AppUtils.colorTextInCian("git clone "+ remoteRepository)
        String repoName = StringUtils.extractRepoNameFromURI(remoteRepository)
        String locationClonedRepo = "$DSL.baseDirectory/$repoName"

        stage.setOutput( ""+"[GITHUB] cloning "+" \n "+"git clone "+ remoteRepository+" \n ")
        stage = BeansGetter.domainStageRepository().save( stage )
        // add the
        DSL.addNewStage( stage )

        String gitCLoneCmd = "git clone -v $remoteRepository $repoName";
        final Process process = ProcessGroovyMethods.execute(gitCLoneCmd,[],new File("$DSL.baseDirectory/"))
        process.waitForProcessOutput( new OutputRedirect(stage) , new OutputRedirect(stage ));
        if( process.exitValue() == 0){
            DSL.workingDirectory = locationClonedRepo
        }else {
            println process.err.text
        }

    }
}
