package com.dtn.pipeline.dsl.utils

/**
 * @Created 31/08/2021-16:42
 * @Project pipeline-dsl-runner
 * @author danyls ngongang
 */
class AppUtils {

    static String colorTextInGreen(String msg){
        return "\u001B[1;32m" + msg
    }

    static String colorTextInWhite(String msg){
        return "\u001B[1;38m" + msg
    }

    static String colorTextInFadeRed(String msg){
        return "\u001B[1;33m" + msg
    }


    static String colorTextInCian(String msg){
        return "\u001B[1;36m" + msg
    }
}
