import { useEffect, useState } from "react";
import {
  createEvent,
  getAllEvents,
  getEventRegistrations
} from "../../api/volunteerApi";

function VolunteerEvents() {
  const [events, setEvents] = useState([]);
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    eventDate: "",
    eventTime: "",
    venue: ""
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [registrationsFor, setRegistrationsFor] = useState(null);
  const [registrations, setRegistrations] = useState([]);

  useEffect(() => {
    loadEvents();
  }, []);

  const loadEvents = async () => {
    try {
      setError("");
      const res = await getAllEvents();
      setEvents(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      setError("Failed to load events");
      setEvents([]);
    }
  };

  const handleChange = (e) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      await createEvent({
        title: formData.title,
        description: formData.description,
        eventDate: formData.eventDate ? formData.eventDate + "T00:00:00" : null,
        eventTime: formData.eventTime,
        venue: formData.venue
      });
      setFormData({ title: "", description: "", eventDate: "", eventTime: "", venue: "" });
      loadEvents();
    } catch (err) {
      setError(err.response?.data?.message || "Failed to create event");
    } finally {
      setLoading(false);
    }
  };

  const viewRegistrations = async (eventId) => {
    try {
      const res = await getEventRegistrations(eventId);
      setRegistrations(res.data || []);
      setRegistrationsFor(eventId);
    } catch (err) {
      setRegistrations([]);
      setRegistrationsFor(eventId);
    }
  };

  const formatDate = (d) =>
    d ? new Date(d).toLocaleDateString() : "-";

  return (
    <div className="container mt-4">
      <h2>Volunteer Events</h2>
      {error && <div className="alert alert-danger">{error}</div>}

      <form onSubmit={handleSubmit} className="card p-3 mb-4 col-md-6">
        <h5>Create Event</h5>
        <input
          className="form-control mb-2"
          name="title"
          placeholder="Event Title *"
          value={formData.title}
          onChange={handleChange}
          required
        />
        <textarea
          className="form-control mb-2"
          name="description"
          placeholder="Description"
          rows="2"
          value={formData.description}
          onChange={handleChange}
        />
        <input
          type="date"
          className="form-control mb-2"
          name="eventDate"
          placeholder="Date"
          value={formData.eventDate}
          onChange={handleChange}
        />
        <input
          type="time"
          className="form-control mb-2"
          name="eventTime"
          placeholder="Time"
          value={formData.eventTime}
          onChange={handleChange}
        />
        <input
          className="form-control mb-2"
          name="venue"
          placeholder="Venue"
          value={formData.venue}
          onChange={handleChange}
        />
        <button className="btn btn-primary" disabled={loading}>
          {loading ? "Creating..." : "Create Event"}
        </button>
      </form>

      <h5>All Events</h5>
      <div className="list-group mb-3">
        {events.length === 0 && !error && (
          <div className="list-group-item text-muted">No events yet.</div>
        )}
        {events.map((e) => (
          <div key={e.id} className="list-group-item">
            <div className="d-flex justify-content-between align-items-center">
              <div>
                <strong>{e.title}</strong>
                {e.eventDate && (
                  <span className="text-muted ms-2">
                    {formatDate(e.eventDate)} {e.eventTime && `• ${e.eventTime}`}{" "}
                    {e.venue && `• ${e.venue}`}
                  </span>
                )}
              </div>
              <button
                className="btn btn-outline-primary btn-sm"
                onClick={() => viewRegistrations(e.id)}
              >
                View Registrations
              </button>
            </div>
          </div>
        ))}
      </div>

      {registrationsFor !== null && (
        <div className="card p-3">
          <h5>Who registered for this event</h5>
          {registrations.length === 0 ? (
            <p className="text-muted mb-0">No registrations yet.</p>
          ) : (
            <table className="table table-sm table-bordered">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Registered At</th>
                </tr>
              </thead>
              <tbody>
                {registrations.map((r) => (
                  <tr key={r.registrationId}>
                    <td>{r.customerName}</td>
                    <td>{r.customerEmail}</td>
                    <td>{r.registeredAt ? new Date(r.registeredAt).toLocaleString() : "-"}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
          <button
            className="btn btn-secondary btn-sm"
            onClick={() => setRegistrationsFor(null)}
          >
            Close
          </button>
        </div>
      )}
    </div>
  );
}

export default VolunteerEvents;
