import { axios } from "./Axios";

// export const PlaceService = {
//   addPlace: payload => {
//     return axios.post("places", { ...payload });
//   },
//   getPlaces: () => {
//     return axios.get("places").then(res => console.log(res));
//   },
//   getPlace: id => {
//     return axios.get(`places/${id}`);
//   }
// };
export const PlaceService = {
  addPlace: payload => {
    return new Promise((resolve, reject) => resolve());
  },
  getPlaces: () => {
    return new Promise((resolve, reject) =>
      resolve([
        {
          id: 1,
          name: "commece place",
          description:
            "nasz hotel , nasz hotelnasz hotelnasz hotelnasz hotelnasz hotelnasz hotelnasz hotel",
          address: {
            line: "ulica1",
            city: "krzeszo",
            postalCode: "12345",
            country: "polska"
          },
          entranceFee: 12.12,
          email: "asd@qwe.pl",
          phone: "+123123123",
          categories: ["hotel", "food", "5star", "cat1", "parking"],
          comments: [
            {
              id: 1,
              user: {
                name: "Kamil"
              },
              content: "nie podoba mi sie",
              rate: 2
            }
          ],
          owner: {
            name: "City Hotel"
          }
        },
        {
          id: 2,
          name: "public place",
          description: "nasz hotel",
          address: {
            line: "ulica1",
            city: "krzeszo",
            postalCode: "12345",
            country: "polska"
          },
          entranceFee: 0.0,
          email: "asd@qwe.pl",
          phone: "+123123123",
          comments: [],
          categories: ["food", "5star", "cat1", "parking"],
          owner: {
            id: 1,
            name: "Gima krakow"
          }
        }
      ])
    );
  },
  getPlace: id => {
    return PlaceService.getPlaces().then(list => {
      return list
        .filter(el => +el.id === +id)
        .reduce((prev, curr) => ({ ...curr }), {});
    });
  }
};
