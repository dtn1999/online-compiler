package com.dtn.executionworker.dsl.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author danyls ngongang
 * @Created 07/09/2021-23:04
 * @Project runner
 */
public class StringUtils {
    private static Pattern GIT_PROJECT_NAME = Pattern.compile("\\/(\\w+(-)?(\\w+))+.git");
    public static String extractRepoNameFromURI(String url){
        Matcher matcher = GIT_PROJECT_NAME.matcher(url);
        if( matcher.find() ){
            return  matcher.group(0).replace(".git","").substring(1);
        }
        return  null;
    }
}
