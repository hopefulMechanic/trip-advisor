import React from "react";
import { CATEGORIES_NAMES } from "../../constans";

const CategoryBadge = ({ category }) => {
  const bages = {
    [CATEGORIES_NAMES.hotel]: (
      <span className="badge badge-primary mr-1">{category}</span>
    ),
    [CATEGORIES_NAMES.food]: (
      <span className="badge badge-secondary mr-1">{category}</span>
    ),
    [CATEGORIES_NAMES["5star"]]: (
      <span className="badge badge-success mr-1">{category}</span>
    ),
    [CATEGORIES_NAMES.parking]: (
      <span className="badge badge-danger mr-1">{category}</span>
    ),
    [CATEGORIES_NAMES.cat1]: (
      <span className="badge badge-warning mr-1">{category}</span>
    )
  };

  return bages[category];
};

export default CategoryBadge;
