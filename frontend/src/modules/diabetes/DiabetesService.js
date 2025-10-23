// Diabetes
import axios from "axios";

export const predictDiabetes = async (data) => {
  const response = await axios.post("http://localhost:8080/predict-diabetes", data, {
    headers: { "Content-Type": "application/json" }
  });
  return response.data;
};