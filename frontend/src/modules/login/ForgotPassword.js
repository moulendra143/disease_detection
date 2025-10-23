import React, { useState } from "react";
import axios from "axios";
import "./login.css";
import { useNavigate } from "react-router-dom";

export default function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [otpShown, setOtpShown] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    try {
      const res = await axios.post("http://localhost:8080/api/auth/forgot-password", { email });
      if (res.status === 200) {
        setMessage("OTP sent (dev mode). Use it to reset your password.");
        // Dev-mode: OTP returned in response
        if (res.data?.otp) setOtpShown(res.data.otp);
        setTimeout(() => navigate("/reset-password"), 1200);
      }
    } catch (err) {
      setMessage(err.response?.data?.message || "Error: Email not found");
    }
  };

  return (
    <div className="auth-bg">
      <div className="login-card">
        <h2>Forgot Password</h2>
        <form onSubmit={handleSubmit}>
          <input type="email" placeholder="Enter your registered email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          <button type="submit" className="primary-btn">Send OTP</button>
        </form>
        {message && <p className="message">{message}</p>}
        {otpShown && <p className="message">(Dev OTP: {otpShown})</p>}
      </div>
    </div>
  );
}
