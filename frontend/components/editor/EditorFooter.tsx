import { faSyncAlt, faTimes } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import Image from "next/image";
import CustomCircularProgress from "./CustomCircularProgress";
import { SubmissionResult } from "../../rest/restClient";

interface Props {
  handleFileSelect: (e: any) => void;
  handleRunClick: () => Promise<SubmissionResult>;
  formik: any;
}

const EditorFooter: React.FC<Props> = ({
  handleFileSelect,
  handleRunClick,
  formik,
}) => {
  const [showOutput, setShowOuput] = React.useState<boolean>(false);
  const [loading, setLoading] = React.useState<boolean>(false);
  const [submissionResult, setSubmissionResult] =
    React.useState<SubmissionResult>();

  const closeOutputPanel = () => {
    setShowOuput(false);
  };

  React.useEffect(() => {
    if (submissionResult) {
      setShowOuput(true);
    }
  }, [submissionResult]);

  const onRunClick = React.useCallback(async () => {
    setLoading(true);
    const res = await handleRunClick();
    setSubmissionResult(res);
    setLoading(false);
    setShowOuput(true);
  }, [handleRunClick]);
  return (
    <div>
      <div className="w-full border p-4 flex flex-row-reverse items-center justify-between">
        <div className="flex flex-row items-center">
          {loading && (
            <div className="flex flex-row items-center">
              <CustomCircularProgress />
              <span className="text-gray-300 ml-2">Running</span>
            </div>
          )}
          <div className="border w-8 h-8 flex items-center justify-center ml-4">
            <FontAwesomeIcon icon={faSyncAlt} />
          </div>
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
          <button
            disabled={loading}
            onClick={onRunClick}
            className={`mx-3 bg-codeChef-btn text-white py-2 text-sm px-5 focus:outline-none focus:ring-2 focus:ring-codeChef-btnFocus hover:bg-gray-500`}
          >
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
            value={formik.values.input}
            onChange={formik.handleChange("input")}
            className="p-0 w-full  ring-gray-500 focus:outline-none  focus:border-gray-500 focus:ring-gray-500"
          ></textarea>
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
                  {submissionResult?.verdict || " "}
                </span>
              </div>
              {/*  date */}
              <div className="py-2 flex-row items-center">
                <span className="font-bold text-codeChef text-md py-2">
                  Date{"   "}
                </span>
                <span className="text-sm text-gray-500">
                  {submissionResult?.date || new Date().toISOString()}
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
                value={submissionResult && submissionResult.output}
                readOnly
                onChange={() => {}}
                className="p-0 w-full  ring-gray-500 focus:outline-none  focus:border-gray-500 focus:ring-gray-500"
              ></textarea>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default EditorFooter;
