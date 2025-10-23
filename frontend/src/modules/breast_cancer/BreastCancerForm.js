import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { predictBreastCancer } from "../../modules/breast_cancer/BreastCancerService.js";
import "./formpage2.css";

export default function BreastCancerFormFull() {
  const initialState = {
    radius_mean: "", texture_mean: "", perimeter_mean: "", area_mean: "", smoothness_mean: "",
    compactness_mean: "", concavity_mean: "", concave_points_mean: "", symmetry_mean: "", fractal_dimension_mean: "",
    radius_se: "", texture_se: "", perimeter_se: "", area_se: "", smoothness_se: "",
    compactness_se: "", concavity_se: "", concave_points_se: "", symmetry_se: "", fractal_dimension_se: "",
    radius_worst: "", texture_worst: "", perimeter_worst: "", area_worst: "", smoothness_worst: "",
    compactness_worst: "", concavity_worst: "", concave_points_worst: "", symmetry_worst: "", fractal_dimension_worst: ""
  };

  const [form, setForm] = useState(initialState);
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (value === "" || /^-?\d*\.?\d*$/.test(value)) {
      setForm({ ...form, [name]: value });
      setErrors({ ...errors, [name]: null });
    } else {
      setErrors({ ...errors, [name]: "Invalid number" });
    }
  };

  const validateAll = () => {
    const newErr = {};
    Object.keys(initialState).forEach(k => {
      if (form[k] === "") newErr[k] = "Required";
    });
    setErrors(newErr);
    return Object.keys(newErr).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateAll()) return;
    setLoading(true);

    // Convert to floats
    const payload = {};
    Object.keys(form).forEach(k => payload[k] = parseFloat(form[k]));

    try {
      const res = await predictBreastCancer(payload); // service uses axios
      if (res?.prediction) {
        navigate("/result", { state: { prediction: res.prediction, type: "breast" } });
      } else if (res?.error) {
        alert("Prediction error: " + res.error);
      } else {
        alert("Unexpected response from server");
      }
    } catch (err) {
      console.error(err);
      alert("Prediction failed. Check inputs.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="breast-page">
      <div className="breast-form-card">
        <h2 className="breast-form-title">Breast Cancer Prediction</h2>
        <form onSubmit={handleSubmit}>
          {Object.keys(initialState).map((field) => (
            <div key={field} className="input-group">
              <input
                type="text"
                name={field}
                placeholder={field.replace(/_/g, " ")}
                value={form[field]}
                onChange={handleChange}
                step="any"
              />
              {errors[field] && <small className="input-error">{errors[field]}</small>}
            </div>
          ))}

          <button type="submit" className="btn-breast-submit" disabled={loading}>
            {loading ? "Predicting..." : "Predict"}
          </button>
        </form>
      </div>
    </div>
  );
}
