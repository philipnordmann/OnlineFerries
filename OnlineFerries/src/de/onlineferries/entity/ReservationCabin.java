package de.onlineferries.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ReservationCabinId.class)
public class ReservationCabin {

	@Id
	@ManyToOne(optional = true)
	private Reservation reservation;
	@Id
	@ManyToOne(optional = true)
	private Cabin cabin;

	Integer cabin_index;
	Integer count;

	public ReservationCabin() {
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Cabin getCabin() {
		return cabin;
	}

	public void setCabin(Cabin cabin) {
		this.cabin = cabin;
	}

	public Integer getCabin_index() {
		return cabin_index;
	}

	public void setCabin_index(Integer cabin_index) {
		this.cabin_index = cabin_index;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
