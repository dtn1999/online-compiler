import { PersonAdd, Settings, Logout } from "@mui/icons-material";
import { Menu, MenuItem, Avatar, Divider, ListItemIcon } from "@mui/material";
import React from "react";

interface Props {
  anchorEl: Element | ((element: Element) => Element) | null | undefined;
  handleClose: () => void;
  open: boolean;
}

const EditeurMenu: React.FC<Props> = ({ anchorEl, handleClose, open }) => {
  return (
    <Menu
      anchorEl={anchorEl}
      open={open}
      onClose={handleClose}
      onClick={handleClose}
      PaperProps={{
        elevation: 0,
        sx: {
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
      <div>
        <p> Theme </p>
        <select>
          <option> theme 1</option>
          <option> theme 1</option>
          <option> theme 1</option>
          <option> theme 1</option>
          <option> theme 1</option>
          <option> theme 1</option>
        </select>
      </div>
      <div>
        <p> Tab Size </p>
        <input type="number" name="" id="" />
      </div>
      <div>
        <p> Font Size </p>
        <input type="number" name="" id="" />
      </div>
      <div>
        <input type="checkbox" name="" id="" />
        <span> Enable Autocomplete </span>
      </div>
      <div>
        <input type="checkbox" name="" id="" />
        <span> Show Line Numbers </span>
      </div>
    </Menu>
  );
};

export default EditeurMenu;
