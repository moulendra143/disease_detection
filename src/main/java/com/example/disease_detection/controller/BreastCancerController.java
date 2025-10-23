package com.example.disease_detection.controller;

import com.example.disease_detection.dto.BreastCancerInputDTO;
import com.example.disease_detection.model.BreastCancer;
import com.example.disease_detection.repository.BreastCancerRepository;
import com.example.disease_detection.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ai.onnxruntime.OrtException;

import java.util.Map;

@RestController
@RequestMapping("/api/breast-cancer")
@CrossOrigin(origins = "*")
public class BreastCancerController {

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private BreastCancerRepository breastCancerRepository;

    @PostMapping("/predict")
    public Map<String, Object> predict(@RequestBody BreastCancerInputDTO input) {
        try {
            // Run prediction
            String prediction = predictionService.predictBreastCancer(input);

            // Save to database
            BreastCancer bc = new BreastCancer();
            bc.setRadius_mean(input.getRadius_mean());
            bc.setTexture_mean(input.getTexture_mean());
            bc.setPerimeter_mean(input.getPerimeter_mean());
            bc.setArea_mean(input.getArea_mean());
            bc.setSmoothness_mean(input.getSmoothness_mean());
            bc.setCompactness_mean(input.getCompactness_mean());
            bc.setConcavity_mean(input.getConcavity_mean());
            bc.setConcave_points_mean(input.getConcave_points_mean());
            bc.setSymmetry_mean(input.getSymmetry_mean());
            bc.setFractal_dimension_mean(input.getFractal_dimension_mean());

            bc.setRadius_se(input.getRadius_se());
            bc.setTexture_se(input.getTexture_se());
            bc.setPerimeter_se(input.getPerimeter_se());
            bc.setArea_se(input.getArea_se());
            bc.setSmoothness_se(input.getSmoothness_se());
            bc.setCompactness_se(input.getCompactness_se());
            bc.setConcavity_se(input.getConcavity_se());
            bc.setConcave_points_se(input.getConcave_points_se());
            bc.setSymmetry_se(input.getSymmetry_se());
            bc.setFractal_dimension_se(input.getFractal_dimension_se());

            bc.setRadius_worst(input.getRadius_worst());
            bc.setTexture_worst(input.getTexture_worst());
            bc.setPerimeter_worst(input.getPerimeter_worst());
            bc.setArea_worst(input.getArea_worst());
            bc.setSmoothness_worst(input.getSmoothness_worst());
            bc.setCompactness_worst(input.getCompactness_worst());
            bc.setConcavity_worst(input.getConcavity_worst());
            bc.setConcave_points_worst(input.getConcave_points_worst());
            bc.setSymmetry_worst(input.getSymmetry_worst());
            bc.setFractal_dimension_worst(input.getFractal_dimension_worst());

            // **Set prediction result**
            bc.setDiagnosis(prediction);

            // Save entity
            breastCancerRepository.save(bc);

            return Map.of("prediction", prediction, "saved", true);
        } catch (OrtException e) {
            e.printStackTrace();
            return Map.of("error", "Error predicting breast cancer: " + e.getMessage());
        } catch (IllegalArgumentException iae) {
            return Map.of("error", "Invalid input: " + iae.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return Map.of("error", "Unexpected error: " + ex.getMessage());
        }
    }
}
