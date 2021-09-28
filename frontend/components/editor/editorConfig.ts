export interface UserEditorSetting {
    theme: string;
    fontSize: number;
    tabSize: number;
    autoComplete: boolean,
    lineNumbers: boolean,
    language: "c_cpp"|"python"|"java",
    input: string
}

export const userEditorSettingInitialValues:UserEditorSetting = {
    theme:"dracula",
    fontSize: 14,
    tabSize: 4,
    autoComplete: true,
    lineNumbers: true,
    language: "c_cpp",
    input: ""
}


export const defaultValues=  {
    theme: ["ambiance","chaos","chrome","clouds_midnight","clouds","cobalt","crimson_editor","dawn","dracula","dreamweaver","eclipse","github","gob","monokai","gruvbox","idle_finders","iplastic","katzenmilch","tomorow_night_blus","tomrrow_night"],

}