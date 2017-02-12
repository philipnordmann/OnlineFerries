package de.onlineferries.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Travellers {
	@Id
	@GeneratedValue
	Integer travellers_id;
	Integer travellers_index;
	@ManyToOne
	@JoinColumn(name="reservation_id")
	Reservation reservation;
	String fullname;
	
	public Travellers(String fullname, Reservation reservation) {
		super();
		this.fullname = fullname;
		this.reservation = reservation;
	}
	
	public Travellers() {
	}

	public Integer getTravellers_id() {
		return travellers_id;
	}

	public void setTravellers_id(Integer traveller_id) {
		this.travellers_id = traveller_id;
	}

	public Integer getTravellers_index() {
		return travellers_index;
	}

	public void setTravellers_index(Integer traveller_index) {
		this.travellers_index = traveller_index;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
	
	

}
