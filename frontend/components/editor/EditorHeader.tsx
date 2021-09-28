import {
  faDownload,
  faCog,
  faExpandAlt,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Tooltip } from "@mui/material";
import EditorMenu from "./EditeurMenu";
import React from "react";
import { useFormik } from "formik";
import {
  UserEditorSetting,
  userEditorSettingInitialValues,
} from "./editorConfig";

interface Props {
  updateEditorState: (values: UserEditorSetting) => void;
  formik: any;
}

const EditorHeader: React.FC<Props> = ({ updateEditorState, formik }) => {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [open, setOpen] = React.useState<boolean>(Boolean(anchorEl));
  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    if (!anchorEl) {
      setAnchorEl(event.currentTarget);
      setOpen(true);
    }
  };
  const handleClose = () => {
    setAnchorEl(null);
    setOpen(false);
  };

  return (
    <div className="w-full border p-4 flex flex-row items-center justify-between">
      <select
        onChange={formik.handleChange("language")}
        value={formik.values.language}
        className="w-48  ring-gray-500 focus:outline-none  focus:border-gray-500 focus:ring-gray-500"
      >
        <option value="c_cpp"> CPP (Gcc 6.3) </option>
        <option value="python"> PYTHON (Python 3.6) </option>
        <option value="java"> JAVA </option>
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
          <button
            onClick={handleClick}
            className="border w-8 h-8 flex items-center justify-center ml-2"
          >
            <FontAwesomeIcon icon={faCog} />
          </button>
        </Tooltip>
        <EditorMenu
          formik={formik}
          open={open}
          handleClose={handleClose}
          handleClick={handleClick}
          anchorEl={anchorEl}
        />
      </div>
    </div>
  );
};

export default EditorHeader;
