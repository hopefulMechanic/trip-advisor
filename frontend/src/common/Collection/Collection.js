import React from "react";

const Collection = ({ list = [], onDoubleClick }) => {
  return (
    <div className="list-group w-100">
      {(list.length > 0 &&
        list.map(el => (
          <button
            type="button"
            key={el.id || el}
            className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
            onClick={() => onDoubleClick && onDoubleClick(el.id)}
          >
            {el.content}
          </button>
        ))) || (
        <button
          type="button"
          className="list-group-item list-group-item-action"
          disabled
        >
          List is Empty!
        </button>
      )}
    </div>
  );
};

export default Collection;
