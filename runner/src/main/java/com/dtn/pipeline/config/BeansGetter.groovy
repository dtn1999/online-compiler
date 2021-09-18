package com.dtn.pipeline.config

import com.dtn.pipeline.PipelineDSLRunnerApplication
import com.dtn.pipeline.domain.DomainStageRepository
import com.dtn.pipeline.domain.PipelineRepository
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * @Created 18/09/2021-08:32
 * @Project runner
 * @author danyls ngongang
 */
class BeansGetter {

    static  ApplicationContext context = new AnnotationConfigApplicationContext(PipelineDSLRunnerApplication.class);

    static  getBean( String beanName){
        return  context.getBean( beanName );
    }


    static DomainStageRepository domainStageRepository(){
        return  getBean("domainStageRepository");
    }

    static PipelineRepository pipelineRepository(){
        return getBean("pipelineRepository");
    }
}
