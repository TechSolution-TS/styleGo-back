package com.techsolution.stylego.repository;

import com.techsolution.stylego.model.BarberAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BarberAssessmentRepository extends JpaRepository<BarberAssessment, Long> {

    @Query("SELECT AVG(b.assessment) FROM BarberAssessment b WHERE b.barber.id = :barberId")
    Double findAverageAssessmentByBarberId(@Param("barberId") Long barberId);
}
