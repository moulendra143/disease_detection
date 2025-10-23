import React from "react";
import "./HomePage.css";
import { useNavigate } from "react-router-dom";

export default function HomePage() {
  const navigate = useNavigate();

  return (
    <div className="homepage-container">
      <div className="overlay">
        <h1 className="homepage-title">Early Disease Detection System</h1>
        <p className="homepage-subtitle">
          Empowering patients with AI-based early prediction for Diabetes and Breast Cancer.
        </p>

        <div className="button-group">
          <button
            className="btn-home btn-diabetes"
            onClick={() => navigate("/diabetes")}
          >
            Predict Diabetes
          </button>

          <button
            className="btn-home btn-breast-cancer"
            onClick={() => navigate("/breast-cancer")}
          >
            Predict Breast Cancer
          </button>
        </div>

        <footer className="homepage-footer">
          © 2025 AI Health Predictor | Designed by Moulendra
        </footer>
      </div>
    </div>
  );
}
