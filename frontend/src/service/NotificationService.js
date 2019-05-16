import { axios } from "./Axios";

export const NotificationService = {
  addSubscripiton: (userId, placeId) =>
    axios.post(`notification/${placeId}/${userId}`).then(res => res.data),
  removeSubscription: (userId, placeId) =>
    axios
      .delete(`notification/${placeId}/${userId}`)
      .then(res => res.data),
  notifyObservers: (placeId, message) =>
    axios
      .post(`notification/notify/${placeId}`, message, {
        headers: { "Content-Type": "text/plain" }
      })
      .then(res => res.data),
  retrieveMessages: userId =>
    axios.get(`notification/${userId}`).then(res => res.data),
  checkIfIsObserver: (placeId, userId) =>
    axios.get(`notification/check/${placeId}/${userId}`).then(res => res.data)
};
