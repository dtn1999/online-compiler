import React from "react";
import { IAceEditorProps } from "react-ace";

const Editor: React.FC<IAceEditorProps> = ({ children, ...props }) => {
  if (typeof window !== "undefined") {
    const AceEditor = require("react-ace").default;
    require("ace-builds/src-noconflict/mode-c_cpp");
    require("ace-builds/src-noconflict/theme-tomorrow_night");
    require("ace-builds/src-noconflict/ext-language_tools");
    require("ace-builds/src-noconflict/snippets/c_cpp");

    return <AceEditor {...props} />;
  }

  return null;
};

export default Editor;
