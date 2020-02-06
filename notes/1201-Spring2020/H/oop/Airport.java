package unl.cse.honors;

import java.time.LocalDate;

/**
 * This class models an airport.
 * 
 * @author cbourke
 *
 */
public class Airport {
	
	private String name;
	private String gpsId;
	private double latitude;
	private double longitude;
	
	private Address address;
	
	private LocalDate dateBuilt;
	
	public Airport() {
	}
	
	public Airport(String name, String gpsId, double latitude, double longitude) {		
		this(name, gpsId, latitude, longitude, null);
	}

	public Airport(String name, String gpsId, double latitude, double longitude, Address address) {
		this.name = name;
		this.gpsId = gpsId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}

	public Airport(String name) {
		this(name, null, 0.0, 0.0);
	}

	public Airport(String name, String gpsId) {
		this(name, gpsId, 0.0, 0.0);
	}
	
	public Airport(Airport that) {
		this(that.name, that.gpsId, that.latitude, that.longitude);
	}

	public Airport(Airport that, String name) {
		this(name, that.gpsId, that.latitude, that.longitude);
	}

	public String getName() {
		return name;
	}

	public String getGpsId() {
		return gpsId;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public Address getAddress() {
		return this.address;
	}

	@Override
	public String toString() {
		return String.format("%s (%.2f,%.2f) [%s]", this.name, this.latitude, this.longitude, this.gpsId);
		
	}
	
	/**
	 * Computes the air distance between this airport and the
	 * given airport on the Earth using the <a href="https://en.wikipedia.org/wiki/Spherical_law_of_cosines">Spherical Law of Cosines</a>.
	 *
	 * @param destination
	 * @return
	 */
	public double getAirDistance(Airport destination) {
		return DistanceUtils.getAirDistance(this.latitude, this.longitude, destination.latitude, destination.longitude);
	}



}
