import { placeActionTypes } from "../actions";

const initState = {
  list: [],
  selected: null,
  loading: false
};

const placeReducer = (state = initState, action) => {
  switch (action.type) {
    default:
      return state;
  }
};

export default placeReducer;
