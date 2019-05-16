import { axios } from "./Axios";
import { USER_DATA_KEY } from "./../store/actions/authActions";

export const PlaceService = {
  addPlace: payload => {
    if (payload.id) {
      console.log("updating");
      return axios
        .put(`places/${payload.id}`, { ...payload })
        .then(res => res.data);
    }
    console.log("adding new");

    return axios.post("places", { ...payload }).then(res => res.data);
  },
  getPlaces: query => {
    let url = "places";
    if (query != null && query !== "") {
      url += `?filter=${query}`;
    }
    return axios.get(url).then(res => res.data || []);
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
