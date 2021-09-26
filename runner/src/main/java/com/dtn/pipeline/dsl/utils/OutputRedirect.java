package com.dtn.pipeline.dsl.utils;

import com.dtn.pipeline.domain.DomainStage;

import java.io.IOException;

/**
 * @author danyls ngongang
 * @Created 08/09/2021-09:29
 * @Project runner
 */
public class OutputRedirect implements Appendable {
    private DomainStage stage;
    public  OutputRedirect(DomainStage stage){
        this.stage = stage;
    }
    @Override
    public Appendable append(CharSequence csq) throws IOException {
        System.out.println(csq);
        stage.setOutput( stage.getOutput() + csq);
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
