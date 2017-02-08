package de.onlineferries.view;

public class ShipCabinView {
	
	private Integer cabin_id;
	private String cabinDescr;
	private int passangers;
	private int count;
	private double price;
	private int res_count;
	
	public ShipCabinView(Integer cabin_id, String cabinDescr, int passangers,
			int count, double price, int res_count) {
		super();
		this.cabin_id = cabin_id;
		this.cabinDescr = cabinDescr;
		this.passangers = passangers;
		this.count = count;
		this.price = price;
		this.res_count = res_count;
	}

	public Integer getCabin_id() {
		return cabin_id;
	}

	public void setCabin_id(Integer cabin_id) {
		this.cabin_id = cabin_id;
	}

	public String getCabinDescr() {
		return cabinDescr;
	}

	public void setCabinDescr(String cabinDescr) {
		this.cabinDescr = cabinDescr;
	}

	public int getPassangers() {
		return passangers;
	}

	public void setPassangers(int passangers) {
		this.passangers = passangers;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRes_count() {
		return res_count;
	}

	public void setRes_count(int res_count) {
		this.res_count = res_count;
	}
	
	
	
	

}
