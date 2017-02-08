package de.onlineferries.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String validator = request.getParameter("validator");
		
		boolean available = false;
		
		if (validator.equals("cabin")) {
			available = validateCabin(Integer.parseInt(request.getParameter("cabin")), 1, 2);
		} else if(validator.equals("passanger")) {
			available = validatePassenger(0);
		}
		
		response.getWriter().print(available);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean validateCabin(int cabinId, int tripId, int count){
		EntityManager em = EntityManagerFactoryService.getEntityManagerFactory().createEntityManager();
		
		Trip t = em.find(Trip.class, tripId);
		int cabinCount = t.getRoute().getShip().getShipCabin().size();
		long reserved = t.getReservations().stream().mapToLong((r) -> r.getCabins().stream().filter(c -> c.getCabin().getId() == cabinId).count()).sum();
		
		return reserved + count < cabinCount;
		
		}
	
	private boolean validatePassenger(int i){
		return false;
	}

}
