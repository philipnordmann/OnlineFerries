package de.onlineferries.model.service;

import java.util.List;

import javax.persistence.EntityManager;

import de.onlineferries.entity.Trip;
import de.onlineferries.view.ShipCabinView;

public class ReservationServiceImpl implements ReservationService {

	@Override
	public double getReservationPrice(int trip_id,
			List<ShipCabinView> shipCabins, int cars, int travellers) {
		EntityManager em = EntityManagerFactoryService
				.getEntityManagerFactory().createEntityManager();
		Trip t = em.find(Trip.class, trip_id);
		em.close();
		return shipCabins.stream()
				.mapToDouble((s) -> s.getPrice() * s.getRes_count()).sum()
				+ t.getPrice_car() * cars + t.getPrice_passenger() * travellers;

	}

}
