package org.reservations.GestionReservations.Presistences;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long invoiceNumber;
    private double amount;
    private boolean paid;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Invoice(Long invoiceNumber, double amount, boolean paid, LocalDate date, Reservation reservation) {
	super();
	this.invoiceNumber = invoiceNumber;
	this.amount = amount;
	this.paid = paid;
	this.date = date;
	this.reservation = reservation;
    }

    public Invoice() {
	super();
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    // Constructors, getters, and setters
}
