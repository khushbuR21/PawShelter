import { useEffect, useState } from "react";
import {
  getAllPets,
  createPet,
  uploadPetImage
} from "../../api/petApi";

import PetCard from "../../components/PetCard";
import PetForm from "../../components/PetForm";

function ManagePets() {
  const [pets, setPets] = useState([]);
  const [selectedPet, setSelectedPet] = useState(null);
  const [image, setImage] = useState(null);

  const loadPets = async () => {
    const res = await getAllPets();
    setPets(res.data);
  };

  useEffect(() => {
    loadPets();
  }, []);

  const handleSubmit = async (petData) => {
    await createPet(petData);
    setSelectedPet(null);
    loadPets();
  };

  const handleImageUpload = async (petId) => {
    if (!image) {
      alert("Please select an image first");
      return;
    }

    await uploadPetImage(petId, image);
    alert("Image uploaded successfully");
    setImage(null);
    loadPets();
  };

  return (
    <div className="container mt-4">
      <h2>Manage Pets</h2>

      <PetForm onSubmit={handleSubmit} selectedPet={selectedPet} />

      <div className="row mt-4">
        {pets.map((pet) => (
          <div className="col-md-4 mb-3" key={pet.id}>
            <PetCard pet={pet} onEdit={setSelectedPet} />

            <input
              type="file"
              className="form-control mt-2"
              accept="image/*"
              onChange={(e) => setImage(e.target.files[0])}
            />

            <button
              className="btn btn-secondary btn-sm mt-2 w-100"
              onClick={() => handleImageUpload(pet.id)}
              disabled={!image}
            >
              Upload Image
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ManagePets;
