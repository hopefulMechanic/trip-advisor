import { PlaceService } from "../../service/PlaceService";

// ACTIONS TPYES
// LIST
const GET_PLACES = "GET_PLACES";
const GET_PLACES_SUCCESS = "GET_PLACES_SUCCESS";
const GET_PLACES_FAIL = "GET_PLACES_FAIL";
// PLACE
const GET_PLACE = "GET_PLACE";
const GET_PLACE_SUCCESS = "GET_PLACE_SUCCESS";
const GET_PLACE_FAIL = "GET_PLACE_FAIL";
//add
const ADD_PLACE = "ADD_PLACE";
const ADD_PLACE_SUCCESS = "ADD_PLACE_SUCCESS";
const ADD_PLACE_FAIL = "ADD_PLACE_FAIL";

// ACTIONS
// LIST
const getPlacesAction = () => ({
  type: GET_PLACES
});

const getPlaces = () => {
  return dispatch => {
    dispatch(getPlacesAction());
    PlaceService.getPlaces()
      .then(res => {
        console.log("TCL: getPlaces -> res", res);
        dispatch(getPlacesSuccess({ data: res }));
      })
      .catch(err => dispatch(getPlacesFail()));
  };
};

const getPlacesSuccess = data => ({
  type: GET_PLACES_SUCCESS,
  payload: { ...data }
});

const getPlacesFail = () => ({
  type: GET_PLACES_FAIL
});

// PLACE
// GET
const getPlaceAction = () => ({
  type: GET_PLACE
});

const getPlace = id => {
  return dispatch => {
    dispatch(getPlaceAction());
    PlaceService.getPlace(id)
      .then(res => {
        dispatch(getPlaceSuccess({ data: res }));
      })
      .catch(err => dispatch(getPlaceFail()));
  };
};

const getPlaceSuccess = data => ({
  type: GET_PLACE_SUCCESS,
  payload: { ...data }
});

const getPlaceFail = () => ({
  type: GET_PLACE_FAIL
});

// ADD
const addPlaceAction = () => ({
  type: ADD_PLACE
});

const addPlace = (payload, history) => {
  return dispatch => {
    dispatch(addPlaceAction());
    PlaceService.addPlace(payload)
      .then(res => {
        history.push("/places");
        dispatch(addPlaceSuccess());
      })
      .catch(err => dispatch(addPlaceFail()));
  };
};

const addPlaceSuccess = () => ({
  type: ADD_PLACE_SUCCESS
});

const addPlaceFail = () => ({
  type: ADD_PLACE_FAIL
});

export const placeActionTypes = {
  GET_PLACES,
  GET_PLACES_SUCCESS,
  GET_PLACES_FAIL,
  GET_PLACE,
  GET_PLACE_SUCCESS,
  GET_PLACE_FAIL,
  ADD_PLACE,
  ADD_PLACE_SUCCESS,
  ADD_PLACE_FAIL
};
export const placeAction = { getPlaces, getPlace, addPlace };
