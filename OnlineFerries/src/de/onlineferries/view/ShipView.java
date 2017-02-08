package de.onlineferries.view;

public class ShipView {
	private Integer ship_id;
	private String description;
	private int passengers;
	private int cars;
			
	public ShipView(Integer ship_id, String description, int passengers,
			int cars) {
		super();
		this.ship_id = ship_id;
		this.description = description;
		this.passengers = passengers;
		this.cars = cars;
	}
	
	public Integer getShip_id() {
		return ship_id;
	}
	public void setShip_id(Integer ship_id) {
		this.ship_id = ship_id;
	}
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
	public int getCars() {
		return cars;
	}
	public void setCars(int cars) {
		this.cars = cars;
	}
	
	
}
