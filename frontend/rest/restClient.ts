import axios from 'axios';
import { Utilities } from "./utils/utilities";

export type ClientLanguageType = "c_cpp"|"python"|"java";
type ServerLanguageType = "CPP"|"JAVA"|"PYTHON";

export interface CodeSubmission {
    code: string;
    input: string;
    language: ServerLanguageType,

}

export interface SubmissionResult {
    verdict: string,
    date: string;
    output: string;
}

//
const SERVER_URL = `http://localhost:8080/api`;

export const RestClient = {
    submitCode: async (code:string,input:string, lang: ClientLanguageType)=>{
        const requestPayload:CodeSubmission = {
            code,
            input,
            language: Utilities.getServerLanguage(lang)
        }
        const {data:response} = await axios.post(`${SERVER_URL}/code`, requestPayload);
        
        const { data:executionResult} = response;
        console.log( executionResult );
        return executionResult;
    }
}


