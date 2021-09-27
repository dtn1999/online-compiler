package com.dtn.online.compiler.coderunner.code.dto;

import lombok.Getter;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@Getter
public enum ExecutionVerdict {
    PASSED("AC"),
    FAILED("WA"),
    COMPILER_ERROR("CE"),
    TIME_LIMIT_EXCEPTION("TLE")
    ;
    private final String verdict;

    ExecutionVerdict(String verdict) {
        this.verdict = verdict;
    }
}
