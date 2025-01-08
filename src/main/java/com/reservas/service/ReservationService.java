package com.reservas.service;

import java.time.LocalDateTime;
import java.util.List;
import com.reservas.model.*;
import org.springframework.stereotype.Service;

import com.reservas.model.Reservation;
import com.reservas.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada!"));
    }

    public boolean isConflict(Long resourceId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Reservation> conflicts = reservationRepository.findByResourceIdAndStartTimeBetweenOrEndTimeBetween(
            resourceId, startTime, endTime, startTime, endTime);
        return !conflicts.isEmpty();
    }

    public Reservation createReservation(Reservation reservation) {
        if (isConflict(reservation.getResource().getId(), reservation.getStartTime(), reservation.getEndTime())) {
            throw new IllegalArgumentException("Conflito de horário!");
        }
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = getReservationById(id);
        reservation.setStartTime(reservationDetails.getStartTime());
        reservation.setEndTime(reservationDetails.getEndTime());
        reservation.setResource(reservationDetails.getResource());
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }
}
