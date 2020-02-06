package unl.cse.airport;
import java.time.LocalDate;

/**
 * This class models an airport.  For more information on
 * <code>GPS</code> <i>IDs</i>, see <a href="https://todo.com">FAA's website</a>
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
	
	public Airport(String name, String gpsId, double latitude, double longitude) {
		this.name = name;
		this.gpsId = gpsId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Airport(String name, String gpsId, double latitude, double longitude, Address address) {
		this.name = name;
		this.gpsId = gpsId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}

	public Airport(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Airport(Airport that) {
		this.name = that.name;
		this.gpsId = that.gpsId;
		this.latitude = that.latitude;
		this.longitude = that.longitude;
	}

	public Airport(Airport that, double longitude) {
		this.name = that.name;
		this.gpsId = that.gpsId;
		this.latitude = that.latitude;
		this.longitude = longitude;
	}


	public String getName() {
		return this.name;
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

	public String toString() {
		//TODO: incorporate the address here
		return String.format("%s (%.2f, %.2f) [%s]", this.name, this.latitude, this.longitude, this.gpsId);
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	/**
	 * This method computes the air distance between the given airport
	 * and this airport using the Spherical Law of Cosines (for more information
	 * see <a href="https://en.wikipedia.org/wiki/Spherical_law_of_cosines">wikipedia</a>
	 * @param airport
	 * @return
	 */
	public double getAirDistance(Airport airport) {
		return DistanceUtils.getAirDistance(this.latitude, this.longitude, airport.latitude, airport.longitude);
	}

}
