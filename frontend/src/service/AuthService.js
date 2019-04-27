import { axios } from "./Axios";

export const AuthService = {
  login: payload => {
    return axios.post("auth/login", payload).then(res => res.data);
  },
  register: payload => {
    return axios.post("auth/register", payload).then(res => res.data);
  }
};
