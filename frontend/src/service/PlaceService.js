export const PlaceService = {
  getPlaces: () => {
    return new Promise((resolve, reject) =>
      resolve([
        { id: 1, name: "test1" },
        { id: 2, name: "test2" },
        { id: 3, name: "test3" },
        { id: 4, name: "test4" }
      ])
    );
  },
  getPlace: id => {
    return new Promise((resolve, reject) => resolve({ id: 1, name: "test1" }));
  }
};
