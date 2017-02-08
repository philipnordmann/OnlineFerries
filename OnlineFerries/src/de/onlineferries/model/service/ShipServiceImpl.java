package de.onlineferries.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.onlineferries.entity.Route;
import de.onlineferries.entity.Ship;
import de.onlineferries.entity.ShipCabin;
import de.onlineferries.view.ShipCabinView;
import de.onlineferries.view.ShipView;

public class ShipServiceImpl implements ShipService {

	@Override
	public ShipView findShip(int route_id) {
		EntityManagerFactory ef = EntityManagerFactoryService
				.getEntityManagerFactory();
		EntityManager em = ef.createEntityManager();
		TypedQuery<Route> query = em.createQuery("from Route where route_id = "
				+ route_id, Route.class);
		Ship ship = query.getSingleResult().getShip();
		return new ShipView(ship.getId(), ship.getDescription(),
				ship.getPassengers(), ship.getCars());
	}

	@Override
	public List<ShipCabinView> findAllShipCabins(Integer ship_id) {
		EntityManagerFactory ef = EntityManagerFactoryService
				.getEntityManagerFactory();
		EntityManager em = ef.createEntityManager();
		TypedQuery<ShipCabin> query = em.createQuery(
				"from ShipCabin where ship_id = " + ship_id, ShipCabin.class);
		List<ShipCabinView> scv = new ArrayList<ShipCabinView>();

		query.getResultList()
				.stream()
				.forEach(
						(s) -> {
							scv.add(new ShipCabinView(s.getCabin().getId(), s
									.getCabin().getDescription(), s.getShip()
									.getPassengers(), s.getCount(), s
									.getPrice(), 0));
						});

		return scv;
	}

}
