export const AuthService = {
  login: payload => {
    return new Promise((resolve, reject) =>
      resolve({ id: 1, name: "TEST", type: "normal" })
    );
  },
  register: payload => {
    return new Promise((resolve, reject) =>
      resolve({ name: payload.username, type: "TEST 2" })
    );
  }
};
