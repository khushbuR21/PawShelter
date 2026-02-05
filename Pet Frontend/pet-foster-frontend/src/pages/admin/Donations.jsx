import { useEffect, useState } from "react";
import { getAllDonations } from "../../api/donationApi";

function Donations() {
  const [donations, setDonations] = useState([]);

  useEffect(() => {
    loadDonations();
  }, []);

  const loadDonations = async () => {
    try {
      const res = await getAllDonations();
      setDonations(res.data || []);
    } catch (err) {
      console.error(err);
      setDonations([]);
    }
  };

  return (
    <div className="container mt-4">
      <h2>All Donations</h2>
      <p className="text-muted">Pets donated by users.</p>

      <table className="table table-bordered mt-3">
        <thead className="table-dark">
          <tr>
            <th>ID</th>
            <th>Pet</th>
            <th>Donor Email</th>
            <th>Donated At</th>
          </tr>
        </thead>
        <tbody>
          {donations.length === 0 ? (
            <tr>
              <td colSpan="4" className="text-center text-muted">
                No donations yet.
              </td>
            </tr>
          ) : (
            donations.map((d) => (
              <tr key={d.donationId}>
                <td>{d.donationId}</td>
                <td>{d.petName}</td>
                <td>{d.donorEmail}</td>
                <td>{d.donatedAt ? new Date(d.donatedAt).toLocaleString() : "-"}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Donations;
