import {
  faDownload,
  faCog,
  faExchangeAlt,
  faExpand,
  faExpandAlt,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Tooltip } from "@mui/material";
import React from "react";

const EditorHeader: React.FC = () => {
  return (
    <div className="w-full border p-4 flex flex-row items-center justify-between">
      <select className="w-48  ring-gray-500 focus:outline-none  focus:border-gray-500 focus:ring-gray-500">
        <option> CPP (Gcc 6.3) </option>
        <option> PYTHON (Python 3.6) </option>
        <option> JAVA </option>
      </select>
      <div className="flex flex-row items-center">
        <Tooltip title="Download Code">
          <button className="border w-8 h-8 flex items-center justify-center ml-2">
            <FontAwesomeIcon icon={faDownload} />
          </button>
        </Tooltip>
        <Tooltip title="Open Your Code in Full Screen">
          <button className="border w-8 h-8 flex items-center justify-center ml-2">
            <FontAwesomeIcon icon={faExpandAlt} />
          </button>
        </Tooltip>
        <Tooltip title="Open Editor Setting">
          <button className="border w-8 h-8 flex items-center justify-center ml-2">
            <FontAwesomeIcon icon={faCog} />
          </button>
        </Tooltip>
      </div>
    </div>
  );
};

export default EditorHeader;