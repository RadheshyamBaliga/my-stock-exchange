import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8050/",
});

export default axiosInstance;
