package com.example.disease_detection.repository;

import com.example.disease_detection.model.BreastCancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreastCancerRepository extends JpaRepository<BreastCancer, Long> {}
