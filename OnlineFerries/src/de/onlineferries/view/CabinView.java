package de.onlineferries.view;

public class CabinView {
	
	private Integer id;
	private String description;
	private int passengers;
	
	public CabinView(Integer id, String description, int passengers) {
		super();
		this.id = id;
		this.description = description;
		this.passengers = passengers;
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

}
