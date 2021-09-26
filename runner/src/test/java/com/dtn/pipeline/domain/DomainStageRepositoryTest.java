package com.dtn.pipeline.domain;


import groovy.lang.GroovyShell;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author danyls ngongang
 * @Created 09/09/2021-14:07
 * @Project runner
 */
@ExtendWith({SpringExtension.class})
@SpringBootTest
class DomainStageRepositoryTest {


    @Autowired
    private DomainStageRepository stageRepository;

    @Test
    public void test() throws IOException {
        File file = new File("src/main/resources/static/" + "pipeline.gdsl");
        String pipelineURI="https://raw.githubusercontent.com/dtn1999/hackernews-node/master/pipeline.gdsl";
        FileUtils.copyURLToFile(new URL(pipelineURI), file);
    }

    @Test
    public void test_getter() throws IOException {
        File scriptPath = new File("src/main/resources/static/pipeline.gdsl");
        GroovyShell shell = new GroovyShell();
    }
}
