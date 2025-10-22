package com.example.disease_detection.dto;

public class BreastCancerInputDTO {
    private int age;
    private float bmi;
    private String menopause;          // "premeno" or "postmeno"
    private String familyHistory;      // "yes" or "no"
    private String lumpPresent;        // "yes" or "no"
    private String nippleDischarge;    // "yes" or "no"
    private String breastPain;         // "yes" or "no"
    private String skinChanges;        // "yes" or "no"
    private String priorBreastDisease; // "yes" or "no"
    private String hormoneTherapy;     // "yes" or "no"

    // Getters and Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public float getBmi() { return bmi; }
    public void setBmi(float bmi) { this.bmi = bmi; }

    public String getMenopause() { return menopause; }
    public void setMenopause(String menopause) { this.menopause = menopause; }

    public String getFamilyHistory() { return familyHistory; }
    public void setFamilyHistory(String familyHistory) { this.familyHistory = familyHistory; }

    public String getLumpPresent() { return lumpPresent; }
    public void setLumpPresent(String lumpPresent) { this.lumpPresent = lumpPresent; }

    public String getNippleDischarge() { return nippleDischarge; }
    public void setNippleDischarge(String nippleDischarge) { this.nippleDischarge = nippleDischarge; }

    public String getBreastPain() { return breastPain; }
    public void setBreastPain(String breastPain) { this.breastPain = breastPain; }

    public String getSkinChanges() { return skinChanges; }
    public void setSkinChanges(String skinChanges) { this.skinChanges = skinChanges; }

    public String getPriorBreastDisease() { return priorBreastDisease; }
    public void setPriorBreastDisease(String priorBreastDisease) { this.priorBreastDisease = priorBreastDisease; }

    public String getHormoneTherapy() { return hormoneTherapy; }
    public void setHormoneTherapy(String hormoneTherapy) { this.hormoneTherapy = hormoneTherapy; }
}
