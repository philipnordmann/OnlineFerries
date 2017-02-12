package de.onlineferries.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import de.onlineferries.entity.Route;
import de.onlineferries.view.RouteView;
import de.onlineferries.view.TripView;

public class RouteServiceImpl implements RouteService {

	@Override
	public List<RouteView> findAllRoutes() {
		EntityManagerFactory ef = EntityManagerFactoryService
				.getEntityManagerFactory();
		EntityManager em = ef.createEntityManager();
		TypedQuery<Route> query = em.createQuery(
				"from de.onlineferries.entity.Route", Route.class);

		List<Route> routes = query.getResultList();
		List<RouteView> routeViews = new ArrayList<RouteView>();
		for (Route route : routes) {
			routeViews.add(new RouteView(route.getId(), route.getStart(), route
					.getDestination()));
		}
		return routeViews;
	}

	@Override
	public List<TripView> findTrips(int route_id) {
		EntityManagerFactory ef = EntityManagerFactoryService
				.getEntityManagerFactory();
		EntityManager em = ef.createEntityManager();
		TypedQuery<Route> query = em.createQuery(
				"from de.onlineferries.entity.Route where id = " + route_id,
				Route.class);
		List<TripView> trips = new ArrayList<>();
		query.getSingleResult()
				.getTrips()
				.stream()
				.forEach(
						(t) -> {
							trips.add(new TripView(t.getId(), new RouteView(t.getRoute().getId(),t.getRoute().getStart(),t.getRoute().getDestination()), t
									.getDate(), t.getDeparture(), t
									.getArrival(), t.getPrice_car(), t
									.getPrice_passenger()));
						});
		return trips;
	}

}
