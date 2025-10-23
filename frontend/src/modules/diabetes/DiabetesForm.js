import React, { useState } from "react";
import axios from "axios";
import "./formpage.css";
import { useNavigate } from "react-router-dom";

export default function DiabetesForm() {
  const [form, setForm] = useState({
    pregnancies: "",
    glucose: "",
    bloodPressure: "",
    skinThickness: "",
    insulin: "",
    bmi: "",
    diabetesPedigreeFunction: "",
    age: "",
  });

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    // numeric validation: allow empty or valid number
    if (value === "" || /^-?\d*\.?\d*$/.test(value)) {
      setForm({ ...form, [name]: value });
      setErrors({ ...errors, [name]: null });
    } else {
      setErrors({ ...errors, [name]: "Invalid number" });
    }
  };

  const validateAll = () => {
    const newErr = {};
    Object.keys(form).forEach(k => {
      if (form[k] === "") newErr[k] = "Required";
    });
    setErrors(newErr);
    return Object.keys(newErr).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateAll()) return;
    setLoading(true);

    // build numeric payload
    const payload = {
      pregnancies: parseFloat(form.pregnancies),
      glucose: parseFloat(form.glucose),
      bloodPressure: parseFloat(form.bloodPressure),
      skinThickness: parseFloat(form.skinThickness),
      insulin: parseFloat(form.insulin),
      bmi: parseFloat(form.bmi),
      diabetesPedigreeFunction: parseFloat(form.diabetesPedigreeFunction),
      age: parseFloat(form.age),
    };

    try {
      const res = await axios.post("http://localhost:8080/api/diabetes/predict", payload);
      if (res.data?.prediction) {
        navigate("/result", { state: { prediction: res.data.prediction, type: "diabetes" } });
      } else if (res.data?.error) {
        // try to map which field is bad
        alert("Prediction error: " + res.data.error);
      }
    } catch (err) {
      console.error(err);
      alert("Prediction failed. Check inputs.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="diabetes-page">
      <div className="diabetes-form-card">
        <h2 className="diabetes-form-title">Diabetes Prediction</h2>
        <form onSubmit={handleSubmit}>
          {Object.keys(form).map((key) => (
            <div key={key} className="input-group">
              <label>{key}</label>
              <input
                type="text"
                name={key}
                value={form[key]}
                onChange={handleChange}
                placeholder={key}
              />
              {errors[key] && <small className="input-error">{errors[key]}</small>}
            </div>
          ))}

          <button type="submit" className="btn-diabetes-submit" disabled={loading}>
            {loading ? "Predicting..." : "Predict"}
          </button>
        </form>
      </div>
    </div>
  );
}
