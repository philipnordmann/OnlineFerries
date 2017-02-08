package de.onlineferries.entity;

import java.io.Serializable;

public class ReservationCabinId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8830291335567832182L;
	
	
	Integer reservation;
	Integer cabin;
	
	public ReservationCabinId() {
	}
	
	public Integer getReservation() {
		return reservation;
	}
	public void setReservation(Integer reservation_id) {
		this.reservation = reservation_id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reservation == null) ? 0 : reservation.hashCode());
		result = prime * result + ((cabin == null) ? 0 : cabin.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationCabinId other = (ReservationCabinId) obj;
		if (reservation == null) {
			if (other.reservation != null)
				return false;
		} else if (!reservation.equals(other.reservation))
			return false;
		if (cabin == null) {
			if (other.cabin != null)
				return false;
		} else if (!cabin.equals(other.cabin))
			return false;
		return true;
	}

	public Integer getCabin() {
		return cabin;
	}

	public void setCabin(Integer cabin) {
		this.cabin = cabin;
	}

}
