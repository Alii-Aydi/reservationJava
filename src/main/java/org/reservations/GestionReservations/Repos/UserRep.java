package org.reservations.GestionReservations.Repos;


import org.reservations.GestionReservations.Presistences.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Long> {
    boolean existsByAddress(String address);
}
