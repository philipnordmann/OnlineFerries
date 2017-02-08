package de.onlineferries.controller.managedbeans;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import de.onlineferries.view.RouteView;
import de.onlineferries.view.TripView;

@ManagedBean
@ApplicationScoped
public class TripHandler {
	
	@ManagedProperty("#{routeHandler}")
	private RouteHandler routeHandler;
	
	private List<TripView> trips;
	private TripView trip;
	
	public String selectTrips() {
		RouteView route = routeHandler.getRoute();
		trips = findTrips(route.getId());
		if(trips != null && trips.size() > 0){
			trip = trips.get(0);
			return "success";
		}
		else
			return "retry";
	}
	
	public List<TripView> findTrips(int route_id){
		return routeHandler.getServiceLocator().getRouteService().findTrips(route_id);
	}

	public List<TripView> getTrips() {
		return trips;
	}

	public void setTrips(List<TripView> trips) {
		this.trips = trips;
	}

	public RouteHandler getRouteHandler() {
		return routeHandler;
	}

	public void setRouteHandler(RouteHandler routeHandler) {
		this.routeHandler = routeHandler;
	}
	
	public Converter getTripViewConverter() {
		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {
				for (int i = 0; i < trips.size(); i++) {
					TripView r = trips.get(i);
					if ((new Integer(r.getId()).toString()).equals(value)) {
						return r;
					}
				}
				return null;
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {
				return (new Integer(((TripView) value).getId())).toString();
			}
		};
	}

	public TripView getTrip() {
		return trip;
	}

	public void setTrip(TripView trip) {
		this.trip = trip;
	}
}
