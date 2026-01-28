import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";

function Register() {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    name: "",
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const registerUser = async () => {
    try {
      await api.post("/auth/register", user);
      alert("User Registered Successfully");
      navigate("/login");
    } catch (error) {
      alert("Registration Failed");
      console.error(error);
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>User Registration</h2>

      <input
        name="name"
        placeholder="Name"
        style={styles.input}
        onChange={handleChange}
      />

      <input
        name="email"
        placeholder="Email"
        style={styles.input}
        onChange={handleChange}
      />

      <input
        type="password"
        name="password"
        placeholder="Password"
        style={styles.input}
        onChange={handleChange}
      />

      <button style={styles.button} onClick={registerUser}>
        Register
      </button>
    </div>
  );
}

const styles = {
  container: {
    width: "350px",
    margin: "50px auto",
    padding: "20px",
    borderRadius: "8px",
    boxShadow: "0 0 10px rgba(0,0,0,0.1)",
    textAlign: "center",
    backgroundColor: "#fff",
  },
  heading: {
    marginBottom: "20px",
    color: "#2c3e50",
  },
  input: {
    width: "100%",
    padding: "10px",
    marginBottom: "12px",
    borderRadius: "4px",
    border: "1px solid #ccc",
    outline: "none",
  },
  button: {
    width: "100%",
    padding: "10px",
    backgroundColor: "#2ecc71",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
    fontSize: "16px",
  },
};

export default Register;
