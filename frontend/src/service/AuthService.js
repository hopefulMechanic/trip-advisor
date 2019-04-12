import { USER_TYPES } from "../constans";
import { axios } from "./Axios";

export const AuthService = {
  login: payload => {
    return new Promise((resolve, reject) =>
      resolve({ id: 1, name: "TEST", type: USER_TYPES.regular })
    );
  },
  register: payload => {
    return new Promise((resolve, reject) =>
      resolve({ name: payload.username, type: "TEST 2" })
    );
  }
};
