import { combineReducers } from "redux";
import authReducer from "./AuthReducer.js";
import placeReducer from "./PlaceReducer.js";

export default combineReducers({
  auth: authReducer,
  place: placeReducer
}, );
