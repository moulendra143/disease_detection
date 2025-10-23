import React from "react";

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container">
        <a className="navbar-brand" href="/">Disease Detection</a>
        <div className="navbar-nav">
          <a className="nav-link" href="/diabetes">Diabetes</a>
          <a className="nav-link" href="/breast-cancer">Breast Cancer</a>
        </div>
      </div>
    </nav>
  );
}
