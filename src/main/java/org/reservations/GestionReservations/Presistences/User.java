package org.reservations.GestionReservations.Presistences;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String address;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    
    
    public User() {
	super();
    }

    public User(Long id, String name, String address, String password, List<Reservation> reservations) {
	super();
	this.id = id;
	this.name = name;
	this.address = address;
	this.password = password;
	this.reservations = reservations;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public List<Reservation> getReservations() {
	return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
	this.reservations = reservations;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", address=" + address + ", password=" + password
		+ ", reservations=" + reservations + "]";
    }
    
    
    // Constructors, getters, and setters
}
