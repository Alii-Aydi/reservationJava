package org.reservations.GestionReservations.Presistences;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @DateTimeFormat(pattern = "HH:mm")
    private String startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private String endTime;

    @OneToMany(mappedBy = "timeSlot",cascade = CascadeType.ALL)
    private List<Reservation> reservations=new ArrayList<>();

    public TimeSlot(LocalDate date, String startTime, String endTime) {
	super();
	this.date = date;
	this.startTime = startTime;
	this.endTime = endTime;
    }

    public void reserverPlage(Reservation r) {
	this.reservations.add(r);
	r.setTimeSlot(this);
    }

    public void librerPlage(Reservation r) {
	this.reservations.remove(r);
	r.setTimeSlot(null);
    }

    public boolean isLibre(Room room) {
	for (Reservation r : reservations) { 
	    if (room.getRoomNumber()==r.getRoom().getRoomNumber()&&this.date.isEqual(r.getTimeSlot().getDate())
		    && (LocalTime.parse(this.startTime).isBefore(LocalTime.parse(r.getTimeSlot().getEndTime()))
			    || LocalTime.parse(this.endTime).isAfter(LocalTime.parse(r.getTimeSlot().getStartTime())))) {
		System.out.println("executed fft");
		return false;
	    }
	}
	return true;
    }

    @Override
    public int hashCode() {
	return Objects.hash(date, endTime, startTime);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	TimeSlot other = (TimeSlot) obj;
	return Objects.equals(date, other.date) && Objects.equals(endTime, other.endTime)
		&& Objects.equals(startTime, other.startTime);
    }

    @Override
    public String toString() {
	return "TimeSlot [date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", reservations="
		+ reservations + "]";
    }

    public TimeSlot() {
	super();
    }

    public LocalDate getDate() {
	return date;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public String getStartTime() {
	return startTime;
    }

    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    public String getEndTime() {
	return endTime;
    }

    public void setEndTime(String endTime) {
	this.endTime = endTime;
    }

    public List<Reservation> getReservations() {
	return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
	this.reservations = reservations;
    }

    // Constructors, getters, and setters
}
