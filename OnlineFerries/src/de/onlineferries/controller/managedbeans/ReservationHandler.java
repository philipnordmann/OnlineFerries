package de.onlineferries.controller.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import de.onlineferries.view.CabinView;
import de.onlineferries.view.ReservationView;
import de.onlineferries.view.ShipCabinView;
import de.onlineferries.view.TravellerView;

@ManagedBean
@SessionScoped
public class ReservationHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{serviceLocatorBean}")
	private ServiceLocator serviceLocator;
	@ManagedProperty("#{routeHandler}")
	private RouteHandler routeHandler;
	@ManagedProperty("#{tripHandler}")
	private TripHandler tripHandler;
	@ManagedProperty("#{loginHandler}")
	private LoginHandler loginHandler;

	private List<ShipCabinView> shipCabins;
	private int cars;
	private int travellers;
	private List<TravellerView> travellerNames;
	private double reservationPrice;
	private List<ReservationView> reservations;
	private ReservationView currentReservation;

	public int[] getTravellerValues() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6 };
	}

	public String enterReservation() {
		shipCabins = serviceLocator.getShipService().findAllShipCabins(routeHandler.getShip().getShip_id());
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

		return "successSelect";
	}

	public void validateTravellerName(FacesContext context, UIComponent component, Object obj) {

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
		reservationPrice = serviceLocator.getReservationService().getReservationPrice(tripHandler.getTrip().getId(),
				shipCabins, cars, travellers);
		return reservationPrice;
	}

	public void setReservationPrice(double reservationPrice) {
		this.reservationPrice = reservationPrice;
	}

	public LoginHandler getLoginHandler() {
		return loginHandler;
	}

	public void setLoginHandler(LoginHandler loginHandler) {
		this.loginHandler = loginHandler;
	}

	public String reserve() {
		int customer_id = loginHandler.getCustomer().getCustomer_id();
		serviceLocator.getReservationService().reserveTrip(tripHandler.getTrip().getId(), customer_id, shipCabins, cars,
				travellerNames);
		return "sucess";
	}

	public String changeReservation(int id) {
		serviceLocator.getReservationService().initializeReservation(id, routeHandler, tripHandler, this);
		currentReservation = reservations.stream().filter(r -> r.getId() == id).findFirst().get();
		shipCabins = serviceLocator.getShipService()
				.findAllShipCabins(currentReservation.getTrip().getRoute().getShip().getShip_id());
		for (CabinView cabin : currentReservation.getCabins()) {
			for (ShipCabinView view : shipCabins) {
				if (view.getCabin_id().equals(cabin.getId())) {
					view.setRes_count(cabin.getRes_count());
					break;
				}
			}
			System.out.println(cabin.getRes_count());
		}
		cars = currentReservation.getCars();
		travellerNames = currentReservation.getTravellers().stream().collect(Collectors.toList());
		return "/restricted/showReservation";
	}
	
	public String changeSomething() {
		serviceLocator.getReservationService().changeReservation(currentReservation.getId(), shipCabins, cars, travellerNames);
		return "/restricted/changeReservation";
	}

	public List<ReservationView> getReservations() {
		List<ReservationView> list;
		if ((list = serviceLocator.getReservationService()
				.getReservations(loginHandler.getCustomer().getCustomer_id())) != null) {
			reservations = list;
		}
		return reservations;
	}

	public void setReservations(List<ReservationView> reservations) {
		this.reservations = reservations;
	}

	public ReservationView getCurrentReservation() {
		return currentReservation;
	}

	public void setCurrentReservation(ReservationView currentReservation) {
		this.currentReservation = currentReservation;
	}

}
