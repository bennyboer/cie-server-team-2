package edu.hm.cs.cieserver.campus;

import javax.persistence.Embeddable;

/**
 * Representation of a geographical location.
 */
@Embeddable
public class Location {

	private Double latitude;
	private Double longitude;

	public Location() {
		// Default constructor for jackson.
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
