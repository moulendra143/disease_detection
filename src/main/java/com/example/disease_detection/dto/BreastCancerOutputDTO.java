package com.example.disease_detection.dto;

public class BreastCancerOutputDTO {

    private float risk;
    private String message;

    public BreastCancerOutputDTO() {}

    public float getRisk() { return risk; }
    public void setRisk(float risk) { this.risk = risk; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
