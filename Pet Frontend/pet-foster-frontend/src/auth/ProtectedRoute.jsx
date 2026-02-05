import { Navigate } from "react-router-dom";

const ProtectedRoute = ({ children, role }) => {
  const token = localStorage.getItem("token");
  const storedRole = localStorage.getItem("role");
  const userJson = localStorage.getItem("user");
  let user = null;
  if (userJson) {
    try {
      user = JSON.parse(userJson);
    } catch {
      user = storedRole ? { role: storedRole } : null;
    }
  } else if (storedRole) {
    user = { role: storedRole };
  }

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  // role can be a string or array (e.g. ["CUSTOMER", "USER"] for backend USER = customer)
  if (role) {
    const allowed = Array.isArray(role) ? role : [role];
    if (!allowed.includes(user?.role)) {
      return <Navigate to="/login" replace />;
    }
  }

  return children;
};

export default ProtectedRoute;
