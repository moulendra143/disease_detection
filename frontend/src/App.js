// src/App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";

import HomePage from "./modules/home/HomePage";
import DiabetesForm from "./modules/diabetes/DiabetesForm";
import BreastCancerForm from "./modules/breast_cancer/BreastCancerForm";
import LoginForm from "./modules/login/LoginForm";
import SignupForm from "./modules/login/SignupForm";
import ForgotPassword from "./modules/login/ForgotPassword";
import ResetPassword from "./modules/login/ResetPassword";
import ResultPage from "./modules/result/ResultPage";

export default function App() {
  const storedUser = localStorage.getItem("user");
  const user = storedUser && storedUser !== "undefined" ? JSON.parse(storedUser) : null;

  return (
    <Router>
      <Routes>
        {/* Always load login at root */}
        <Route path="/" element={<Navigate to="/login" />} />

        {/* Public routes */}
        <Route path="/login" element={<LoginForm />} />
        <Route path="/signup" element={<SignupForm />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/reset-password" element={<ResetPassword />} />

        {/* Protected routes */}
        <Route path="/home" element={user ? <HomePage /> : <Navigate to="/login" />} />
        <Route path="/diabetes" element={user ? <DiabetesForm /> : <Navigate to="/login" />} />
        <Route path="/breast-cancer" element={user ? <BreastCancerForm /> : <Navigate to="/login" />} />
        <Route path="/result" element={user ? <ResultPage /> : <Navigate to="/login" />} />
      </Routes>
    </Router>
  );
}
