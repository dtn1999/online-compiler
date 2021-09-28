import {
  CircularProgressProps,
  CircularProgress,
  circularProgressClasses,
} from "@mui/material";
import { Box } from "@mui/system";
import React from "react";

const CustomCircularProgress: React.FC<CircularProgressProps> = ({
  children,
  ...props
}) => {
  return (
    <Box sx={{ position: "relative" }}>
      <CircularProgress
        variant="determinate"
        sx={{
          color: (theme) =>
            theme.palette.grey[theme.palette.mode === "light" ? 200 : 800],
        }}
        size={24}
        thickness={2}
        {...props}
        value={100}
      />
      <CircularProgress
        variant="indeterminate"
        disableShrink
        sx={{
          color: (theme) =>
            theme.palette.mode === "light" ? "#1a90ff" : "#308fe8",
          animationDuration: "550ms",
          position: "absolute",
          left: 0,
          [`& .${circularProgressClasses.circle}`]: {
            strokeLinecap: "round",
          },
        }}
        size={24}
        thickness={4}
        {...props}
      />
    </Box>
  );
};

export default CustomCircularProgress;
