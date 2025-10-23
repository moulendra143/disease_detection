package com.example.disease_detection.service;

import ai.onnxruntime.*;
import com.example.disease_detection.dto.BreastCancerInputDTO;
import com.example.disease_detection.dto.DiabetesInput;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class PredictionService {

    private final OrtEnvironment env;
    private final OrtSession diabetesSession;
    private final OrtSession breastSession;

    public PredictionService() throws OrtException {
        env = OrtEnvironment.getEnvironment();
        diabetesSession = env.createSession("src/main/resources/models/diabetes.onnx", new OrtSession.SessionOptions());
        breastSession = env.createSession("src/main/resources/models/breast_cancer.onnx", new OrtSession.SessionOptions());
    }

    /** Predict Diabetes from float array */
    public String predictDiabetes(float[] input) throws OrtException {
        if (input.length != 8) throw new IllegalArgumentException("Diabetes input must have 8 features");

        try (OnnxTensor tensor = OnnxTensor.createTensor(env, new float[][]{input});
             OrtSession.Result result = diabetesSession.run(Collections.singletonMap("input", tensor))) {
            float[][] output = (float[][]) result.get(0).getValue();
            return output[0][0] > 0.5 ? "Diabetic" : "Non-Diabetic";
        }
    }

    /** Predict Diabetes from DTO */
    public String predictDiabetes(DiabetesInput input) throws OrtException {
        float[] features = new float[]{
                input.getPregnancies(), input.getGlucose(), input.getBloodPressure(),
                input.getSkinThickness(), input.getInsulin(), input.getBmi(),
                input.getDiabetesPedigreeFunction(), input.getAge()
        };
        return predictDiabetes(features);
    }

    /** Predict Breast Cancer from double array */
    public String predictBreastCancer(double[] input) throws OrtException {
        if (input.length != 30) throw new IllegalArgumentException("Breast Cancer input must have 30 features");

        float[] floatInput = new float[input.length];
        for (int i = 0; i < input.length; i++) floatInput[i] = (float) input[i];

        try (OnnxTensor tensor = OnnxTensor.createTensor(env, new float[][]{floatInput});
             OrtSession.Result result = breastSession.run(Collections.singletonMap("float_input", tensor))) {

            Object labelObj = result.get(0).getValue();
            String predictedLabel;

            if (labelObj instanceof String[]) {
                predictedLabel = ((String[]) labelObj)[0];
            } else if (labelObj instanceof String[][]) {
                predictedLabel = ((String[][]) labelObj)[0][0];
            } else if (labelObj instanceof Object[]) {
                predictedLabel = ((Object[]) labelObj)[0].toString();
            } else {
                throw new IllegalStateException("Unexpected output type: " + labelObj.getClass());
            }

            return predictedLabel.equalsIgnoreCase("0") || predictedLabel.equalsIgnoreCase("Benign")
                    ? "Benign"
                    : "Malignant";
        }
    }

    /** Predict Breast Cancer from DTO */
    public String predictBreastCancer(BreastCancerInputDTO input) throws OrtException {
        double[] features = new double[]{
                input.getRadius_mean(), input.getTexture_mean(), input.getPerimeter_mean(), input.getArea_mean(),
                input.getSmoothness_mean(), input.getCompactness_mean(), input.getConcavity_mean(), input.getConcave_points_mean(),
                input.getSymmetry_mean(), input.getFractal_dimension_mean(), input.getRadius_se(), input.getTexture_se(),
                input.getPerimeter_se(), input.getArea_se(), input.getSmoothness_se(), input.getCompactness_se(),
                input.getConcavity_se(), input.getConcave_points_se(), input.getSymmetry_se(), input.getFractal_dimension_se(),
                input.getRadius_worst(), input.getTexture_worst(), input.getPerimeter_worst(), input.getArea_worst(),
                input.getSmoothness_worst(), input.getCompactness_worst(), input.getConcavity_worst(), input.getConcave_points_worst(),
                input.getSymmetry_worst(), input.getFractal_dimension_worst()
        };
        return predictBreastCancer(features);
    }
}
