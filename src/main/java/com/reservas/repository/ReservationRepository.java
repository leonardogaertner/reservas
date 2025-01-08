package com.reservas.repository;

import com.reservas.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByResourceIdAndStartTimeBetweenOrEndTimeBetween(
        Long resourceId, LocalDateTime start, LocalDateTime end, LocalDateTime startConflict, LocalDateTime endConflict);
}
