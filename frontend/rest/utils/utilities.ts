import { ClientLanguageType } from "../restClient";

export const  Utilities = {
    getServerLanguage: (language:ClientLanguageType)=>{
        switch(language){
            case "c_cpp":
            return "CPP";
            case "java":
            return "JAVA";
            case "python":
            return "PYTHON";
        }
    }
}