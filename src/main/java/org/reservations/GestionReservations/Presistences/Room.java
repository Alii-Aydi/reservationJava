package org.reservations.GestionReservations.Presistences;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Room {
    @Id
    private Long roomNumber;
    private double area;
    
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations= new ArrayList<>();

    public Room(Long roomNumber, double area) {
	super();
	this.roomNumber = roomNumber;
	this.area = area;
    }

    public Room() {
	super();
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
	return "Room [roomNumber=" + roomNumber + ", area=" + area + ", reservations=" + reservations + "]";
    }
    
    // Constructors, getters, and setters
    
}
