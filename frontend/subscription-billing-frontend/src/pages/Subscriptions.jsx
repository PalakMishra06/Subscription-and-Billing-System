import { useEffect, useState } from "react";
import api from "../api/api";

function Subscriptions() {
  const [subs, setSubs] = useState([]);

  const loadSubscriptions = () => {
    api.get("/subscriptions/user/1")
      .then((res) => {
        setSubs(res.data); // store all subscriptions
      })
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    loadSubscriptions();

    const interval = setInterval(() => {
      loadSubscriptions();
    }, 60000);

    return () => clearInterval(interval);
  }, []);

  const getDaysLeft = (endDate) => {
    const today = new Date();
    const end = new Date(endDate);
    const diffTime = end - today;
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    return diffDays > 0 ? diffDays : 0;
  };

  // Separate active and expired subscriptions
  const currentDate = new Date();
  const activeSubs = subs.filter(
    (s) => new Date(s.endDate) >= currentDate && s.status?.toUpperCase() === "ACTIVE"
  );
  const expiredSubs = subs.filter(
    (s) => new Date(s.endDate) < currentDate || s.status?.toUpperCase() !== "ACTIVE"
  );

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>My Subscriptions</h2>

      <h3>Active Subscriptions</h3>
      {activeSubs.length === 0 && <p style={styles.noData}>No active subscriptions.</p>}
      {activeSubs.map((s) => (
        <div key={s.id} style={styles.card}>
          <p><b>Status:</b> {s.status}</p>
          <p><b>Start:</b> {s.startDate}</p>
          <p><b>End:</b> {s.endDate}</p>
          <p>
            <b>Days Left:</b> <span style={styles.daysLeft}>{getDaysLeft(s.endDate)}</span>
          </p>
        </div>
      ))}

      <h3>Expired Subscriptions</h3>
      {expiredSubs.length === 0 && <p style={styles.noData}>No expired subscriptions.</p>}
      {expiredSubs.map((s) => (
        <div key={s.id} style={{ ...styles.card, backgroundColor: "#f8d7da" }}>
          <p><b>Status:</b> {s.status}</p>
          <p><b>Start:</b> {s.startDate}</p>
          <p><b>End:</b> {s.endDate}</p>
          <p>
            <b>Days Left:</b> <span style={{ ...styles.daysLeft, color: "#c0392b" }}>{getDaysLeft(s.endDate)}</span>
          </p>
        </div>
      ))}
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
    backgroundColor: "#fff",
  },
  daysLeft: {
    color: "#27ae60",
    fontWeight: "600",
  },
  noData: {
    textAlign: "center",
    color: "#777",
  },
};

export default Subscriptions;
