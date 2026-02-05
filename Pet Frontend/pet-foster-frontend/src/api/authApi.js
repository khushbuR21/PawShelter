import axiosInstance from "./axiosConfig";

export const loginUser = (loginData) => {
  return axiosInstance.post("/auth/login", loginData);
};

export const registerUser = (registerData) => {
  return axiosInstance.post("/auth/register", registerData);
};
