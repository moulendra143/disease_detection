package com.example.disease_detection.controller;

import com.example.disease_detection.model.DiabetesPrediction;
import com.example.disease_detection.dto.DiabetesInputDTO;
import com.example.disease_detection.dto.DiabetesOutputDTO;
import com.example.disease_detection.repository.DiabetesPredictionRepository;
import com.example.disease_detection.service.ONNXService;
import ai.onnxruntime.OrtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diabetes")
public class DiabetesController {

    @Autowired
    private DiabetesPredictionRepository repository;

    @Autowired
    private ONNXService onnxService;

    @PostMapping("/predict")
    public ResponseEntity<DiabetesOutputDTO> predict(@RequestBody DiabetesInputDTO input) {
        try {
            // Convert input DTO to float array for ONNX model
            float[] features = new float[]{
                (float) input.getAge(),
                (float) input.getBmi()
                // Map other features: encode yes/no and male/female as 1/0
            };

            // Predict using ONNX
            float risk = onnxService.predictDiabetes(features);

            // Save to DB
            DiabetesPrediction entity = new DiabetesPrediction();
            entity.setAge((float) input.getAge());
            entity.setBmi((float) input.getBmi());
            // Set other fields if needed
            entity.setRiskScore(risk);
            repository.save(entity);

            // Prepare output
            DiabetesOutputDTO output = new DiabetesOutputDTO();
            output.setRisk(risk);
            output.setMessage(risk > 0.5f ? "High risk of diabetes" : "Low risk");

            return ResponseEntity.ok(output);

        } catch (OrtException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
