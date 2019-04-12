import React from "react";
import "./Loader.scss";

const Loader = ({ size = "small" }) => {
  return (
    <div className={`spinner-border text-dark ${size}`} role="status">
      <span className="sr-only">Loading...</span>
    </div>
  );
};

export default Loader;
