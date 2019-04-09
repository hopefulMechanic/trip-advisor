import React from "react";

const Collection = ({ list, onDoubleClick }) => {
  return (
    <ul class="list-group w-100">
      {list.map(el => (
        <li
          key={el.id}
          class="list-group-item"
          onClick={() => onDoubleClick && onDoubleClick(el.id)}
        >
          {el.content}
        </li>
      ))}
    </ul>
  );
};

export default Collection;
