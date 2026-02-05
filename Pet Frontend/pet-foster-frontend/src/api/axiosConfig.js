import axios from "axios";

export const API_BASE_URL = "http://localhost:8080";

const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
});

// Attach JWT token automatically
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default axiosInstance;
