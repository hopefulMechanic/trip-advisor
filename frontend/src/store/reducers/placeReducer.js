import { placeActionTypes } from "../actions";

const initState = {
  list: [],
  selected: null,
  loading: false
};

const placeReducer = (state = initState, action) => {
  switch (action.type) {
    case placeActionTypes.GET_PLACE:
    case placeActionTypes.GET_PLACES:
    case placeActionTypes.ADD_PLACES:
      return { ...state, loading: true };
    case placeActionTypes.GET_PLACES_SUCCESS:
      return { ...state, list: action.payload.data, loading: false };
    case placeActionTypes.GET_PLACE_SUCCESS:
      return { ...state, selected: action.payload.data, loading: false };
    case placeActionTypes.ADD_PLACE_SUCCESS:
    case placeActionTypes.ADD_PLACE_FAIL:
    case placeActionTypes.GET_PLACE_FAIL:
    case placeActionTypes.GET_PLACES_FAIL:
      return { ...state, loading: false };
    default:
      return state;
  }
};

export default placeReducer;
