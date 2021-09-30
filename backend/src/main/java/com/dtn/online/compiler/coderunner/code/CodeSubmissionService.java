package com.dtn.online.compiler.coderunner.code;

import com.dtn.online.compiler.coderunner.code.dto.CodeSubmissionDTO;
import com.dtn.online.compiler.coderunner.code.dto.ExecutionResult;
import com.dtn.online.compiler.coderunner.code.dto.ExecutionVerdict;
import com.dtn.online.compiler.coderunner.utils.ApiResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * Created by dtn1999
 * Date: 9/27/21
 */
@Service
@Slf4j
public class CodeSubmissionService {

    private final File workingDirectory;
    private final String runnerScript;

    public CodeSubmissionService() {
        runnerScript     = "./runner.sh";
        workingDirectory = new File("src/main/resources/static");
    }


    public ApiResponse runCode(@NotNull CodeSubmissionDTO codeSubmissionDTO, @Valid ExecutionConstraints constraints) {
        final String code = codeSubmissionDTO.getCode();
        final String lang = codeSubmissionDTO.getLanguage().getLang();
        final String input = codeSubmissionDTO.getInput();
        final long memoryLimit = constraints.getMemoryLimit();
        final long timeLimit = constraints.getTimeLimit();
        final String[] cmd = {runnerScript, code, input , lang, "" + memoryLimit};
        ProcessBuilder processBuilder = new ProcessBuilder().directory(workingDirectory).command(cmd);
        return ApiResponse.builder()
                .data(monitoredCommandExecution(processBuilder, timeLimit))
                .error(null)
                .success(true)
                .build();
    }

    /**
     * this function help to monitor if each code submission run under a given time contraints
     *
     * @param processBuilder
     * @param maxExecutionTime
     * @return
     */
    private ExecutionResult monitoredCommandExecution(final ProcessBuilder processBuilder, final long maxExecutionTime) {
        final ExecutorService service = Executors.newSingleThreadExecutor();
        String output;
        ExecutionResult executionResult = ExecutionResult.builder().build();
        try {
            final Future<HashMap<String,Object>> executionContext = service.submit(( ) -> {
                // start the process
                final Process process = processBuilder.start();
                HashMap<String,Object> res = new HashMap<>();
                String stdout = collectProcessStdOutOutput(process);
                String stderr = collectProcessStdErrOutput(process);
                if(stderr!= null && !stderr.equals("")){
                    res.put("success", false);
                }else{
                    res.put("success", true);
                }
                res.put("output", stdout + stderr);

                return res;
            });

            HashMap<String, Object> execResult = executionContext.get(maxExecutionTime, TimeUnit.MILLISECONDS);
            Boolean success = (Boolean) execResult.get("success");
            output = (String)execResult.get("output");
            if( success ){
                executionResult.setVerdict(ExecutionVerdict.PASSED);
            }else{
                executionResult.setVerdict(ExecutionVerdict.FAILED);
            }
            executionResult.setOutput(parseOutput(output));
            executionResult.setDate(   LocalDateTime.now().toString() );
            log.info(output);

        } catch (final TimeoutException e) {
            output = "Calculation took to long";
            executionResult.setOutput(parseOutput(output));
            executionResult.setVerdict(ExecutionVerdict.TIME_LIMIT_EXCEPTION);
            executionResult.setDate( LocalDateTime.now().toString()  );
            log.info(output);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
        return executionResult;
    }


    /**
     * collect the output (stdout) of the given process
     *
     * @param process
     * @return
     */
    @SneakyThrows
    private String collectProcessStdOutOutput(final Process process) {
        // collect output rom the standard output
        return  getOutputFromStream(process.getInputStream());
    }

    /**
     * collect the output (stderr) of the given process
     * @param process
     * @return
     */
    @SneakyThrows
    private String collectProcessStdErrOutput(final Process process) {
        // collect output rom the standard output error
        return getOutputFromStream(process.getErrorStream());
    }

    @SneakyThrows
    private String getOutputFromStream(InputStream  inputStream) {

        BufferedReader stdoutReader =
                new BufferedReader(new InputStreamReader( inputStream ));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line = stdoutReader.readLine()) != null) {
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }



    /**
     * parsed the process output to return an understandable output
     * @param output
     * @return
     */
    private String parseOutput(String output){
        return output;
    }
}
