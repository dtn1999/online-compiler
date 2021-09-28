import * as React from "react";
import type { NextPage } from "next";
import EditorHeader from "../components/editor/EditorHeader";
import EditorFooter from "../components/editor/EditorFooter";
import ProgramInformation from "../components/ProgramInformation";
import dynamic from "next/dynamic";
import {
  UserEditorSetting,
  userEditorSettingInitialValues,
} from "../components/editor/editorConfig";
import Dropzone from "react-dropzone";
import { RestClient, SubmissionResult } from "../rest/restClient";
import { useFormik } from "formik";

const Editor = dynamic(
  () => {
    return import("../components/editor/Editor");
  },
  { ssr: false }
);
const Home: NextPage = () => {
  const [code, setCode] = React.useState<string>("");

  const [editorSettings, setEditorSettings] = React.useState<UserEditorSetting>(
    userEditorSettingInitialValues
  );

  const formik = useFormik<UserEditorSetting>({
    initialValues: { ...userEditorSettingInitialValues },
    onSubmit: (values) => {
      console.log(values);
    },
  });

  // handles
  const UpdateEditorState = React.useCallback(
    (values: UserEditorSetting) => {
      setEditorSettings({ ...formik.values });
      console.log(formik.values);
    },
    [formik.values]
  );

  React.useEffect(() => {
    setEditorSettings({ ...formik.values });
    console.log("formik values change to ", formik.values);
  }, [formik.values]);

  // reat the file and set it to the code state
  const handleFileLoad = (ev: ProgressEvent<FileReader>) => {
    setCode(ev.target?.result?.toString() || "");
  };

  // handler for  file upload through the button
  const handleFileSelect = React.useCallback((event: any) => {
    console.log("event");
    const reader = new FileReader();
    reader.onload = handleFileLoad;
    reader.readAsText(event.target.files[0]);
  }, []);

  // handler for file droping on the editor
  const handleDrop = React.useCallback(async (acceptedFiles) => {
    const res = await acceptedFiles[0].text();
    setCode(res);
  }, []);

  // submit the code and get execution result
  const submitCode = React.useCallback(async () => {
    const result = await RestClient.submitCode(
      code,
      formik.values.input,
      editorSettings.language
    );

    return {
      ...result,
    };
  }, [code, editorSettings, formik.values.input]);

  return (
    <div className="px-40 pt-6 pb-10 bg-gray-100  w-full max-h-full flex flex-col items-center">
      <div className="bg-white shadow-2xl pt-16 px-20 min-h-full pb-10 w-3/4 h-full flex flex-col">
        <ProgramInformation />
        <div className="mt-5 flex-1">
          <EditorHeader formik={formik} updateEditorState={UpdateEditorState} />
          <Dropzone onDrop={handleDrop}>
            {({ getRootProps, isDragActive }) => (
              <div
                className={`${
                  isDragActive ? "border-4 border-dashed border-red-700" : ""
                }`}
                {...getRootProps()}
              >
                <Editor
                  width="100%"
                  mode={editorSettings.language}
                  theme={editorSettings.theme}
                  name="blah2"
                  onChange={setCode}
                  fontSize={editorSettings.fontSize}
                  showPrintMargin={true}
                  showGutter={true}
                  highlightActiveLine={true}
                  value={code}
                  setOptions={{
                    enableBasicAutocompletion: false,
                    enableLiveAutocompletion: true,
                    enableSnippets: true,
                    showLineNumbers: true,
                    tabSize: editorSettings.tabSize,
                  }}
                />
              </div>
            )}
          </Dropzone>

          <EditorFooter
            handleFileSelect={handleFileSelect}
            handleRunClick={submitCode}
            formik={formik}
          />
        </div>
      </div>
    </div>
  );
};

export default Home;
