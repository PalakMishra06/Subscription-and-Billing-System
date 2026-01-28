import { useSearchParams, useNavigate } from "react-router-dom";
import api from "../api/api";
import { useState } from "react";

function Payments() {
  const [params] = useSearchParams();
  const navigate = useNavigate();

  const planId = params.get("planId");
  const price = params.get("price");

  const [loading, setLoading] = useState(false);
  const [paymentMethod, setPaymentMethod] = useState("");

  const user = JSON.parse(localStorage.getItem("user"));

  // Fake Razorpay simulation
  const fakeRazorpayPayment = () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          paymentId: "pay_" + Math.random().toString(36).substring(2, 10),
          status: "success",
        });
      }, 2000);
    });
  };

  const handlePayment = async () => {
    if (!paymentMethod) {
      alert("Please select a payment method");
      return;
    }

    setLoading(true);

    try {
      const payment = await fakeRazorpayPayment();
      console.log("Payment Success:", payment);

      alert(`Payment of ₹${price} successful via ${paymentMethod.toUpperCase()}`);

      await api.post(
        `/subscriptions/subscribe?userId=${user.id}&planId=${planId}`
      );

      alert("Subscription Activated!");
      navigate("/subscriptions");
    } catch (error) {
      console.error(error);
      alert("Payment failed!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>Payment Page</h2>

      <p><b>Plan ID:</b> {planId}</p>
      <p><b>Amount:</b> ₹{price}</p>

      <h4 style={styles.subHeading}>Select Payment Method</h4>

      <div style={styles.option}>
        <label>
          <input
            type="radio"
            name="payment"
            value="card"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />{" "}
          💳 Credit / Debit Card
        </label>
      </div>

      <div style={styles.option}>
        <label>
          <input
            type="radio"
            name="payment"
            value="upi"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />{" "}
          📱 UPI
        </label>
      </div>

      <div style={styles.option}>
        <label>
          <input
            type="radio"
            name="payment"
            value="netbanking"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />{" "}
          🏦 Net Banking
        </label>
      </div>

      <button
        onClick={handlePayment}
        disabled={loading}
        style={styles.button}
      >
        {loading ? "Processing..." : `Pay ₹${price}`}
      </button>
    </div>
  );
}

const styles = {
  container: {
    maxWidth: "400px",
    margin: "50px auto",
    padding: "20px",
    borderRadius: "8px",
    boxShadow: "0 0 10px rgba(0,0,0,0.1)",
    backgroundColor: "#fff",
  },
  heading: {
    marginBottom: "15px",
    color: "#2c3e50",
    textAlign: "center",
  },
  subHeading: {
    marginTop: "20px",
    marginBottom: "10px",
  },
  option: {
    marginBottom: "8px",
  },
  button: {
    width: "100%",
    padding: "10px",
    marginTop: "20px",
    backgroundColor: "#27ae60",
    color: "#fff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
    fontSize: "16px",
  },
};

export default Payments;
