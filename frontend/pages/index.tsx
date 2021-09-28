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
import { useFormik, useFormikContext } from "formik";
import { Update } from "@mui/icons-material";

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

  const UpdateEditorState = React.useCallback((values: UserEditorSetting) => {
    console.log(values);
    setEditorSettings({ ...values });
  }, []);

  return (
    <div className="px-40 pt-6 pb-10 bg-gray-100  w-full max-h-full flex flex-col items-center">
      <div className="bg-white shadow-2xl pt-16 px-20 min-h-full pb-10 w-3/4 h-full flex flex-col">
        <ProgramInformation />
        <div className="mt-5 flex-1">
          <EditorHeader updateEditorState={UpdateEditorState} />
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
          <EditorFooter />
        </div>
      </div>
    </div>
  );
};

export default Home;
