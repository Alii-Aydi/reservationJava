package org.reservations.GestionReservations.Repos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.reservations.GestionReservations.Presistences.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRep extends JpaRepository<TimeSlot, LocalDate> {

    //Optional<TimeSlot> findByDate(LocalDate date);

    List<TimeSlot> findAllByDate(LocalDate date);

    //TimeSlot findByDateAndStartTimeAndEndTime(LocalDate id, String startTime, String endTime);

}
