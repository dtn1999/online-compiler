package com.dtn.pipeline.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    public void test(){

    }
}
