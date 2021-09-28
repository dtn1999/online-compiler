import { faBook } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";

const ProgramInformation: React.FC = () => {
  return (
    <div className="border p-4 flex flex-row items-center justify-between">
      <p className="font-bold text-codeChef capitalize">
        Problem Code: <span className="text-codeChef-blue"> QUALPREL </span>
      </p>
      <div className="border w-8 h-8 flex items-center justify-center">
        <FontAwesomeIcon icon={faBook} />
      </div>
    </div>
  );
};

export default ProgramInformation;
