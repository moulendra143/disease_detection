package com.example.disease_detection.dto;

public class DiabetesInputDTO {
    private int age;
    private float bmi;
    private String gender;             // "male" or "female"
    private String familyHistory;      // "yes" or "no"
    private String frequentUrination;  // "yes" or "no"
    private String excessiveThirst;    // "yes" or "no"
    private String suddenWeightLoss;   // "yes" or "no"
    private String increasedAppetite;  // "yes" or "no"
    private String fatigue;            // "yes" or "no"
    private String blurredVision;      // "yes" or "no"
    private String slowHealingWounds;  // "yes" or "no"
    private String physicalActivity;   // "low", "moderate", "high"

    // Getters and Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public float getBmi() { return bmi; }
    public void setBmi(float bmi) { this.bmi = bmi; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getFamilyHistory() { return familyHistory; }
    public void setFamilyHistory(String familyHistory) { this.familyHistory = familyHistory; }

    public String getFrequentUrination() { return frequentUrination; }
    public void setFrequentUrination(String frequentUrination) { this.frequentUrination = frequentUrination; }

    public String getExcessiveThirst() { return excessiveThirst; }
    public void setExcessiveThirst(String excessiveThirst) { this.excessiveThirst = excessiveThirst; }

    public String getSuddenWeightLoss() { return suddenWeightLoss; }
    public void setSuddenWeightLoss(String suddenWeightLoss) { this.suddenWeightLoss = suddenWeightLoss; }

    public String getIncreasedAppetite() { return increasedAppetite; }
    public void setIncreasedAppetite(String increasedAppetite) { this.increasedAppetite = increasedAppetite; }

    public String getFatigue() { return fatigue; }
    public void setFatigue(String fatigue) { this.fatigue = fatigue; }

    public String getBlurredVision() { return blurredVision; }
    public void setBlurredVision(String blurredVision) { this.blurredVision = blurredVision; }

    public String getSlowHealingWounds() { return slowHealingWounds; }
    public void setSlowHealingWounds(String slowHealingWounds) { this.slowHealingWounds = slowHealingWounds; }

    public String getPhysicalActivity() { return physicalActivity; }
    public void setPhysicalActivity(String physicalActivity) { this.physicalActivity = physicalActivity; }
}
