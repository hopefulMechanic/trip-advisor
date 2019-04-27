import { axios } from "./Axios";

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
    return axios.post(`places/${placeId}/comments`, comment);
  }
};
