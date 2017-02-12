package de.onlineferries.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;

	private Customer customer;

	private Trip trip;

	private Set<ReservationCabin> reservationCabins = new HashSet<>();

	private Set<Travellers> travellers = new HashSet<>();

	private Integer cars;

	public Reservation() {
	}

	@Id
	@Column(name="reservation_id")
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer reservation_id) {
		this.id = reservation_id;
	}

	public Integer getCars() {
		return cars;
	}

	public void setCars(Integer cars) {
		this.cars = cars;
	}

	@ManyToOne
	@JoinColumn(name = "trip_id")
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "reservation")
	public Set<ReservationCabin> getReservationCabins() {
		return reservationCabins;
	}

	public void setReservationCabins(Set<ReservationCabin> reservationCabins) {
		this.reservationCabins = reservationCabins;
	}

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "reservation")
	public Set<Travellers> getTravellers() {
		return travellers;
	}

	public void setTravellers(Set<Travellers> travellers) {
		this.travellers = travellers;
	}

	@ManyToOne
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
