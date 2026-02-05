import axiosInstance from "./axiosConfig";

export const getAllPets = () => {
  return axiosInstance.get("/pets");
};

export const createPet = (petData) => {
  return axiosInstance.post("/pets", petData);
};

export const updatePet = (id, petData) => {
  return axiosInstance.put(`/pets/${id}`, petData);
};

export const deletePet = (id) => {
  return axiosInstance.delete(`/pets/${id}`);
};

export const uploadPetImage = (id, imageFile) => {
  const formData = new FormData();
  formData.append("image", imageFile);

  return axiosInstance.post(`/pets/${id}/images`, formData, {
    headers: { "Content-Type": "multipart/form-data" }
  });
};
