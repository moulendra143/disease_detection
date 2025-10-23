// src/modules/breast_cancer/BreastCancerService.js
import axios from "axios";

/**
 * Sends breast cancer input data to backend for prediction
 * @param {Object} data - 30 feature values, all numbers
 * @returns {Promise<Object>} - { prediction: "Benign" | "Malignant" }
 */
export const predictBreastCancer = async (data) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/breast-cancer/predict",
      data,
      {
        headers: { "Content-Type": "application/json" },
      }
    );

    // Backend now always returns { prediction: "Benign" | "Malignant" }
    return response.data;
  } catch (error) {
    console.error("Prediction error:", error);
    return { error: error.response?.data?.message || error.message };
  }
};
