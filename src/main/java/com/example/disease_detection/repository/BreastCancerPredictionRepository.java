package com.example.disease_detection.repository;

import com.example.disease_detection.model.BreastCancerPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreastCancerPredictionRepository extends JpaRepository<BreastCancerPrediction, Long> {}
