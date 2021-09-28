import React from "react";
import { IAceEditorProps } from "react-ace";
import AceEditor from "react-ace";
require("ace-builds/src-noconflict/mode-c_cpp");
require("ace-builds/src-noconflict/theme-tomorrow_night");
require("ace-builds/src-noconflict/ext-language_tools");
require("ace-builds/src-noconflict/snippets/c_cpp");

const Editor: React.FC<IAceEditorProps> = ({ ...props }) => {
  return <AceEditor {...props} />;
};

export default Editor;
