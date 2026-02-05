import axiosInstance from "./axiosConfig";

// CUSTOMER
export const createSos = (data) => {
  return axiosInstance.post("/sos", data);
};

// ADMIN
export const getAllSos = () => {
  return axiosInstance.get("/sos");
};
