package de.onlineferries.controller.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import de.onlineferries.view.ShipCabinView;
import de.onlineferries.view.TravellerView;

@ManagedBean
@ApplicationScoped
public class ReservationHandler {

	@ManagedProperty("#{serviceLocatorBean}")
	private ServiceLocator serviceLocator;
	@ManagedProperty("#{routeHandler}")
	private RouteHandler routeHandler;
	@ManagedProperty("#{tripHandler}")
	private TripHandler tripHandler;

	private List<ShipCabinView> shipCabins;
	private int cars;
	private int travellers;
	private List<TravellerView> travellerNames;
	private double reservationPrice;

	public int[] getTravellerValues() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6 };
	}

	public String enterReservation() {
		System.out.println(routeHandler.getShip().getShip_id());
		shipCabins = serviceLocator.getShipService().findAllShipCabins(
				routeHandler.getShip().getShip_id());
		if (shipCabins != null)
			return "success";
		else
			return "retry";
	}

	public void changeTraveller(ValueChangeEvent vce) {
		travellers = (Integer) vce.getNewValue();
		travellerNames = new ArrayList<TravellerView>(travellers);
		for (int i = 0; i < travellers; i++) {
			travellerNames.add(new TravellerView(i, ""));
		}
		FacesContext.getCurrentInstance().renderResponse();
	}

	public String selectCustomerType() {
		reservationPrice = serviceLocator.getReservationService()
				.getReservationPrice(tripHandler.getTrip().getId(), shipCabins,
						cars, travellers);
		return "successSelect";
	}

	public void validateTravellerName(FacesContext context,
			UIComponent component, Object obj) {
		System.out.println("Communication-Modul: v1");
		System.out.println("===============================");
		System.out.println("Prüfung der Funktionalität.");

	}

	public List<ShipCabinView> getShipCabins() {
		return shipCabins;
	}

	public void setShipCabins(List<ShipCabinView> shipCabins) {
		this.shipCabins = shipCabins;
	}

	public int getCars() {
		return cars;
	}

	public void setCars(int cars) {
		this.cars = cars;
	}

	public int getTravellers() {
		return travellers;
	}

	public void setTravellers(int travellers) {
		this.travellers = travellers;
	}

	public List<TravellerView> getTravellerNames() {
		return travellerNames;
	}

	public void setTravellerNames(List<TravellerView> travellerNames) {
		this.travellerNames = travellerNames;
	}

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public RouteHandler getRouteHandler() {
		return routeHandler;
	}

	public void setRouteHandler(RouteHandler routeHandler) {
		this.routeHandler = routeHandler;
	}

	public TripHandler getTripHandler() {
		return tripHandler;
	}

	public void setTripHandler(TripHandler tripHandler) {
		this.tripHandler = tripHandler;
	}

	public double getReservationPrice() {
		return reservationPrice;
	}

	public void setReservationPrice(double reservationPrice) {
		this.reservationPrice = reservationPrice;
	}

}
