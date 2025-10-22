package com.example.disease_detection.repository;

import com.example.disease_detection.model.DiabetesPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiabetesPredictionRepository extends JpaRepository<DiabetesPrediction, Long> {}
