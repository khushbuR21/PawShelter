import { useEffect, useState } from "react";
import { getAllPets } from "../../api/petApi";
import { createAdoptionRequest } from "../../api/adoptionApi";
import { API_BASE_URL } from "../../api/axiosConfig";

function ViewPets() {
  const [pets, setPets] = useState([]);

  useEffect(() => {
    loadPets();
  }, []);

  const loadPets = async () => {
    const res = await getAllPets();
    setPets(res.data);
  };

  const handleAdopt = async (petId) => {
    try {
      await createAdoptionRequest(petId);
      alert("Adoption request submitted!");
    } catch (err) {
      alert("You already requested or something went wrong");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Available Pets</h2>

      <div className="row">
        {pets.map(pet => (
          <div className="col-md-4 mb-4" key={pet.id}>
            <div className="card shadow-sm">
              <img
                src={
                  pet.images?.length
                    ? (pet.images[0].startsWith("http") ? pet.images[0] : `${API_BASE_URL}${pet.images[0]}`)
                    : "https://via.placeholder.com/250"
                }
                className="card-img-top"
                alt={pet.name}
                style={{ height: "200px", objectFit: "cover" }}
              />

              <div className="card-body">
                <h5>{pet.name}</h5>
                <p>
                  Breed: {pet.breed}<br />
                  Age: {pet.age}<br />
                  Gender: {pet.gender}
                </p>

                <button
                  className="btn btn-success w-100"
                  onClick={() => handleAdopt(pet.id)}
                >
                  Request Adoption
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ViewPets;
