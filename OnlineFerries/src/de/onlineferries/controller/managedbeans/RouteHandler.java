package de.onlineferries.controller.managedbeans;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.ValueChangeEvent;

import de.onlineferries.model.service.RouteService;
import de.onlineferries.view.RouteView;
import de.onlineferries.view.ShipView;

@ManagedBean
@ApplicationScoped
public class RouteHandler {
	@ManagedProperty("#{serviceLocatorBean}")
	private ServiceLocator serviceLocator;

	private List<RouteView> routeViews;
	private RouteView route;
	private ShipView ship;

	public RouteHandler() {
	}

	public String routes() {
		System.out.println("RouteHandler");
		RouteService rs = serviceLocator.getRouteService();
		routeViews = rs.findAllRoutes();
		route = routeViews.get(0);
		if (routeViews != null) {
			return "success";
		} else
			return "retry";
	}

	public List<RouteView> getRouteViews() {
		return routeViews;
	}

	public void setRouteViews(List<RouteView> routeViews) {
		this.routeViews = routeViews;
	}

	public RouteView getRoute() {
		return route;
	}

	public void setRoute(RouteView route) {
		this.route = route;
	}

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public Converter getRouteViewConverter() {
		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {
				for (int i = 0; i < routeViews.size(); i++) {
					RouteView r = routeViews.get(i);
					if ((new Integer(r.getId()).toString()).equals(value)) {
						return r;
					}
				}
				return null;
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {
				return (new Integer(((RouteView) value).getId())).toString();
			}
		};
	}

	public ShipView getShip() {
		return ship;
	}

	public void setShip(ShipView ship) {
		this.ship = ship;
	}

	public void routeChanged(ValueChangeEvent vce) {
		try {
			RouteView route = (RouteView) vce.getNewValue();
			ship = serviceLocator.getShipService().findShip(route.getId());
			System.out.println("Ship is now " + ship.getShip_id());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
