import { axios } from "./Axios";

export const NotificiationService = {
  addSubscripiton: (userId, palceId) => {
    return axios
      .post(`notification/add/${palceId}/${userId}`)
      .then(res => res.data);
  },
  removeSubscription: (userId, palceId) => {
    return axios
      .delete(`notification/remove/${palceId}/${userId}`)
      .then(res => res.data);
  }
};
