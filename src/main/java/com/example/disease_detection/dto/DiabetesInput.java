package com.example.disease_detection.dto;

public class DiabetesInput {
    private float pregnancies;
    private float glucose;
    private float bloodPressure;
    private float skinThickness;
    private float insulin;
    private float bmi;
    private float diabetesPedigreeFunction;
    private int age;

    // Getters and Setters
    public float getPregnancies() { return pregnancies; }
    public void setPregnancies(float pregnancies) { this.pregnancies = pregnancies; }

    public float getGlucose() { return glucose; }
    public void setGlucose(float glucose) { this.glucose = glucose; }

    public float getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(float bloodPressure) { this.bloodPressure = bloodPressure; }

    public float getSkinThickness() { return skinThickness; }
    public void setSkinThickness(float skinThickness) { this.skinThickness = skinThickness; }

    public float getInsulin() { return insulin; }
    public void setInsulin(float insulin) { this.insulin = insulin; }

    public float getBmi() { return bmi; }
    public void setBmi(float bmi) { this.bmi = bmi; }

    public float getDiabetesPedigreeFunction() { return diabetesPedigreeFunction; }
    public void setDiabetesPedigreeFunction(float diabetesPedigreeFunction) { this.diabetesPedigreeFunction = diabetesPedigreeFunction; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
