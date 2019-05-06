import { axios } from "./Axios";
import { USER_DATA_KEY } from "./../store/actions/authActions";

export const PlaceService = {
  addPlace: payload => {
    return axios.post("places", { ...payload }).then(res => res.data);
  },
  getPlaces: () => {
    return axios.get("places").then(res => res.data || []);
  },
  getPlace: id => {
    return axios.get(`places/${id}`).then(res => res.data);
  },
  addComment: (placeId, comment) => {
    const el = localStorage.getItem(USER_DATA_KEY);
    const user = JSON.parse(el);
    return axios.post(`places/${placeId}/comments/user/${user.id}`, comment);
  },
  deleteComment: commentId => {
    return axios.delete(`comments/${commentId}`);
  }
};
