import { Link, useNavigate } from "react-router-dom";

function AdminNavbar() {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-4">
      <Link className="navbar-brand" to="/admin">Admin Panel</Link>

      <div className="navbar-nav">
        <Link className="nav-link" to="/admin/pets">Manage Pets</Link>
        <Link className="nav-link" to="/admin/adoptions">Adoptions</Link>
        <Link className="nav-link" to="/admin/donations">Donations</Link>
        <Link className="nav-link" to="/admin/volunteer-events">Volunteer</Link>
        <Link className="nav-link" to="/admin/sos">SOS</Link>
      </div>

      <button onClick={logout} className="btn btn-danger ms-auto">
        Logout
      </button>
    </nav>
  );
}

export default AdminNavbar;
