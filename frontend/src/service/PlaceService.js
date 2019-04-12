import { axios } from "./Axios";

export const PlaceService = {
  addPlace: payload => {
    return axios.post("places", { ...payload });
  },
  getPlaces: () => {
    return axios.get("places").then(res => console.log(res));
  },
  getPlace: id => {
    return axios.get(`places/${id}`);
  }
};
