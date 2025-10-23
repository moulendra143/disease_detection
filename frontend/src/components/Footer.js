import React from "react";

export default function Footer() {
  return (
    <footer className="bg-dark text-light text-center p-3 mt-5">
      <p>© {new Date().getFullYear()} AI Disease Detection | Powered by ONNX + Spring Boot</p>
    </footer>
  );
}
