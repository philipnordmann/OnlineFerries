package de.onlineferries.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cabintype")
public class Cabin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue
	private Integer cabintype_id;
	private String description;
	private int passengers;
	
	private Set<ReservationCabin> reservationCabins = new HashSet<>();
	
	public Cabin() {}
	
	@Id
	public Integer getCabintype_id() { return cabintype_id; }
	public void setCabintype_id(Integer id) { this.cabintype_id = id; }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	@OneToMany(mappedBy="cabin")
	public Set<ReservationCabin> getReservationCabins() {
		return reservationCabins;
	}

	public void setReservationCabins(Set<ReservationCabin> reservationCabins) {
		this.reservationCabins = reservationCabins;
	}

}
