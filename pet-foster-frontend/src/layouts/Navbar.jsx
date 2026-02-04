import { Link, useNavigate } from "react-router-dom";
import { getRole, logout } from "../utils/auth";

function Navbar() {
  const navigate = useNavigate();
  const role = getRole();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-dark bg-dark navbar-expand-lg">
      <div className="container">
        <Link className="navbar-brand" to="/">
          PetFoster
        </Link>

        <ul className="navbar-nav ms-auto">

          {role === "ADMIN" && (
            <>
              <li className="nav-item">
                <Link className="nav-link" to="/admin/dashboard">
                  Dashboard
                </Link>
              </li>

              <li className="nav-item">
                <Link className="nav-link" to="/admin/pets">
                  Manage Pets
                </Link>
              </li>

               <li className="nav-item">
            <Link className="nav-link" to="/admin/adoptions">
                Adoption Requests
            </Link>
            </li>

            <Link className="nav-link" to="/admin/donations">
            Donations
            </Link>

<Link className="nav-link" to="/admin/volunteers">
Volunteer Events
</Link>

<Link className="nav-link" to="/admin/sos">SOS Reports</Link>


                </>
          )}

          {role === "CUSTOMER" && (
            <>

            <li className="nav-item">
              <Link className="nav-link" to="/customer/dashboard">
                Dashboard
              </Link>
            </li>

            <li className="nav-item">
                 <Link className="nav-link" to="/customer/pets">
                View Pets
              </Link>
            </li>

            <Link className="nav-link" to="/customer/donate">Donate</Link>
<Link className="nav-link" to="/customer/volunteer">Volunteer</Link>
<Link className="nav-link" to="/customer/sos">Raise SOS</Link>

            </>

            
        
          )}

          {role && (
            <li className="nav-item ms-3">
              <button className="btn btn-danger btn-sm" onClick={handleLogout}>
                Logout
              </button>
            </li>
          )}

        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
