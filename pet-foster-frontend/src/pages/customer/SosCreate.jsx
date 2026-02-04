import { useState } from "react";
import { createSos } from "../../api/sosApi";
import CustomerNavbar from "../../components/CustomerNavbar";

function SosCreate() {
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await createSos({ message });
      alert("SOS reported successfully!");
      setMessage("");
    } catch {
      alert("Failed to submit SOS");
    }
  };

  return (
    <>
      <CustomerNavbar />

      <div className="container mt-4">
        <h2>Raise SOS</h2>

        <form onSubmit={handleSubmit} className="card p-4 col-md-5">
          <textarea
            className="form-control mb-3"
            placeholder="Describe the emergency"
            rows="4"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            required
          />

          <button className="btn btn-danger w-100">
            Submit SOS
          </button>
        </form>
      </div>
    </>
  );
}

export default SosCreate;
