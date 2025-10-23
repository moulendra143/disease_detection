import React, { useState } from "react";
import axios from "axios";
import "./login.css";
import { useNavigate } from "react-router-dom";

export default function ResetPassword() {
  const [form, setForm] = useState({ email: "", otp: "", newPassword: "" });
  const [message, setMessage] = useState("");
  const [showPwd, setShowPwd] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/api/auth/reset-password", form);
      if (res.status === 200) {
        setMessage("Password reset successful! Redirecting to login...");
        setTimeout(() => navigate("/login"), 1200);
      } else {
        setMessage(res.data?.message || "Reset failed");
      }
    } catch (err) {
      setMessage(err.response?.data?.message || "Invalid OTP or Email");
    }
  };

  return (
    <div className="auth-bg">
      <div className="login-card">
        <h2>Reset Password</h2>
        <form onSubmit={handleSubmit}>
          <input type="email" name="email" placeholder="Registered Email" value={form.email} onChange={handleChange} required />
          <input type="text" name="otp" placeholder="Enter OTP" value={form.otp} onChange={handleChange} required />
          <div className="password-row">
            <input type={showPwd ? "text" : "password"} name="newPassword" placeholder="New Password" value={form.newPassword} onChange={handleChange} required />
            <button type="button" className="eye-btn" onClick={() => setShowPwd(s => !s)}>{showPwd ? "🙈" : "👁️"}</button>
          </div>
          <button type="submit" className="primary-btn">Reset Password</button>
        </form>
        {message && <p className="message">{message}</p>}
      </div>
    </div>
  );
}
