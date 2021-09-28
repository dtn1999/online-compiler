import { ContactlessOutlined } from "@mui/icons-material";
import { Menu } from "@mui/material";
import React, { MouseEventHandler } from "react";
import { defaultValues } from "./editorConfig";

interface Props {
  anchorEl: Element | ((element: Element) => Element) | null | undefined;
  handleClose: (event: {}, reason: "backdropClick" | "escapeKeyDown") => void;
  handleClick: MouseEventHandler<HTMLDivElement>;
  open: boolean;
  formik: any;
}

const EditeurMenu: React.FC<Props> = ({
  anchorEl,
  handleClose,
  handleClick,
  open,
  formik,
}) => {
  return (
    <Menu
      anchorEl={anchorEl}
      open={open}
      onClose={handleClose}
      onClick={handleClick}
      PaperProps={{
        elevation: 0,
        sx: {
          width: "250px",
          overflow: "visible",
          filter: "drop-shadow(0px 2px 8px rgba(0,0,0,0.32))",
          mt: 1.5,
          "& .MuiAvatar-root": {
            width: 32,
            height: 32,
            ml: -0.5,
            mr: 1,
          },
          "&:before": {
            content: '""',
            display: "block",
            position: "absolute",
            top: 0,
            right: 14,
            width: 10,
            height: 10,
            bgcolor: "background.paper",
            transform: "translateY(-50%) rotate(45deg)",
            zIndex: 0,
          },
        },
      }}
      transformOrigin={{ horizontal: "right", vertical: "top" }}
      anchorOrigin={{ horizontal: "right", vertical: "bottom" }}
    >
      <div className="w-full h-full p-2">
        <div className="w-full py-1">
          <p className="font-bold text-codeChef text-md py-2"> Theme </p>
          <select onChange={formik.handleChange("theme")} className="w-full">
            {defaultValues.theme.map((theme) => (
              <option key={theme} value={theme}>
                {theme}
              </option>
            ))}
          </select>
        </div>
        <div className="w-full py-1">
          <p className="font-bold text-codeChef text-md py-2"> Tab Size </p>
          <input
            onChange={formik.handleChange("tabSize")}
            value={formik.values.tabSize}
            className="w-full"
            type="number"
            min={0}
          />
        </div>
        <div className="w-full py-1">
          <p className="font-bold text-codeChef text-md py-2"> Font Size </p>
          <input
            onChange={formik.handleChange("fontSize")}
            value={formik.values.fontSize}
            className="w-full"
            type="number"
            min={0}
          />
        </div>
      </div>
    </Menu>
  );
};

export default EditeurMenu;
