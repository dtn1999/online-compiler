import * as React from "react";
import type { NextPage } from "next";
import { IAceEditorProps } from "react-ace";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import EditorHeader from "../components/editor/EditorHeader";
import EditorFooter from "../components/editor/EditorFooter";
import ProgramInformation from "../components/ProgramInformation";
import dynamic from "next/dynamic";

const Editor = dynamic(
  () => {
    return import("../components/editor/Editor");
  },
  { ssr: false }
);
const Home: NextPage = () => {
  const [code, setCode] = React.useState<string>("");

  return (
    <div className="px-40 pt-6 pb-10 bg-gray-100  w-full max-h-full flex flex-col items-center">
      <div className="bg-white shadow-2xl pt-16 px-20 min-h-full pb-10 w-3/4 h-full flex flex-col">
        <ProgramInformation />
        <div className="mt-5 flex-1">
          <EditorHeader />
          <Editor
            width="100%"
            mode="c_cpp"
            theme="tomorrow_night"
            name="blah2"
            onChange={setCode}
            fontSize={14}
            showPrintMargin={true}
            showGutter={true}
            highlightActiveLine={true}
            value={code}
            setOptions={{
              enableBasicAutocompletion: true,
              enableLiveAutocompletion: true,
              enableSnippets: true,
              showLineNumbers: true,
              tabSize: 2,
            }}
          />
          <EditorFooter />
        </div>
      </div>
    </div>
  );
};

export default Home;
