import { Outlet } from "react-router-dom";
import CustomerNavbar from "../components/CustomerNavbar";

function CustomerLayout() {
  return (
    <>
      <CustomerNavbar />
      <Outlet />
    </>
  );
}

export default CustomerLayout;
