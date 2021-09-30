package com.dtn.online.compiler.coderunner.code;

import com.dtn.online.compiler.coderunner.code.dto.CodeSubmissionDTO;
import com.dtn.online.compiler.coderunner.code.dto.ExecutionResult;
import com.dtn.online.compiler.coderunner.code.dto.ExecutionVerdict;
import com.dtn.online.compiler.coderunner.code.dto.Language;
import com.dtn.online.compiler.coderunner.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@AutoConfigureMockMvc
@SpringBootTest
class CodeSubmissionServiceTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Test
    @DisplayName("BAD REQUEST")
    void runCode_bad_request() {
        CodeSubmissionDTO codeSubmissionDTO = CodeSubmissionDTO.builder().build();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/code")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( objectMapper.writeValueAsString(codeSubmissionDTO)))
                .andExpect( status().isBadRequest())
                .andExpect( result -> {
                    ApiResponse apiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ApiResponse.class);
                    Assertions.assertNotNull(apiResponse);
                    Assertions.assertFalse(apiResponse.isSuccess());
                    Assertions.assertNull(apiResponse.getData());
                    Assertions.assertNotNull(apiResponse.getError());
                });
    }

    @SneakyThrows
    @Test
    @DisplayName("GOOD REQUEST")
    void runCode_good_request() {
        CodeSubmissionDTO codeSubmissionDTO = CodeSubmissionDTO.builder()
                .language(Language.CPP)
                .code("#include <iostream>\n" +
                        "using namespace std;\n" +
                        "\n" +
                        "int main() {\n" +
                        "\t// your code goes here\n" +
                        "\tcout << \"Hello World!!\"<< endl;\n" +
                        "\treturn 0;\n" +
                        "}\n")
                .build();
//        Mockito.when( codeSubmissionService.runCode(codeSubmissionDTO, ExecutionConstraints.builder()
//                        .timeLimit(2000)
//                        .memoryLimit(63)
//                        .build()))
//                .thenReturn(ApiResponse.builder().data("").error(null).build());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/code")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( objectMapper.writeValueAsString(codeSubmissionDTO)))
                .andExpect( status().isOk())
                .andExpect( result -> {
                    ApiResponse apiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ApiResponse.class);
                    Assertions.assertNotNull(apiResponse);
                    Assertions.assertTrue(apiResponse.isSuccess());
                    Assertions.assertNull(apiResponse.getError());
                    Assertions.assertNotNull(apiResponse.getData());
                    // confirm that the code run without error
                });
    }

    @SneakyThrows
    @Test
    @DisplayName("GOOD REQUEST: BUT INFINITE LOOP")
    void runCode_good_request_but_bad_code() {
        CodeSubmissionDTO codeSubmissionDTO = CodeSubmissionDTO.builder()
                .language(Language.CPP)
                .code("#include <iostream>\n" +
                        "using namespace std;\n" +
                        "\n" +
                        "int main() {\n" +
                        "\t// your code goes here\n" +
                        "\tint i = 0;\n" +
                        "\twhile(true){\n" +
                        "\t    i++;\n" +
                        "\t    cout << i << endl;\n" +
                        "\t}\n" +
                        "\treturn 0;\n" +
                        "}\n")
                .build();
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/code")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content( objectMapper.writeValueAsString(codeSubmissionDTO)))
                .andExpect( status().isOk())
                .andExpect( result -> {
                    ApiResponse apiResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ApiResponse.class);
                    Assertions.assertNotNull(apiResponse);
                    Assertions.assertTrue(apiResponse.isSuccess());
                    Assertions.assertNull(apiResponse.getError());
                    Assertions.assertNotNull(apiResponse.getData());
                    // confirm that the code run without error

                    HashMap executionResult = (HashMap) apiResponse.getData();
                    Assertions.assertEquals("TIME_LIMIT_EXCEPTION", executionResult.get("verdict"));
                });
    }



}