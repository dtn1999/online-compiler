package com.dtn.online.compiler.coderunner.code;

import com.dtn.online.compiler.coderunner.code.dto.CodeSubmissionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeSubmissionController {

    private final CodeSubmissionService codeSubmissionService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity runCode(@Valid @RequestBody CodeSubmissionDTO codeSubmissionDTO){
        ExecutionConstraints constraints = ExecutionConstraints.builder()
                .memoryLimit(64)
                .timeLimit(5000)
                .build();

        return ResponseEntity.ok(codeSubmissionService.runCode(codeSubmissionDTO,constraints));
    }

    @GetMapping("")
    public ResponseEntity ping(){
        return ResponseEntity.ok( "Response from your ping message");
    }

}
