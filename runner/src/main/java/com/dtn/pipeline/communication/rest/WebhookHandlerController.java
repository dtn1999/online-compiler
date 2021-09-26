package com.dtn.pipeline.communication.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import groovy.util.GroovyScriptEngine;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.groovy.util.ScriptRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URL;

/**
 * @author danyls ngongang
 * @Created 02/09/2021-13:50
 * @Project pipline-dsl-runner
 */
@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class WebhookHandlerController {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    @SneakyThrows
    @PostMapping("/push")
    public ResponseEntity message(@RequestBody String body){
        ObjectNode jsonNodes = objectMapper.readValue(body, ObjectNode.class);
        String username = jsonNodes.get("repository").get("owner").get("name").asText();
        String repositoryName = jsonNodes.get("repository").get("name").asText();
        String filename = "pipeline.gdsl";

        System.out.println( String.format("username : %s , repository name : %s", username, repositoryName) );
        String resourceEndPoint = String.format("https://api.github.com/repos/%s/%s/contents/%s", username, repositoryName, filename );
        ObjectNode responseJson = restTemplate.getForObject(resourceEndPoint, ObjectNode.class);
        String pipelineURI = responseJson.get("download_url").asText();
        File scriptPath = new File("src/main/resources/static/" + filename);
        FileUtils.copyURLToFile(new URL(pipelineURI), scriptPath);

        ScriptRunner.runScript(scriptPath);
        System.out.println(responseJson);
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
