package com.dtn.online.compiler.coderunner.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExecutionResult {
    private ExecutionVerdict verdict;
    private String output;
}
