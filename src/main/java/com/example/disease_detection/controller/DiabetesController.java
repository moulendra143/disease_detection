package com.example.disease_detection.controller;

import com.example.disease_detection.dto.DiabetesInput;
import com.example.disease_detection.model.DiabetesFormData;
import com.example.disease_detection.repository.DiabetesFormRepository;
import ai.onnxruntime.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/diabetes")
@CrossOrigin(origins = "*")
public class DiabetesController {

    private OrtEnvironment env;
    private OrtSession session;

    @Autowired
    private DiabetesFormRepository diabetesFormRepository;

    public DiabetesController() throws OrtException {
        env = OrtEnvironment.getEnvironment();
        session = env.createSession("src/main/resources/models/diabetes.onnx", new OrtSession.SessionOptions());
    }

    @PostMapping("/predict")
    public Map<String, Object> predict(@RequestBody DiabetesInput input) throws OrtException {
        // Basic input validation: we will ensure we have 8 numbers
        float[] features = new float[]{
            input.getPregnancies(),
            input.getGlucose(),
            input.getBloodPressure(),
            input.getSkinThickness(),
            input.getInsulin(),
            input.getBmi(),
            input.getDiabetesPedigreeFunction(),
            input.getAge()
        };

        // ONNX input
        OnnxTensor tensor = OnnxTensor.createTensor(env, new float[][]{features});

        OrtSession.Result result = session.run(Collections.singletonMap(session.getInputNames().iterator().next(), tensor));
        // Depending on your model output shape; try to extract sensibly
        Object raw = result.get(0).getValue();
        int predictionInt = 0;
        if (raw instanceof long[]) {
            predictionInt = (int) ((long[]) raw)[0];
        } else if (raw instanceof float[][]) {
            float[][] out = (float[][]) raw;
            predictionInt = out[0][0] > 0.5 ? 1 : 0;
        } else if (raw instanceof float[]) {
            predictionInt = ((float[]) raw)[0] > 0.5 ? 1 : 0;
        }

        String prediction = predictionInt == 1 ? "Diabetic" : "Non-Diabetic";

        // Persist a simplified form entry (map fields to DiabetesFormData; some fields are optional)
        // Persist all form fields
DiabetesFormData formData = new DiabetesFormData();
    formData.setAge(input.getAge());
    formData.setBmi(input.getBmi());
    formData.setPregnancies(input.getPregnancies());
    formData.setGlucose(input.getGlucose());
    formData.setBloodPressure(input.getBloodPressure());
    formData.setSkinThickness(input.getSkinThickness());
    formData.setInsulin(input.getInsulin());
    formData.setDiabetesPedigreeFunction(input.getDiabetesPedigreeFunction());
    formData.setResult(prediction);

    diabetesFormRepository.save(formData);


        Map<String, Object> response = new HashMap<>();
        response.put("prediction", prediction);
        response.put("saved", true);
        return response;
    }
}
