import { USER_TYPES } from "../constans";

export const AuthService = {
  login: payload => {
    return new Promise((resolve, reject) =>
      resolve({ id: 1, name: "no commercial user", type: USER_TYPES.regular })
    );
  },
  register: payload => {
    return new Promise((resolve, reject) => resolve({ ...payload }));
  }
};
