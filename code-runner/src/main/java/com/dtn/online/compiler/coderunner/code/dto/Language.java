package com.dtn.online.compiler.coderunner.code.dto;

import lombok.Getter;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@Getter
public enum Language {
    CPP("cpp"),
    PYTHON("py"),
    JAVA("java")
    ;
    private final String lang;
    Language(String lang) {
        this.lang = lang;
    }
}
