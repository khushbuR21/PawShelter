import axiosInstance from "./axiosConfig";

// ADMIN
export const createEvent = (data) => {
  return axiosInstance.post("/volunteers/events", data);
};

export const getAllEvents = () => {
  return axiosInstance.get("/volunteers/events");
};

export const getEventRegistrations = (eventId) => {
  return axiosInstance.get(`/volunteers/events/${eventId}/registrations`);
};

// CUSTOMER
export const getMyRegistrations = () => {
  return axiosInstance.get("/volunteers/my");
};

export const registerVolunteer = (eventId) => {
  return axiosInstance.post("/volunteers/register", { eventId });
};
