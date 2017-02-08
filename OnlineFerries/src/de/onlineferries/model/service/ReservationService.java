package de.onlineferries.model.service;

import java.util.List;

import de.onlineferries.view.ShipCabinView;

public interface ReservationService {
	public double getReservationPrice(int trip_id, List<ShipCabinView> shipCabins, int cars, int travellers);

}
