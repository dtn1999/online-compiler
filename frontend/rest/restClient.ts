import axios from 'axios';
import { Utilities } from "./utils/utilities";

export type ClientLanguageType = "c_cpp"|"python"|"java";
type ServerLanguageType = "CPP"|"JAVA"|"PYTHON";

export interface CodeSubmission {
    code: string;
    language: ServerLanguageType

}

//
const SERVER_URL = `${process.env.SERVER_URL}`;

export const RestClient = {
    submitCode: async (code:string, lang: ClientLanguageType)=>{
        const requestPayload:CodeSubmission = {
            code,
            language: Utilities.getServerLanguage(lang)
        }
        const {data:response} = await axios.post(`${SERVER_URL}/code`, JSON.stringify(requestPayload));
        const { data:executionResult} = response;
        console.log( executionResult );
        return executionResult;
    }
}


