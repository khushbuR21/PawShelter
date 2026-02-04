import axiosInstance from "./axiosConfig";

// CUSTOMER
export const createDonation = (data) => {
  return axiosInstance.post("/donations", data);
};

// ADMIN
export const getAllDonations = () => {
  return axiosInstance.get("/donations");
};
