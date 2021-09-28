import React from "react";
import { IAceEditorProps } from "react-ace";
import AceEditor from "react-ace";
/**
 * the backend currently support just the languages: python, java and c++
 */
require("ace-builds/src-noconflict/mode-c_cpp");
require("ace-builds/src-noconflict/mode-python");
require("ace-builds/src-noconflict/mode-java");
/**
 * list of theme users can choose from
 */
require("ace-builds/src-noconflict/theme-ambiance");
require("ace-builds/src-noconflict/theme-chaos");
require("ace-builds/src-noconflict/theme-chrome");
require("ace-builds/src-noconflict/theme-clouds");
require("ace-builds/src-noconflict/theme-clouds_midnight");
require("ace-builds/src-noconflict/theme-cobalt");
require("ace-builds/src-noconflict/theme-crimson_editor");
require("ace-builds/src-noconflict/theme-dawn");
require("ace-builds/src-noconflict/theme-dracula");
require("ace-builds/src-noconflict/theme-dreamweaver");
require("ace-builds/src-noconflict/theme-eclipse");
require("ace-builds/src-noconflict/theme-github");
require("ace-builds/src-noconflict/theme-gob");
require("ace-builds/src-noconflict/theme-gruvbox");
require("ace-builds/src-noconflict/theme-idle_fingers");
require("ace-builds/src-noconflict/theme-iplastic");
require("ace-builds/src-noconflict/theme-katzenmilch");
require("ace-builds/src-noconflict/theme-tomorrow_night");
require("ace-builds/src-noconflict/theme-tomorrow_night_blue");
require("ace-builds/src-noconflict/theme-tomorrow_night_eighties");
require("ace-builds/src-noconflict/theme-tomorrow_night");
require("ace-builds/src-noconflict/theme-monokai");
/**
 * code snippets and code autocompletion
 */
require("ace-builds/src-noconflict/ext-language_tools");
require("ace-builds/src-noconflict/snippets/c_cpp");
require("ace-builds/src-noconflict/snippets/javascript");
require("ace-builds/src-noconflict/snippets/java");
require("ace-builds/src-noconflict/snippets/python");

const Editor: React.FC<IAceEditorProps> = ({ ...props }) => {
  return <AceEditor {...props} />;
};

export default Editor;
