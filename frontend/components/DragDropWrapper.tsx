import { BoxTypeMap } from "@mui/material";
import { OverridableComponent } from "@mui/material/OverridableComponent";
import { borderColor, Box, BoxProps } from "@mui/system";
import React from "react";

interface Props {
  hanldeDrop: (e: DragEvent) => void;
}

const DragDropWrapper: React.FC<Props> = ({ hanldeDrop, children }) => {
  //
  const [dragging, setDragging] = React.useState<boolean>(false);
  const [dragCounter, setDragCounter] = React.useState<number>(0);

  const dropRef = React.useRef<HTMLDivElement>();

  //
  const handleDragEnter = React.useCallback((e: DragEvent) => {
    e.preventDefault();
    e.stopPropagation();
    setDragCounter(dragCounter + 1);
    if (e.dataTransfer?.items && e.dataTransfer.items.length > 0) {
      setDragging(true);
    }
  }, []);
  const handleDragOver = React.useCallback((e: DragEvent) => {
    e.preventDefault();
    e.stopPropagation();
  }, []);
  const handleDragLeave = React.useCallback((e: DragEvent) => {
    e.preventDefault();
    e.stopPropagation();
    setDragCounter(dragCounter - 1);
    if (dragCounter > 0) return;
    setDragging(false);
  }, []);
  const handleDragDrop = React.useCallback(async (e: DragEvent) => {
    e.preventDefault();
    e.stopPropagation();
    setDragging(false);
    console.log("handle drop ", await e.dataTransfer?.files[0]?.arrayBuffer());
    if (e.dataTransfer?.files && e.dataTransfer.files.length > 0) {
      hanldeDrop(e);
      e.dataTransfer.clearData();
      setDragCounter(0);
    }
  }, []);

  React.useEffect(() => {
    const box = dropRef.current;
    box?.addEventListener("dragenter", handleDragEnter);
    box?.addEventListener("dragover", handleDragOver);
    box?.addEventListener("dragleave", handleDragLeave);
    box?.addEventListener("drop", handleDragDrop);

    return () => {
      box?.removeEventListener("dragenter", handleDragEnter);
      box?.removeEventListener("dragover", handleDragOver);
      box?.removeEventListener("dragleave", handleDragLeave);
      box?.removeEventListener("drop", handleDragDrop);
    };
  }, []);
  return (
    <Box ref={dropRef} sx={{ position: "relative" }}>
      {dragging && (
        <div className="flex items-center justify-center border-4 border-dashed border-red-600 absolute inset-0 z-50">
          <div className="text-center text-3xl text-red-500 inset-x-0">
            <div>drop here 🙂 </div>
          </div>
        </div>
      )}
      {children}
    </Box>
  );
};

export default DragDropWrapper;
