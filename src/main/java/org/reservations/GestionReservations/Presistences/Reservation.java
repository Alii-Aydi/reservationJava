package org.reservations.GestionReservations.Presistences;

import java.time.Duration;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationNumber;
    private double price;
    private boolean paid;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @OneToOne
    @JoinColumn(name = "invoice")
    private Invoice facture;
    // Constructors, getters, and setters

    public Long getReservationNumber() {
	return reservationNumber;
    }

    public Reservation(Long reservationNumber, double price, boolean paid, Room room, User user, TimeSlot timeSlot,
	    Invoice facture) {
	super();
	this.reservationNumber = reservationNumber;
	this.price = price;
	this.paid = paid;
	this.room = room;
	this.user = user;
	this.timeSlot = timeSlot;
	this.facture = facture;
    }

    public Reservation() {
	super();
    }
    public void setReservationNumber(Long reservationNumber) {
	this.reservationNumber = reservationNumber;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public boolean isPaid() {
	return paid;
    }

    public void setPaid(boolean paid) {
	this.paid = paid;
    }

    public Room getRoom() {
	return room;
    }

    public void setRoom(Room room) {
	this.room = room;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public TimeSlot getTimeSlot() {
	return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
	this.timeSlot = timeSlot;
    }

    public Invoice getFacture() {
	return facture;
    }

    public void setFacture(Invoice facture) {
	this.facture = facture;
    }
    
    public void calculerPrix() {
	Duration d= Duration.between(LocalTime.parse(this.timeSlot.getEndTime()), LocalTime.parse(this.timeSlot.getStartTime()));
	long hours = d.toHours();
        // Define cost per hour
        double costPerHour = 10.0;
        // Calculate the total cost
        double totalCost = hours * costPerHour;
	this.price=Math.abs(this.room.getArea()*0.5+totalCost);
    }
    
    void annuler() {
	this.timeSlot.librerPlage(this);
    }
    
    void confirmer() {
	this.timeSlot.reserverPlage(this);
    }

    @Override
    public String toString() {
	return "Reservation [reservationNumber=" + reservationNumber + ", price=" + price + ", paid=" + paid + ", room="
		+ room + ", user=" + user + ", timeSlot=" + timeSlot + ", facture=" + facture + "]";
    }

}
