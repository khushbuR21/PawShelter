import { useEffect, useState } from "react";
import {
  getAllEvents,
  registerVolunteer,
  getMyRegistrations
} from "../../api/volunteerApi";

function VolunteerRegister() {
  const [events, setEvents] = useState([]);
  const [myRegistrations, setMyRegistrations] = useState([]);

  useEffect(() => {
    loadEvents();
    loadMyRegistrations();
  }, []);

  const loadEvents = async () => {
    try {
      const res = await getAllEvents();
      setEvents(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      setEvents([]);
    }
  };

  const loadMyRegistrations = async () => {
    try {
      const res = await getMyRegistrations();
      setMyRegistrations(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      setMyRegistrations([]);
    }
  };

  const handleRegister = async (id) => {
    try {
      await registerVolunteer(id);
      alert("Registered successfully!");
      loadMyRegistrations();
    } catch (err) {
      alert(err.response?.data?.message || "Already registered or failed.");
    }
  };

  const formatDate = (d) => (d ? new Date(d).toLocaleDateString() : "-");
  const formatDateTime = (d) => (d ? new Date(d).toLocaleString() : "-");

  return (
    <div className="container mt-4">
      <h2>Volunteer Events</h2>

      <h5 className="mt-4">Available events – Register</h5>
      <ul className="list-group mb-4">
        {events.length === 0 && (
          <li className="list-group-item text-muted">No events available.</li>
        )}
        {events.map((e) => (
          <li
            className="list-group-item d-flex justify-content-between align-items-center"
            key={e.id}
          >
            <div>
              <strong>{e.title}</strong>
              {(e.eventDate || e.eventTime || e.venue) && (
                <span className="text-muted ms-2">
                  {formatDate(e.eventDate)} {e.eventTime && `• ${e.eventTime}`}{" "}
                  {e.venue && `• ${e.venue}`}
                </span>
              )}
            </div>
            <button
              className="btn btn-success btn-sm"
              onClick={() => handleRegister(e.id)}
            >
              Register
            </button>
          </li>
        ))}
      </ul>

      <h5>My registrations</h5>
      <p className="text-muted small">Events you have registered for (date, time, venue)</p>
      {myRegistrations.length === 0 ? (
        <p className="text-muted">You have not registered for any event yet.</p>
      ) : (
        <div className="table-responsive">
          <table className="table table-bordered">
            <thead className="table-light">
              <tr>
                <th>Event</th>
                <th>Date</th>
                <th>Time</th>
                <th>Venue</th>
                <th>Registered At</th>
              </tr>
            </thead>
            <tbody>
              {myRegistrations.map((r) => (
                <tr key={r.registrationId}>
                  <td>{r.eventTitle}</td>
                  <td>{formatDate(r.eventDate)}</td>
                  <td>{r.eventTime || "-"}</td>
                  <td>{r.venue || "-"}</td>
                  <td>{formatDateTime(r.registeredAt)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default VolunteerRegister;
