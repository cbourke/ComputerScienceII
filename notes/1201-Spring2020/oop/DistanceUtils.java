package unl.cse.airport;

public class DistanceUtils {

	/**
	 * This variable represents the earth's radius in kilometers.
	 */
	public static final double EARTH_RADIUS = 6371;

	/**
	 * Converts degrees to radians
	 * 
	 * @param degrees
	 * @return
	 */
	public static double degreeToRadians(double degrees) {
		return degrees / 180 * Math.PI;
	}

	/**
	 * This method computes the air distance between the given latitude/longitude
	 * and the destination latitude/longitude using the Spherical Law of Cosines
	 * (for more information see <a href="https://en.wikipedia.org/wiki/Spherical_law_of_cosines">wikipedia</a>
	 * 
	 * @param latitudeA
	 * @param longitudeA
	 * @param latitudeB
	 * @param longitudeB
	 * @return
	 */
	public static double getAirDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
		double latARadian = DistanceUtils.degreeToRadians(latitudeA);
		double lonARadian = DistanceUtils.degreeToRadians(longitudeA);
		double latBRadian = DistanceUtils.degreeToRadians(latitudeB);
		double lonBRadian = DistanceUtils.degreeToRadians(longitudeB);
		double delta = lonBRadian - lonARadian;
		return Math.acos(Math.sin(latARadian) * Math.sin(latBRadian)
				+ Math.cos(latARadian) * Math.cos(latBRadian) * Math.cos(delta)) * DistanceUtils.EARTH_RADIUS;
	}

}
