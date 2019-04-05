export const LoginService = {
  isAutheticated: () => {
    return new Promise((resolve, reject) => resolve(false));
  },
  login: payload => {
    return new Promise((resolve, reject) =>
      resolve({ name: "Dawid", type: "TEST" })
    );
  },
  register: payload => {
    return new Promise((resolve, reject) =>
      resolve({ name: payload.username, type: "TEST 2" })
    );
  }
};
