import { API_BASE_URL } from "../api/axiosConfig";

function PetCard({ pet, onEdit, onDelete }) {
  const imageSrc = pet.images?.length
    ? (pet.images[0].startsWith("http") ? pet.images[0] : `${API_BASE_URL}${pet.images[0]}`)
    : "https://via.placeholder.com/200";

  return (
    <div className="card shadow-sm">
      <img
        src={imageSrc}
        className="card-img-top"
        alt={pet.name}
        style={{ height: "200px", objectFit: "cover" }}
      />

      <div className="card-body">
        <h5 className="card-title">{pet.name}</h5>
        <p className="card-text">
          Breed: {pet.breed}<br />
          Age: {pet.age}<br />
          Gender: {pet.gender}
        </p>

        <div className="d-flex justify-content-between gap-1">
          <button className="btn btn-warning btn-sm" onClick={() => onEdit(pet)}>
            Edit
          </button>
          {onDelete && (
            <button className="btn btn-danger btn-sm" onClick={() => onDelete(pet.id)}>
              Delete
            </button>
          )}
        </div>
      </div>
    </div>
  );
}

export default PetCard;
