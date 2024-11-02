package org.reservations.GestionReservations.Repos;


import org.reservations.GestionReservations.Presistences.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservRep extends JpaRepository<Reservation, Long> {
    
}
