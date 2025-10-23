package com.example.disease_detection.service;

import ai.onnxruntime.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Map;

@Service
public class ONNXService {

    private OrtEnvironment env;
    private OrtSession diabetesSession;
    private OrtSession breastCancerSession;

    public ONNXService() throws OrtException, URISyntaxException {
        env = OrtEnvironment.getEnvironment();

        // Load diabetes model from classpath
        File diabetesFile = new File(getClass().getClassLoader().getResource("models/diabetes.onnx").toURI());
        diabetesSession = env.createSession(diabetesFile.getAbsolutePath(), new OrtSession.SessionOptions());

        // Load breast cancer model from classpath
        File breastFile = new File(getClass().getClassLoader().getResource("models/breast_cancer.onnx").toURI());
        breastCancerSession = env.createSession(breastFile.getAbsolutePath(), new OrtSession.SessionOptions());
    }

    public float predictDiabetes(float[] input) throws OrtException {
        OnnxTensor tensor = OnnxTensor.createTensor(env, new float[][]{input});
        OrtSession.Result result = diabetesSession.run(
                Map.of(diabetesSession.getInputNames().iterator().next(), tensor));
        float[][] output = (float[][]) result.get(0).getValue();
        return output[0][0];
    }

    public float predictBreastCancer(float[] input) throws OrtException {
        OnnxTensor tensor = OnnxTensor.createTensor(env, new float[][]{input});
        OrtSession.Result result = breastCancerSession.run(
                Map.of(breastCancerSession.getInputNames().iterator().next(), tensor));
        float[][] output = (float[][]) result.get(0).getValue();
        return output[0][0];
    }
}
