package de.onlineferries.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import de.onlineferries.controller.managedbeans.ReservationHandler;
import de.onlineferries.controller.managedbeans.RouteHandler;
import de.onlineferries.controller.managedbeans.TripHandler;
import de.onlineferries.entity.Cabin;
import de.onlineferries.entity.Customer;
import de.onlineferries.entity.Reservation;
import de.onlineferries.entity.ReservationCabin;
import de.onlineferries.entity.Route;
import de.onlineferries.entity.Travellers;
import de.onlineferries.entity.Trip;
import de.onlineferries.view.CabinView;
import de.onlineferries.view.ReservationView;
import de.onlineferries.view.RouteView;
import de.onlineferries.view.ShipCabinView;
import de.onlineferries.view.ShipView;
import de.onlineferries.view.TravellerView;
import de.onlineferries.view.TripView;

public class ReservationServiceImpl implements ReservationService {

	@Override
	public double getReservationPrice(int trip_id, List<ShipCabinView> shipCabins, int cars, int travellers) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		Trip t = em.find(Trip.class, trip_id);
		em.close();
		return shipCabins.stream().mapToDouble((s) -> s.getPrice() * s.getRes_count()).sum() + t.getPrice_car() * cars
				+ t.getPrice_passenger() * travellers;

	}

	public void reserveTrip(int trip_id, int customer_id, List<ShipCabinView> shipCabins, int cars,
			List<TravellerView> travellerNames) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		Reservation reservation = new Reservation();
		em.getTransaction().begin();
		try {
			reservation.setCars(cars);
			reservation.setCustomer(em.find(Customer.class, customer_id));
			reservation.setTrip(em.find(Trip.class, trip_id));
			Set<ReservationCabin> reservationCabins = new HashSet<>();
			shipCabins.stream().filter(s -> s.getRes_count() > 0).forEach((s) -> reservationCabins
					.add(new ReservationCabin(reservation, em.find(Cabin.class, s.getCabin_id()), s.getRes_count())));
			if (travellerNames != null) {
				Set<Travellers> travellers = new HashSet<>();
				travellerNames.stream().forEach((t) -> travellers.add(new Travellers(t.getName(), reservation)));
				reservation.setTravellers(travellers);
			}

			reservation.setReservationCabins(reservationCabins);
			em.persist(reservation);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public List<ReservationView> getReservations(int customer_id) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		Customer cust = em.find(Customer.class, customer_id);
		List<ReservationView> reservations = new ArrayList<>();
		cust.getReservations().stream().forEach(r -> reservations.add(transformReservation(r)));

		return reservations;
	}

	private ReservationView transformReservation(Reservation r) {
		ReservationView res = new ReservationView();
		res.setId(r.getId());
		res.setCars(r.getCars());

		TripView trip = new TripView();
		trip.setDate(r.getTrip().getDate());
		trip.setDeparture(r.getTrip().getDeparture());
		trip.setArrival(r.getTrip().getArrival());

		RouteView route = new RouteView(r.getTrip().getRoute().getId(), r.getTrip().getRoute().getStart(),
				r.getTrip().getRoute().getDestination());
		route.setShip(new ShipView(r.getTrip().getRoute().getShip().getId(),
				r.getTrip().getRoute().getShip().getDescription(), r.getTrip().getRoute().getShip().getPassengers(),
				r.getTrip().getRoute().getShip().getCars()));
		trip.setRoute(route);

		res.setTrip(trip);

		Set<CabinView> cabins = new HashSet<>();
		r.getReservationCabins().stream().forEach((c) -> cabins.add(new CabinView(c.getCabin().getCabintype_id(),
				c.getCabin().getDescription(), c.getCabin().getPassengers(), c.getCount())));

		res.setCabins(cabins);

		Set<TravellerView> travellers = new HashSet<>();
		r.getTravellers().stream()
				.forEach(t -> travellers.add(new TravellerView(t.getTravellers_id(), t.getFullname())));

		res.setTravellers(travellers);

		return res;
	}

	public void initializeReservation(int resId, RouteHandler routeHandler, TripHandler tripHandler,
			ReservationHandler reservationHandler) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		Reservation r = em.find(Reservation.class, resId);

		Route route = r.getTrip().getRoute();
		routeHandler.setRoute(new RouteView(route.getId(), route.getStart(), route.getStart()));

		tripHandler.setTrip(
				new TripView(r.getTrip().getId(), new RouteView(route.getId(), route.getStart(), route.getStart()),
						r.getTrip().getDate(), r.getTrip().getDeparture(), r.getTrip().getArrival(),
						r.getTrip().getPrice_car(), r.getTrip().getPrice_passenger()));
	}

	@Override
	public void changeReservation(int reservation_id, List<ShipCabinView> shipCabins, int cars,
			List<TravellerView> travellerNames) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		Reservation r = em.find(Reservation.class, reservation_id);
		
				
		try {
			em.getTransaction().begin();
			r.getTravellers().forEach(t -> em.remove(t));
			r.getTravellers().removeIf(t -> !t.getFullname().equals(""));
			r.setCars(cars);
			for (ShipCabinView cabin : shipCabins) {
				r.getReservationCabins().stream().filter(c -> c.getCabin().getCabintype_id() == cabin.getCabin_id())
						.forEach(c -> c.setCount(cabin.getRes_count()));
			}

			travellerNames.stream().forEach(t -> r.getTravellers().add(new Travellers(t.getName(), r)));
			em.persist(r);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}

	}

}
