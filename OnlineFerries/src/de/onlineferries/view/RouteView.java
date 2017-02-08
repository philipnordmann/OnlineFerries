package de.onlineferries.view;

import java.io.Serializable;

public class RouteView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5745215725709998269L;
	private Integer id;
	private String start;
	private String destination;
	
	public RouteView() {
	}
	
	public RouteView(Integer id, String start, String destination) {
		super();
		this.id = id;
		this.start = start;
		this.destination = destination;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	
}
