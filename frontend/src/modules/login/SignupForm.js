import React, { useState } from "react";
import axios from "axios";
import "./login.css";
import { useNavigate } from "react-router-dom";

export default function SignupForm() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ fullName: "", email: "", password: "", confirmPassword: "" });
  const [error, setError] = useState("");
  const [showPwd, setShowPwd] = useState(false);
  const [strengthMsg, setStrengthMsg] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
    if (name === "password") setStrengthMsg(checkStrength(value));
  };

  const checkStrength = (pwd) => {
    if (!pwd) return "";
    if (pwd.length < 8) return "Too short (min 8)";
    const upper = /[A-Z]/.test(pwd);
    const lower = /[a-z]/.test(pwd);
    const digit = /[0-9]/.test(pwd);
    const special = /[!@#$%^&*(),.?":{}|<>]/.test(pwd);
    const passed = [upper, lower, digit, special].filter(Boolean).length;
    if (passed < 3) return "Weak: include upper, lower, number and symbol";
    return "Strong password";
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    if (form.password !== form.confirmPassword) {
      setError("Passwords do not match"); return;
    }
    if (checkStrength(form.password) !== "Strong password") {
      setError("Password not strong enough"); return;
    }

    try {
      const res = await axios.post("http://localhost:8080/api/auth/signup", form);
      if (res.status === 200) {
        alert("Signup successful! Please login.");
        navigate("/login");
      } else {
        setError(res.data?.message || "Signup error");
      }
    } catch (err) {
      setError(err.response?.data?.message || "Something went wrong. Try again.");
    }
  };

  return (
    <div className="auth-bg">
      <div className="login-card">
        <h2>Create Account</h2>
        {error && <div className="error">{error}</div>}
        <form onSubmit={handleSubmit}>
          <input type="text" name="fullName" placeholder="Full Name" value={form.fullName} onChange={handleChange} required />
          <input type="email" name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
          <div className="password-row">
            <input
              type={showPwd ? "text" : "password"}
              name="password"
              placeholder="Password"
              value={form.password}
              onChange={handleChange}
              required
            />
            <button type="button" className="eye-btn" onClick={() => setShowPwd(s => !s)}>{showPwd ? "🙈" : "👁️"}</button>
          </div>
          <div className="pwd-strength">{strengthMsg}</div>
          <div className="password-row">
            <input
              type={showPwd ? "text" : "password"}
              name="confirmPassword"
              placeholder="Confirm Password"
              value={form.confirmPassword}
              onChange={handleChange}
              required
            />
          </div>
          <button type="submit" className="primary-btn">Sign Up</button>
        </form>
        <p className="small">
          Already have an account? <span className="link" onClick={() => navigate("/login")}>Login</span>
        </p>
      </div>
    </div>
  );
}
