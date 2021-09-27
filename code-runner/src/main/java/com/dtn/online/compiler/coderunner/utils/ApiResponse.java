package com.dtn.online.compiler.coderunner.utils;

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
@Builder
@Data
public class ApiResponse <D,E>{
    private D data;
    private E error;
    private boolean success;
}
