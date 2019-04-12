import axiosOrg from "axios";
import qs from "qs";

export const axios = axiosOrg.create({
  baseURL: "http://localhost:8080/api/",
  paramsSerializer: params =>
    qs.stringify(params, {
      encodeValuesOnly: true,
      encoder: str => encodeURI(str)
    })
});
