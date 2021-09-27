package com.dtn.online.compiler.coderunner.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExecutionConstraints {
    @NotNull
    @Min(1)
    private long memoryLimit;
    @NotNull
    @Min(1)
    private long timeLimit;
}
