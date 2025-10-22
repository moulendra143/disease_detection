package com.example.disease_detection.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DiabetesPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float age;
    private Float bmi;
    private Float glucose;
    private Float insulin;
    private String prediction;
    private Float riskScore;
    private LocalDateTime timestamp;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Float getAge() { return age; }
    public void setAge(Float age) { this.age = age; }

    public Float getBmi() { return bmi; }
    public void setBmi(Float bmi) { this.bmi = bmi; }

    public Float getGlucose() { return glucose; }
    public void setGlucose(Float glucose) { this.glucose = glucose; }

    public Float getInsulin() { return insulin; }
    public void setInsulin(Float insulin) { this.insulin = insulin; }

    public String getPrediction() { return prediction; }
    public void setPrediction(String prediction) { this.prediction = prediction; }

    public Float getRiskScore() { return riskScore; }
    public void setRiskScore(Float riskScore) { this.riskScore = riskScore; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
