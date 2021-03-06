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

//add COMMENT
const ADD_COMMENT = "ADD_COMMENT";
const ADD_COMMENT_SUCCESS = "ADD_COMMENT_SUCCESS";
const ADD_COMMENT_FAIL = "ADD_COMMENT_FAIL";

//delete COMMENT
const DELETE_COMMENT = "DELETE_COMMENT";
const DELETE_COMMENT_SUCCESS = "DELETE_COMMENT_SUCCESS";
const DELETE_COMMENT_FAIL = "DELETE_COMMENT_FAIL";

// ACTIONS
// LIST
const getPlacesAction = () => ({
  type: GET_PLACES
});

const getPlaces = (query) => {
  return dispatch => {
    dispatch(getPlacesAction());
    PlaceService.getPlaces(query)
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
// Comment
const addCommentAction = () => ({
  type: ADD_COMMENT
});

const addComment = (id, comment) => {
  return dispatch => {
    dispatch(addCommentAction());
    PlaceService.addComment(id, comment)
      .then(res => {
        PlaceService.getPlace(id).then(res => {
          dispatch(getPlaceSuccess({ data: res }));
          dispatch(addCommentSuccess());
        });
      })
      .catch(err => dispatch(addCommentFail()));
  };
};

const addCommentSuccess = () => ({
  type: ADD_COMMENT_SUCCESS
});

const addCommentFail = () => ({
  type: ADD_COMMENT_FAIL
});

const deleteCommentAction = () => ({
  type: DELETE_COMMENT
});

const deleteComment = (commentId, placeId) => {
  return dispatch => {
    dispatch(deleteCommentAction());
    PlaceService.deleteComment(commentId)
      .then(res => {
        PlaceService.getPlace(placeId).then(res => {
          dispatch(getPlaceSuccess({ data: res }));
          dispatch(deleteCommenSuccess());
        });
      })
      .catch(err => dispatch(deleteCommenFail()));
  };
};

const deleteCommenSuccess = () => ({
  type: DELETE_COMMENT_SUCCESS
});

const deleteCommenFail = () => ({
  type: DELETE_COMMENT_FAIL
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
  ADD_PLACE_FAIL,
  ADD_COMMENT,
  ADD_COMMENT_SUCCESS,
  ADD_COMMENT_FAIL
};
export const placeAction = {
  getPlaces,
  getPlace,
  addPlace,
  addComment,
  deleteComment
};
