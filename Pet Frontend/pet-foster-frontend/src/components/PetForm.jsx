import { useState, useEffect } from "react";

function PetForm({ onSubmit, selectedPet }) {
  const [petData, setPetData] = useState({
    name: "",
    breed: "",
    age: "",
    gender: ""
  });

  useEffect(() => {
    if (selectedPet) {
      setPetData(selectedPet);
    }
  }, [selectedPet]);

  const handleChange = (e) => {
    setPetData({
      ...petData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(petData);
    setPetData({ name: "", breed: "", age: "", gender: "" });
  };

  return (
    <form onSubmit={handleSubmit} className="card p-3 mb-4">
      <h5>{selectedPet ? "Update Pet" : "Add Pet"}</h5>

      <input className="form-control mb-2" name="name" placeholder="Pet Name" value={petData.name} onChange={handleChange} required />
      <input className="form-control mb-2" name="breed" placeholder="Breed" value={petData.breed} onChange={handleChange} required />
      <input className="form-control mb-2" name="age" placeholder="Age" value={petData.age} onChange={handleChange} required />
      <select className="form-control mb-2" name="gender" value={petData.gender} onChange={handleChange} required>
        <option value="">Select Gender</option>
        <option>MALE</option>
        <option>FEMALE</option>
      </select>

      <button className="btn btn-primary">
        {selectedPet ? "Update" : "Add"}
      </button>
    </form>
  );
}

export default PetForm;
