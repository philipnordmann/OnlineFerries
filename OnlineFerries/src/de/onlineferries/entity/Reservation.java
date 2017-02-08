package de.onlineferries.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {

	@GeneratedValue
	@Id
	Integer reservation_id;
	Integer trip_id;
	Integer customer_id;
	Integer cars;
	
	
	public Reservation() {
	}	
	
	public Reservation(Integer reservation_id, Integer trip_id, Integer customer_id, Integer cars) {
		super();
		this.reservation_id = reservation_id;
		this.trip_id = trip_id;
		this.customer_id = customer_id;
		this.cars = cars;
	}
	
	public Integer getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}
	public Integer getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(Integer trip_id) {
		this.trip_id = trip_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getCars() {
		return cars;
	}
	public void setCars(Integer cars) {
		this.cars = cars;
	}
	
}
