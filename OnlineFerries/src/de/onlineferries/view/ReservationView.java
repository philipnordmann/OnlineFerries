package de.onlineferries.view;

import java.util.HashSet;
import java.util.Set;

public class ReservationView {
	
	private Integer id;

	private CustomerView customer;

	private TripView trip;

	private Set<CabinView> cabins = new HashSet<>();

	private Set<TravellerView> travellers = new HashSet<>();

	private Integer cars;
	
	

	public ReservationView(Integer id, TripView trip, Set<CabinView> cabins,
			Set<TravellerView> travellers, Integer cars) {
		super();
		this.id = id;
		this.trip = trip;
		this.cabins = cabins;
		this.travellers = travellers;
		this.cars = cars;
	}
	
	public ReservationView() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomerView getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerView customer) {
		this.customer = customer;
	}

	public TripView getTrip() {
		return trip;
	}

	public void setTrip(TripView trip) {
		this.trip = trip;
	}

	public Set<CabinView> getCabins() {
		return cabins;
	}

	public void setCabins(Set<CabinView> cabins) {
		this.cabins = cabins;
	}

	public Set<TravellerView> getTravellers() {
		return travellers;
	}

	public void setTravellers(Set<TravellerView> travellers) {
		this.travellers = travellers;
	}

	public Integer getCars() {
		return cars;
	}

	public void setCars(Integer cars) {
		this.cars = cars;
	}
	
	

}
