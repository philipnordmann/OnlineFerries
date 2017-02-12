package de.onlineferries.model.service;

import java.util.List;

import de.onlineferries.controller.managedbeans.ReservationHandler;
import de.onlineferries.controller.managedbeans.RouteHandler;
import de.onlineferries.controller.managedbeans.TripHandler;
import de.onlineferries.view.ReservationView;
import de.onlineferries.view.ShipCabinView;
import de.onlineferries.view.TravellerView;

public interface ReservationService {
	public double getReservationPrice(int trip_id, List<ShipCabinView> shipCabins, int cars, int travellers);
	public void reserveTrip(int trip_id, int customer_id, List<ShipCabinView> shipCabins, int cars, List<TravellerView> travellerNames);
	public void changeReservation(int reservation_id, List<ShipCabinView> shipCabins, int cars, List<TravellerView> travellerNames);
	public List<ReservationView> getReservations(int customer_id);
	public void initializeReservation(int resId, RouteHandler routeHandler, TripHandler tripHandler, ReservationHandler reservationHandler);

}
