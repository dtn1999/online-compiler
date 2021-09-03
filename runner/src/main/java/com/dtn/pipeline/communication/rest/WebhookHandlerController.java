package com.dtn.pipeline.communication.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author danyls ngongang
 * @Created 02/09/2021-13:50
 * @Project pipline-dsl-runner
 */
@RestController
@RequestMapping("/api/pipeline-dsl/runner")
public class WebhookHandlerController {

    @PostMapping("/push")
    public ResponseEntity message(@RequestBody Object body){
        System.out.println( body );
        return ResponseEntity.ok(body);
    }

    @GetMapping ("/push")
    public ResponseEntity test(){
        System.out.println("Hello world");
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping ("")
    public ResponseEntity redirectCallbackHandler(){
        System.out.println("Hello world");
        return ResponseEntity.ok("Hello World");
    }
}
