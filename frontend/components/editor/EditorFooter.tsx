import { faSyncAlt, faTimes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";

interface Props {
  handleFileSelect: (e: any) => void;
}
const EditorFooter: React.FC<Props> = ({ handleFileSelect }) => {
  const [showOutput, setShowOuput] = React.useState<boolean>(true);
  const closeOutputPanel = () => {
    setShowOuput(false);
  };

  return (
    <div>
      <div className="w-full border p-4 flex flex-row-reverse items-center justify-between">
        <div className="border w-8 h-8 flex items-center justify-center">
          <FontAwesomeIcon icon={faSyncAlt} />
        </div>
      </div>
      {/* buttons to handle submission and file upload */}
      <div className="py-5 w-full flex flex-row justify-between items-center">
        <span className="relative bg-codeChef-btn text-white py-2 text-sm px-5 focus:outline-none focus:ring-2 focus:ring-codeChef-btnFocus hover:bg-gray-500 cursor-pointer">
          Open File
          <input
            type="file"
            onChange={handleFileSelect}
            className="absolute inset-0 text-right opacity-0 outline-none cursor-pointer block text-transparent filter-none "
          />
        </span>
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
            value={""}
            onChange={() => {}}
            className="p-0 w-full  ring-gray-500 focus:outline-none  focus:border-gray-500 focus:ring-gray-500"
          >
            {" "}
          </textarea>
        </div>
        {/*  output for the program  */}
        {showOutput && (
          <div className="flex border flex-col w-full mt-2">
            <div className="w-full bg-codeChef-bgOutPut border  p-4 flex flex-row items-center justify-between">
              {/*  status  */}
              <div className="py-2 flex-row items-center">
                <span className="font-bold text-codeChef text-md py-2">
                  Status{"   "}
                </span>
                <span className="text-sm text-gray-500">
                  Time limit exceeded
                </span>
              </div>
              {/*  date */}
              <div className="py-2 flex-row items-center">
                <span className="font-bold text-codeChef text-md py-2">
                  Date{"   "}
                </span>
                <span className="text-sm text-gray-500">
                  {new Date().toISOString()}
                </span>
              </div>
              {/*  Execution time  */}
              <div className="py-2 flex-row items-center">
                <span className="font-bold text-codeChef text-md py-2">
                  Time{"   "}
                </span>
                <span className="text-sm text-gray-500">5 sec</span>
              </div>
              {/*  Memory usage  */}
              <div className="py-2 flex-row items-center">
                <span className="font-bold text-codeChef text-md py-2">
                  Mem{"   "}
                </span>
                <span className="text-sm text-gray-500">15.232kB</span>
              </div>
              <button
                onClick={closeOutputPanel}
                className="border w-8 h-8 flex items-center justify-center ml-10"
              >
                <FontAwesomeIcon icon={faTimes} />
              </button>
            </div>
            <div className="w-full p-4">
              <div className="w-full">
                <p className="text-codeChef py-2"> Output </p>
              </div>
              <textarea
                rows={5}
                value={""}
                onChange={() => {}}
                className="p-0 w-full  ring-gray-500 focus:outline-none  focus:border-gray-500 focus:ring-gray-500"
              >
                {" "}
              </textarea>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default EditorFooter;
