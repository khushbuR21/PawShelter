export const getToken = () => localStorage.getItem("token");

export const getRole = () => localStorage.getItem("role");

export const isAuthenticated = () => {
  return getToken() !== null;
};

export const logout = () => {
  localStorage.clear();
};
