package com.example.disease_detection.controller;

import com.example.disease_detection.model.BreastCancerPrediction;
import com.example.disease_detection.dto.BreastCancerInputDTO;
import com.example.disease_detection.dto.BreastCancerOutputDTO;
import com.example.disease_detection.repository.BreastCancerPredictionRepository;
import com.example.disease_detection.service.ONNXService;
import ai.onnxruntime.OrtException; // import OrtException
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/breast-cancer")
public class BreastCancerController {

    @Autowired
    private BreastCancerPredictionRepository repository;

    @Autowired
    private ONNXService onnxService;

    @PostMapping("/predict")
    public ResponseEntity<BreastCancerOutputDTO> predict(@RequestBody BreastCancerInputDTO input) {
        try {
            // Prepare features for ONNX model
            float[] features = new float[]{
                input.getAge(),
                input.getBmi()
                
            };

            // Call ONNX model
            float risk = onnxService.predictBreastCancer(features);

            // Save prediction in database
            BreastCancerPrediction entity = new BreastCancerPrediction();
            entity.setAge(input.getAge());
            entity.setBmi(input.getBmi());
            entity.setRiskScore(risk);
            repository.save(entity);

            // Prepare response
            BreastCancerOutputDTO output = new BreastCancerOutputDTO();
            output.setRisk(risk);
            output.setMessage(risk > 0.5f ? "High risk of breast cancer" : "Low risk");

            return ResponseEntity.ok(output);

        } catch (OrtException e) {
            e.printStackTrace();
            // Return 500 error if prediction fails
            return ResponseEntity.status(500).body(null);
        }
    }
}
