import axiosInstance from "./axiosConfig";

// CUSTOMER
export const createAdoptionRequest = (petId) => {
  return axiosInstance.post("/adoptions", { petId });
};

export const getMyAdoptionRequests = () => {
  return axiosInstance.get("/adoptions/my");
};

// ADMIN
export const getAllAdoptionRequests = () => {
  return axiosInstance.get("/adoptions");
};

export const updateAdoptionStatus = (id, status) => {
  return axiosInstance.put(`/adoptions/${id}/status`, { status });
};
