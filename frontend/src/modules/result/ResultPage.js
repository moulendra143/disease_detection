import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./result.css";

export default function ResultPage() {
  const { state } = useLocation();
  const navigate = useNavigate();
  const prediction = state?.prediction || "Unknown";
  const type = state?.type || "result";

  const healthy = (type === "diabetes" && prediction === "Non-Diabetic")
                 || (type === "breast" && (prediction === "Benign" || prediction === "Non-Diabetic"));

  return (
    <div className={`result-page ${healthy ? 'happy' : 'sad'}`}>
      <div className="result-card">
        <h2>{healthy ? "Good news!" : "Important result"}</h2>
        <p className="big">{prediction}</p>
        <p className="message">
          {healthy ? "No immediate sign of the disease. Keep healthy!" : "Please consult a doctor for follow-up."}
        </p>
        <button className="primary-btn" onClick={() => navigate("/home")}>Back to Home</button>
      </div>
    </div>
  );
}
