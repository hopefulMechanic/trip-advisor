import React from "react";

const Card = ({ header, children, footer }) => {
  return (
    <div className="card" style={{ marginTop: "2rem" }}>
      {header && <div className="card-header">{header}</div>}
      <div className="card-body d-flex justify-content-center">{children}</div>
      {footer && <div class="card-footer text-muted">{footer}</div>}
    </div>
  );
};

export default Card;
