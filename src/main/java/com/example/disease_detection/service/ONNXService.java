package com.example.disease_detection.service;

import ai.onnxruntime.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ONNXService {

    private OrtEnvironment env;
    private OrtSession diabetesSession;
    private OrtSession breastCancerSession;

    public ONNXService() throws OrtException {
        env = OrtEnvironment.getEnvironment();
        diabetesSession = env.createSession("src/main/resources/models/diabetes.onnx", new OrtSession.SessionOptions());
        breastCancerSession = env.createSession("src/main/resources/models/breast_cancer.onnx", new OrtSession.SessionOptions());
    }

    public float predictDiabetes(float[] input) throws OrtException {
        OnnxTensor tensor = OnnxTensor.createTensor(env, new float[][]{input});
        OrtSession.Result result = diabetesSession.run(Map.of(diabetesSession.getInputNames().iterator().next(), tensor));
        float[][] output = (float[][]) result.get(0).getValue();
        return output[0][0];
    }

    public float predictBreastCancer(float[] input) throws OrtException {
        OnnxTensor tensor = OnnxTensor.createTensor(env, new float[][]{input});
        OrtSession.Result result = breastCancerSession.run(Map.of(breastCancerSession.getInputNames().iterator().next(), tensor));
        float[][] output = (float[][]) result.get(0).getValue();
        return output[0][0];
    }
}
