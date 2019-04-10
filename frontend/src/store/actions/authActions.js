import { AuthService } from "../../service/AuthService";
import $ from "jquery";

// login actions
const LOGIN = "LOGIN";
const LOGIN_SUCCESS = "LOGIN_SUCCESS";
const LOGIN_FAIL = "LOGIN_FAIL";
const IS_LOGGED = "IS_LOGGED";

// logout action
const LOGOUT = "LOGOUT";

// register actions
const REGISTER = "REGISTER";
const REGISTER_SUCCESS = "REGISTER_SUCCESS";
const REGISTER_FAIL = "REGISTER_FAIL";

const USER_DATA_KEY = "USER_DATA";

// login actions

const loginAction = () => ({
  type: LOGIN
});

const login = payload => {
  return dispatch => {
    dispatch(loginAction());
    AuthService.login(payload)
      .then(res => {
        $("#loginModal").modal("hide");
        localStorage.setItem(USER_DATA_KEY, JSON.stringify(res));
        dispatch(loginSuccess({ data: res }));
      })
      .catch(err => dispatch(loginFail()));
  };
};

const loginSuccess = data => ({
  type: LOGIN_SUCCESS,
  payload: { ...data }
});

const loginFail = data => ({
  type: LOGIN_FAIL
});

const isLogged = () => {
  const el = localStorage.getItem(USER_DATA_KEY);
  let payload = { isAuthenticated: false, data: null };
  if (el != null) {
    payload = { isAuthenticated: true, data: JSON.parse(el) };
  }
  return {
    type: IS_LOGGED,
    payload: { ...payload }
  };
};

// logout actions
const logout = () => {
  localStorage.removeItem(USER_DATA_KEY);
  return {
    type: LOGOUT
  };
};

// register actions
const registerAction = () => ({
  type: REGISTER
});

const register = payload => {
  return dispatch => {
    dispatch(registerAction());
    AuthService.register(payload).then(res => {
      $("#loginModal").modal("hide");
      dispatch(registerSuccess({ data: res }));
    });
  };
};

const registerSuccess = data => ({
  type: REGISTER_SUCCESS,
  payload: { ...data }
});

export const authActionTypes = {
  LOGIN,
  LOGIN_SUCCESS,
  LOGIN_FAIL,
  IS_LOGGED,
  LOGOUT,
  REGISTER,
  REGISTER_SUCCESS,
  REGISTER_FAIL
};
export const authAction = { login, register, logout, isLogged };
