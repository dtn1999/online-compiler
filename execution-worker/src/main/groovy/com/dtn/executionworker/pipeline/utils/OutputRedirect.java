package com.dtn.executionworker.dsl.utils;

import com.dtn.executionworker.domain.StageDocument;
import com.dtn.executionworker.domain.StageRepository;
import com.dtn.executionworker.dsl.components.Stage;

import java.io.IOException;

/**
 * @author danyls ngongang
 * @Created 08/09/2021-09:29
 * @Project runner
 */
public class OutputRedirect implements Appendable {
    private final StageRepository stageRepository;
    private final StageDocument stage ;

    public OutputRedirect(StageDocument stage, StageRepository stageRepository) {
        this.stageRepository = stageRepository;
        this.stage = stage;
    }

    @Override
    public Appendable append(CharSequence csq) throws IOException {
        stage.setOutPut( stage.getOutPut() + csq);
        stageRepository.save( stage );
        return this;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
        return null;
    }

    @Override
    public Appendable append(char c) throws IOException {
        return null;
    }
}
