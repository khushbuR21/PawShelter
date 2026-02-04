import { useEffect, useState } from "react";
import { getAllSos } from "../../api/sosApi";
import AdminNavbar from "../../components/AdminNavbar";

function SosReports() {
  const [reports, setReports] = useState([]);

  useEffect(() => {
    loadReports();
  }, []);

  const loadReports = async () => {
    const res = await getAllSos();
    setReports(res.data);
  };

  return (
    <>
      <AdminNavbar />

      <div className="container mt-4">
        <h2>SOS Reports</h2>

        <table className="table table-bordered mt-3">
          <thead className="table-dark">
            <tr>
              <th>ID</th>
              <th>User</th>
              <th>Message</th>
              <th>Date</th>
            </tr>
          </thead>

          <tbody>
            {reports.map((r) => (
              <tr key={r.id}>
                <td>{r.id}</td>
                <td>{r.user?.name}</td>
                <td>{r.message}</td>
                <td>{r.createdAt}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}

export default SosReports;
