import { useEffect, useState } from "react";
import api from "../api/api";
import { useNavigate } from "react-router-dom";

function Plans() {
  const [plans, setPlans] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPlans = async () => {
      try {
        const res = await api.get("/plans/all");
        console.log("Plans API response:", res.data);
        setPlans(res.data);
      } catch (err) {
        console.error("Error fetching plans", err);
        setError("Failed to load plans");
      } finally {
        setLoading(false);
      }
    };

    fetchPlans();
  }, []);

  if (loading) return <h3 style={styles.center}>Loading plans...</h3>;
  if (error) return <h3 style={styles.error}>{error}</h3>;

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>Available Plans</h2>

      {plans.length === 0 ? (
        <p>No plans available</p>
      ) : (
        plans.map((plan) => (
          <div key={plan.id} style={styles.card}>
            <p style={styles.planName}>
              {plan.name}
            </p>

            <p style={styles.price}>₹{plan.price}</p>

            <button
              style={styles.button}
              onClick={() =>
                navigate(`/payments?planId=${plan.id}&price=${plan.price}`)
              }
            >
              Subscribe
            </button>
          </div>
        ))
      )}
    </div>
  );
}

const styles = {
  container: {
    maxWidth: "600px",
    margin: "40px auto",
    padding: "10px",
  },
  heading: {
    textAlign: "center",
    marginBottom: "20px",
    color: "#2c3e50",
  },
  card: {
    border: "1px solid #ddd",
    borderRadius: "6px",
    padding: "15px",
    marginBottom: "15px",
    boxShadow: "0 0 8px rgba(0,0,0,0.05)",
  },
  planName: {
    fontSize: "18px",
    fontWeight: "600",
    marginBottom: "5px",
  },
  price: {
    fontSize: "16px",
    color: "#27ae60",
    marginBottom: "10px",
  },
  button: {
    padding: "8px 14px",
    backgroundColor: "#3498db",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
  },
  center: {
    textAlign: "center",
  },
  error: {
    color: "red",
    textAlign: "center",
  },
};

export default Plans;
