import { Link, useNavigate } from "react-router-dom";

function CustomerNavbar() {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light px-4">
      <Link className="navbar-brand" to="/customer">
        Pet Foster
      </Link>

      <div className="navbar-nav">
        <Link className="nav-link" to="/customer">
          Dashboard
        </Link>
        <Link className="nav-link" to="/customer/pets">
          Pets
        </Link>
        <Link className="nav-link" to="/customer/donate">
          Donate
        </Link>
        <Link className="nav-link" to="/customer/sos">
          SOS
        </Link>
        <Link className="nav-link" to="/customer/volunteer">
          Volunteer
        </Link>
        <Link className="nav-link" to="/customer/my-adoptions">
          My Adoption Status
        </Link>
      </div>

      <button onClick={logout} className="btn btn-outline-danger ms-auto">
        Logout
      </button>
    </nav>
  );
}

export default CustomerNavbar;
