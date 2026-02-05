import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./auth/Login";
import Register from "./auth/Register";
import ProtectedRoute from "./auth/ProtectedRoute";

import CustomerLayout from "./layouts/CustomerLayout";
import AdminLayout from "./layouts/AdminLayout";

import AdminDashboard from "./pages/admin/AdminDashboard";
import ManagePets from "./pages/admin/ManagePets";
import AdoptionApprovals from "./pages/admin/AdoptionApprovals";
import Donations from "./pages/admin/Donations";
import SosReports from "./pages/admin/SosReports";
import VolunteerEvents from "./pages/admin/VolunteerEvents";

import CustomerDashboard from "./pages/customer/CustomerDashboard";
import ViewPets from "./pages/customer/ViewPets";
import Donate from "./pages/customer/Donate";
import SosCreate from "./pages/customer/SosCreate";
import VolunteerRegister from "./pages/customer/VolunteerRegister";
import MyAdoptionRequests from "./pages/customer/MyAdoptionRequests";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route path="/" element={<Navigate to="/login" replace />} />

        {/* CUSTOMER: layout has Navbar + Outlet; index = dashboard, rest = nested pages */}
        {/* Backend uses USER for customers; allow both CUSTOMER and USER */}
        <Route
          path="/customer"
          element={
            <ProtectedRoute role={["CUSTOMER", "USER"]}>
              <CustomerLayout />
            </ProtectedRoute>
          }
        >
          <Route index element={<CustomerDashboard />} />
          <Route path="pets" element={<ViewPets />} />
          <Route path="donate" element={<Donate />} />
          <Route path="sos" element={<SosCreate />} />
          <Route path="volunteer" element={<VolunteerRegister />} />
          <Route path="my-adoptions" element={<MyAdoptionRequests />} />
        </Route>

        {/* ADMIN */}
        <Route
          path="/admin"
          element={
            <ProtectedRoute role="ADMIN">
              <AdminLayout />
            </ProtectedRoute>
          }
        >
          <Route index element={<AdminDashboard />} />
          <Route path="pets" element={<ManagePets />} />
          <Route path="adoptions" element={<AdoptionApprovals />} />
          <Route path="donations" element={<Donations />} />
          <Route path="sos" element={<SosReports />} />
          <Route path="volunteer-events" element={<VolunteerEvents />} />
        </Route>

        <Route path="*" element={<Navigate to="/login" replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
