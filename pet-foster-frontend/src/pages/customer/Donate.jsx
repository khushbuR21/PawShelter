import { useState } from "react";
import { createDonation } from "../../api/donationApi";

function Donate() {
  const [formData, setFormData] = useState({
    name: "",
    gender: "MALE",
    description: "",
    type: "",
    breed: "",
    color: "",
    age: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createDonation({
        name: formData.name,
        gender: formData.gender,
        description: formData.description,
        type: formData.type,
        breed: formData.breed,
        color: formData.color,
        age: parseInt(formData.age, 10) || 0
      });
      alert("Pet donation submitted successfully!");
      setFormData({
        name: "",
        gender: "MALE",
        description: "",
        type: "",
        breed: "",
        color: "",
        age: ""
      });
    } catch (err) {
      alert(err.response?.data?.message || "Donation failed");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Donate a Pet</h2>
      <p className="text-muted">Submit details of the pet you wish to donate.</p>

      <form onSubmit={handleSubmit} className="card p-4 col-md-6">
        <input
          type="text"
          name="name"
          className="form-control mb-2"
          placeholder="Pet name *"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <select
          name="gender"
          className="form-control mb-2"
          value={formData.gender}
          onChange={handleChange}
        >
          <option value="MALE">Male</option>
          <option value="FEMALE">Female</option>
        </select>
        <input
          type="text"
          name="type"
          className="form-control mb-2"
          placeholder="Type (e.g. Dog, Cat)"
          value={formData.type}
          onChange={handleChange}
        />
        <input
          type="text"
          name="breed"
          className="form-control mb-2"
          placeholder="Breed"
          value={formData.breed}
          onChange={handleChange}
        />
        <input
          type="text"
          name="color"
          className="form-control mb-2"
          placeholder="Color"
          value={formData.color}
          onChange={handleChange}
        />
        <input
          type="number"
          name="age"
          className="form-control mb-2"
          placeholder="Age (years)"
          value={formData.age}
          onChange={handleChange}
          min="0"
        />
        <textarea
          name="description"
          className="form-control mb-3"
          placeholder="Description"
          rows="2"
          value={formData.description}
          onChange={handleChange}
        />
        <button type="submit" className="btn btn-success w-100">
          Submit Donation
        </button>
      </form>
    </div>
  );
}

export default Donate;
