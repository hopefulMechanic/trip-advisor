import React from "react";

const Collection = ({ list, onDoubleClick }) => {
  return (
    <ul class="list-group w-100">
      {list.map(el => (
        <button
          type="button"
          key={el.id}
          class="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
          onClick={() => onDoubleClick && onDoubleClick(el.id)}
        >
          {el.content}
          <span class="badge badge-primary badge-pill">2</span>
        </button>
      ))}
    </ul>
  );
};

export default Collection;
