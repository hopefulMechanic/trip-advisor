import { authActionTypes } from "../actions";

const initState = {
  isAuthenticated: false,
  user: {},
  loading: false,
  wrongCredetials: false
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
        wrongCredetials: false,
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
    case authActionTypes.LOGIN_FAIL: {
      return {
        ...state,
        loading: false,
        isAutheticated: false,
        wrongCredetials: true,
        user: {}
      };
    }
    case authActionTypes.LOGOUT: {
      return initState;
    }
    default:
      return state;
  }
};

export default authReducer;
