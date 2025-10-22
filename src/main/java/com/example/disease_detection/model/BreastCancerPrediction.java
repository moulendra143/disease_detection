package com.example.disease_detection.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BreastCancerPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer age;
    private Float bmi;
    private Float meanRadius;
    private Float meanTexture;
    private Float meanPerimeter;
    private Float meanArea;
    private Float meanSmoothness;
    private Float meanCompactness;
    private Float meanConcavity;
    private Float meanConcavePoints;
    private Float meanSymmetry;
    private Float meanFractalDimension;
    private String prediction;
    private Float riskScore;
    private LocalDateTime timestamp;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Float getBmi() { return bmi; }
    public void setBmi(Float bmi) { this.bmi = bmi; }

    public Float getMeanRadius() { return meanRadius; }
    public void setMeanRadius(Float meanRadius) { this.meanRadius = meanRadius; }

    public Float getMeanTexture() { return meanTexture; }
    public void setMeanTexture(Float meanTexture) { this.meanTexture = meanTexture; }

    public Float getMeanPerimeter() { return meanPerimeter; }
    public void setMeanPerimeter(Float meanPerimeter) { this.meanPerimeter = meanPerimeter; }

    public Float getMeanArea() { return meanArea; }
    public void setMeanArea(Float meanArea) { this.meanArea = meanArea; }

    public Float getMeanSmoothness() { return meanSmoothness; }
    public void setMeanSmoothness(Float meanSmoothness) { this.meanSmoothness = meanSmoothness; }

    public Float getMeanCompactness() { return meanCompactness; }
    public void setMeanCompactness(Float meanCompactness) { this.meanCompactness = meanCompactness; }

    public Float getMeanConcavity() { return meanConcavity; }
    public void setMeanConcavity(Float meanConcavity) { this.meanConcavity = meanConcavity; }

    public Float getMeanConcavePoints() { return meanConcavePoints; }
    public void setMeanConcavePoints(Float meanConcavePoints) { this.meanConcavePoints = meanConcavePoints; }

    public Float getMeanSymmetry() { return meanSymmetry; }
    public void setMeanSymmetry(Float meanSymmetry) { this.meanSymmetry = meanSymmetry; }

    public Float getMeanFractalDimension() { return meanFractalDimension; }
    public void setMeanFractalDimension(Float meanFractalDimension) { this.meanFractalDimension = meanFractalDimension; }

    public String getPrediction() { return prediction; }
    public void setPrediction(String prediction) { this.prediction = prediction; }

    public Float getRiskScore() { return riskScore; }
    public void setRiskScore(Float riskScore) { this.riskScore = riskScore; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
