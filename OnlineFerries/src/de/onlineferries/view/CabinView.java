package de.onlineferries.view;

public class CabinView {
	
	private Integer id;
	private String description;
	private int passengers;
	private int res_count;
	
	public CabinView(Integer id, String description, int passengers, int res_count) {
		super();
		this.id = id;
		this.description = description;
		this.passengers = passengers;
		this.res_count = res_count;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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


	public int getRes_count() {
		return res_count;
	}


	public void setRes_count(int res_count) {
		this.res_count = res_count;
	}

}
