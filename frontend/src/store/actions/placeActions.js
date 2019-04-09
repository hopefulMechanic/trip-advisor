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

export const placeActionTypes = {
  GET_PLACES,
  GET_PLACES_SUCCESS,
  GET_PLACES_FAIL,
  GET_PLACE,
  GET_PLACE_SUCCESS,
  GET_PLACE_FAIL
};
export const placeAction = { getPlaces, getPlace };
