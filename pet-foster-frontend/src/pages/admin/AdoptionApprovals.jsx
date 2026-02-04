import { useEffect, useState } from "react";
import {
  getAllAdoptionRequests,
  updateAdoptionStatus
} from "../../api/adoptionApi";

function AdoptionApprovals() {
  const [requests, setRequests] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    loadRequests();
  }, []);

  const loadRequests = async () => {
    try {
      setError("");
      const res = await getAllAdoptionRequests();
      setRequests(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      setError("Failed to load adoption requests");
      setRequests([]);
    }
  };

  const handleStatus = async (id, status) => {
    try {
      await updateAdoptionStatus(id, status);
      loadRequests();
    } catch (err) {
      setError(err.response?.data?.message || "Failed to update status");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Adoption Requests</h2>
      {error && <div className="alert alert-danger">{error}</div>}

      <table className="table table-bordered mt-3">
        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Pet</th>
            <th>Customer</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {requests.length === 0 && !error && (
            <tr>
              <td colSpan="5" className="text-center text-muted">
                No adoption requests yet.
              </td>
            </tr>
          )}
          {requests.map((r) => (
            <tr key={r.id}>
              <td>{r.id}</td>
              <td>{r.pet?.name}</td>
              <td>{r.customer?.name ?? r.customer?.email ?? "-"}</td>
              <td>{r.status}</td>
              <td>
                {r.status === "PENDING" && (
                  <>
                    <button
                      className="btn btn-success btn-sm me-2"
                      onClick={() => handleStatus(r.id, "APPROVED")}
                    >
                      Approve
                    </button>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => handleStatus(r.id, "REJECTED")}
                    >
                      Reject
                    </button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdoptionApprovals;
