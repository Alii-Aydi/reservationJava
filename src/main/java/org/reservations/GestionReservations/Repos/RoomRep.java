package org.reservations.GestionReservations.Repos;

import org.reservations.GestionReservations.Presistences.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRep extends JpaRepository<Room, Long> {
    
}
