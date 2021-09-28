package com.dtn.online.compiler.coderunner.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CodeSubmissionDTO {

    @NotNull
    private Language language;

    @NotNull
    @NotEmpty
    private String code;

    private String input="";
}
