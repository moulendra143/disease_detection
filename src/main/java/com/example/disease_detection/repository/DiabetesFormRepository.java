package com.example.disease_detection.repository;

import com.example.disease_detection.model.DiabetesFormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiabetesFormRepository extends JpaRepository<DiabetesFormData, Long> {
}
