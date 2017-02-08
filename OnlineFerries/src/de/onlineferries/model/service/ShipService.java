package de.onlineferries.model.service;

import java.util.List;

import de.onlineferries.view.ShipCabinView;
import de.onlineferries.view.ShipView;

public interface ShipService {

	public ShipView findShip(int route_id);
	public List<ShipCabinView> findAllShipCabins(Integer ship_id);
}
