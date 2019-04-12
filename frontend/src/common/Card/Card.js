import React from "react";

const Card = ({ header, children, footer }) => {
  return (
    <div className="card" style={{ marginTop: "2rem" }}>
      {header && <div className="card-header font-weight-bold">{header}</div>}
      <div className="card-body d-flex justify-content-center flex-column">{children}</div>
      {footer && <div className="card-footer text-muted">{footer}</div>}
    </div>
  );
};

export default Card;
