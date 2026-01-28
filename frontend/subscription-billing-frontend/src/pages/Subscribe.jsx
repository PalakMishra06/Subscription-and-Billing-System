import { useSearchParams, useNavigate } from "react-router-dom";

function Subscribe() {
  const [params] = useSearchParams();
  const navigate = useNavigate();
  const planId = params.get("planId");

  const confirmAndPay = () => {
    navigate(`/payment?planId=${planId}`);
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>Confirm Subscription</h2>

      <p style={styles.text}>
        You are about to subscribe to <b>Plan ID: {planId}</b>
      </p>

      <p style={styles.text}>Click confirm to proceed to payment.</p>

      <button style={styles.button} onClick={confirmAndPay}>
        Confirm & Pay
      </button>
    </div>
  );
}

const styles = {
  container: {
    maxWidth: "400px",
    margin: "60px auto",
    padding: "20px",
    borderRadius: "8px",
    boxShadow: "0 0 10px rgba(0,0,0,0.1)",
    backgroundColor: "#fff",
    textAlign: "center",
  },
  heading: {
    marginBottom: "20px",
    color: "#2c3e50",
  },
  text: {
    marginBottom: "10px",
    fontSize: "15px",
  },
  button: {
    marginTop: "20px",
    padding: "10px 20px",
    backgroundColor: "#27ae60",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
    fontSize: "16px",
  },
};

export default Subscribe;
