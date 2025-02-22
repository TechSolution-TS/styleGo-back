package com.techsolution.stylego.repository;

import com.techsolution.stylego.model.Barber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Long> {

    List<Barber> findAllByOnlineTrue();

    @Transactional
    @Modifying
    @Query("UPDATE Barber b SET b.online = true WHERE b.id = :barberId")
    int updateOnlineStatusToTrue(Long barberId);

    @Transactional
    @Modifying
    @Query("UPDATE Barber b SET b.online = false WHERE b.online = true")
    void setAllOffline();

    @Query("SELECT b FROM Barber b WHERE " +
            "(:numberCuts IS NULL OR b.numberCuts > :numberCuts OR b.numberCuts = :numberCuts) " +
            "AND (:online IS NULL OR b.online = :online) " +
            "AND (:assessment IS NULL OR " +
            "COALESCE((SELECT AVG(a.assessment) FROM BarberAssessment a WHERE a.barber = b), 0.0) = :assessment)")
    Page<Barber> findByFilters(@Param("numberCuts") Integer numberCuts,
                               @Param("online") Boolean online,
                               @Param("assessment") Double assessment,
                               Pageable pageable);

    @Query("SELECT b FROM Barber b JOIN b.user u WHERE u.uuid = :barberUuid")
    Optional<Barber> findByUserUuid(@Param("barberUuid") String barberUuid);
}
