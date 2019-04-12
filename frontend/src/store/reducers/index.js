import { combineReducers } from "redux";
import authReducer from "./authReducer.js";
import placeReducer from "./placeReducer.js";

export default combineReducers({
  auth: authReducer,
  place: placeReducer
}, );
