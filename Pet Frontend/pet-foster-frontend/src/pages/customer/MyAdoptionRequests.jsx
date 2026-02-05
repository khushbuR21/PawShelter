import { useEffect, useState } from "react";
import { getMyAdoptionRequests } from "../../api/adoptionApi";

function MyAdoptionRequests() {
  const [requests, setRequests] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    loadRequests();
  }, []);

  const loadRequests = async () => {
    try {
      setError("");
      const res = await getMyAdoptionRequests();
      setRequests(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      setError("Failed to load your adoption requests");
      setRequests([]);
    }
  };

  const statusBadge = (status) => {
    const cls =
      status === "APPROVED"
        ? "bg-success"
        : status === "REJECTED"
        ? "bg-danger"
        : "bg-warning text-dark";
    return <span className={`badge ${cls}`}>{status}</span>;
  };

  return (
    <div className="container mt-4">
      <h2>My Adoption Requests</h2>
      <p className="text-muted">
        See the status of your adoption requests. Admin approval or rejection will appear here.
      </p>
      {error && <div className="alert alert-danger">{error}</div>}

      {requests.length === 0 && !error && (
        <p className="text-muted">You have not submitted any adoption request yet.</p>
      )}

      {requests.length > 0 && (
        <table className="table table-bordered">
          <thead className="table-light">
            <tr>
              <th>Pet</th>
              <th>Status</th>
              <th>Requested At</th>
              <th>Updated At</th>
            </tr>
          </thead>
          <tbody>
            {requests.map((r) => (
              <tr key={r.id}>
                <td>{r.pet?.name ?? "-"}</td>
                <td>{statusBadge(r.status)}</td>
                <td>
                  {r.requestedAt ? new Date(r.requestedAt).toLocaleString() : "-"}
                </td>
                <td>
                  {r.actionAt ? new Date(r.actionAt).toLocaleString() : "-"}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default MyAdoptionRequests;
