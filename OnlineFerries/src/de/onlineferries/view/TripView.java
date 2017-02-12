package de.onlineferries.view;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TripView {

	private Integer id;
	private RouteView route;
	private Date date;
	private Date departure;
	private Date arrival;
	private double price_car;
	private double price_passenger;
	
	public TripView() {
	}
	
	public TripView(Integer id, RouteView route, Date date, Date departure,
			Date arrival, double price_car, double price_passenger) {
		super();
		this.id = id;
		this.route = route;
		this.date = date;
		this.departure = departure;
		this.arrival = arrival;
		this.price_car = price_car;
		this.price_passenger = price_passenger;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public RouteView getRoute() {
		return route;
	}
	public void setRoute(RouteView route) {
		this.route = route;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public double getPrice_car() {
		return price_car;
	}
	public double getCarPrice() {
		return price_car;
	}
	public void setPrice_car(double price_car) {
		this.price_car = price_car;
	}
	public double getPrice_passenger() {
		return price_passenger;
	}
	public double getPassengerPrice() {
		return price_passenger;
	}
	public void setPrice_passenger(double price_passenger) {
		this.price_passenger = price_passenger;
	}
	public String getFormatDate(){
		return new SimpleDateFormat("DD.MM.YYYY").format(this.getDate());
	}
	
	public String getFormatDeparture(){
		return new SimpleDateFormat("DD.MM.YYYY").format(this.getDeparture());
	}
	
	public String getFormatArrival(){
		return new SimpleDateFormat("DD.MM.YYYY").format(this.getArrival());
	}
}
