import { faSyncAlt } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";

const EditorFooter: React.FC = () => {
  return (
    <>
      <div className="w-full border p-4 flex flex-row-reverse items-center justify-between">
        <div className="border w-8 h-8 flex items-center justify-center">
          <FontAwesomeIcon icon={faSyncAlt} />
        </div>
      </div>
      {/* buttons to handle submission and file upload */}
      <div className="py-5 w-full flex flex-row justify-between items-center">
        <button className="bg-codeChef-btn text-white py-2 text-sm px-5 focus:outline-none focus:ring-2 focus:ring-codeChef-btnFocus hover:bg-gray-500">
          Open File
        </button>
        <div className="flex flex-row">
          <button className="mx-3 bg-codeChef-btn text-white py-2 text-sm px-5 focus:outline-none focus:ring-2 focus:ring-codeChef-btnFocus hover:bg-gray-500">
            run
          </button>
        </div>
      </div>
      {/*  input for the program  */}
      <div className="flex flex-col w-full mt-2">
        <div className="w-full">
          <p className="text-codeChef py-2"> Custom Input </p>
        </div>
        <div className="w-full">
          <textarea
            rows={5}
            className="p-0 w-full  ring-gray-500 focus:outline-none  focus:border-gray-500 focus:ring-gray-500"
          >
            {" "}
          </textarea>
        </div>
      </div>
    </>
  );
};

export default EditorFooter;
