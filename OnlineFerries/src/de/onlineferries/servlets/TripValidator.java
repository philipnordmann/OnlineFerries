package de.onlineferries.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.onlineferries.entity.Cabin;
import de.onlineferries.entity.Trip;
import de.onlineferries.model.service.EntityManagerFactoryService;

/**
 * Servlet implementation class TripValidator
 */
@WebServlet("/TripValidator")
public class TripValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TripValidator() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String validator = request.getParameter("validator");

		boolean available = false;

		if (validator.equals("cabin")) {

			available = validateCabin(Integer.parseInt(request.getParameter("cabin")),
					Integer.parseInt(request.getParameter("trip")), Integer.parseInt(request.getParameter("count")));
		} else if (validator.equals("passanger")) {
			Map<Integer, Integer> cabins = new HashMap<>();
			String map = request.getParameter("cabins");
			for (String cabinPair : map.split(",")) {
				String[] pair = cabinPair.split("-");
				cabins.put(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
			}

			available = validatePassenger(cabins, Integer.parseInt(request.getParameter("count")));
		}

		response.getWriter().print(available);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private boolean validateCabin(int cabinId, int tripId, int count) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();

		Trip t = em.find(Trip.class, tripId);
		long cabinCount = t.getRoute().getShip().getShipCabin().stream()
				.filter(sc -> sc.getCabin().getCabintype_id() == cabinId).count();
		long reserved = t.getReservations()
				.stream().mapToLong(r -> r.getReservationCabins().stream()
						.filter(c -> c.getCabin().getCabintype_id() == cabinId).mapToLong(v -> v.getCount()).sum())
				.sum();

		return reserved + count <= cabinCount;

	}

	private boolean validatePassenger(Map<Integer, Integer> cabins, int count) {
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		int passcount = cabins.keySet().stream().mapToInt(c -> em.find(Cabin.class, c).getPassengers() * cabins.get(c))
				.sum();
		return count <= passcount;
	}

}
