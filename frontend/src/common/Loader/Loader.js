import React from "react";
import "./Loader.scss";
const Loader = () => {
  return (
    <div className="Loader d-flex justify-content-center m-5">
      <div className="lds-dual-ring" />
    </div>
  );
};

export default Loader;
