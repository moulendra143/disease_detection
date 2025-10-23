import React from "react";

export default function BreastCancerResult({ result }) {
  return (
    <div className="alert alert-info mt-3">
      <h5>Prediction Result:</h5>
      <p>{result.prediction}</p>
    </div>
  );
}
