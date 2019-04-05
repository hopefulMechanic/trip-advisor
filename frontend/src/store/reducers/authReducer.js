import { authActionTypes } from "../actions";

const initState = {
  isAuthenticated: false,
  user: {},
  loading: false
};

const authReducer = (state = initState, action) => {
  switch (action.type) {
    case authActionTypes.REGISTER:
    case authActionTypes.LOGIN: {
      return { ...state, loading: true };
    }
    case authActionTypes.REGISTER_SUCCESS:
    case authActionTypes.LOGIN_SUCCESS: {
      return {
        ...state,
        loading: false,
        isAuthenticated: true,
        user: action.payload.data
      };
    }

    case authActionTypes.IS_LOGGED: {
      return {
        ...state,
        loading: false,
        isAuthenticated: action.payload.isAuthenticated,
        user: action.payload.data
      };
    }
    case authActionTypes.LOGOUT:
    case authActionTypes.LOGIN_FAIL: {
      return initState;
    }
    default:
      return state;
  }
};

export default authReducer;
