import React from "react";
import { CATEGORIES_NAMES } from "../../constans";

const CategoryBadge = ({ category }) => {
  const bages = {
    [CATEGORIES_NAMES.hotel]: (
      <span class="badge badge-primary mr-1">{category}</span>
    ),
    [CATEGORIES_NAMES.food]: (
      <span class="badge badge-secondary mr-1">{category}</span>
    ),
    [CATEGORIES_NAMES["5star"]]: (
      <span class="badge badge-success mr-1">{category}</span>
    ),
    [CATEGORIES_NAMES.parking]: (
      <span class="badge badge-danger mr-1">{category}</span>
    ),
    [CATEGORIES_NAMES.cat1]: (
      <span class="badge badge-warning mr-1">{category}</span>
    )
  };

  return bages[category];
};

export default CategoryBadge;
