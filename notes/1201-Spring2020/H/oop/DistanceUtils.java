package unl.cse.honors;

/**
 * This is a collection of methods to compute distances.
 * 
 * @see #degToRadians(double) for example
 * 
 * @author cbourke
 *
 */
public class DistanceUtils {
	
	/**
	 * The radius of the Earth in kilometers.
	 */
	public static final double EARTH_RADIUS = 6371;

	/**
	 * 
	 * @param degrees
	 * @return
	 */
	private static double degToRadians(double degrees) {
		return degrees/180 * Math.PI;
	}
	
	/**
	 * Computes the air distance between two latitude/longitude locations
	 * on the Earth using the <a href="https://en.wikipedia.org/wiki/Spherical_law_of_cosines">Spherical Law of Cosines</a>.
	 * 
	 * @param latA latitude of the origin in degrees
	 * @param longA longitude of the origin in degrees
	 * @param latB latitude of the destination in degrees
	 * @param longB longitude of the destination in degrees
	 * @return
	 */
	public static double getAirDistance(double latA, double longA, double latB, double longB) {
		double latArad = DistanceUtils.degToRadians(latA);
		double lonArad = DistanceUtils.degToRadians(longA);
		double latBrad = DistanceUtils.degToRadians(latB);
		double lonBrad = DistanceUtils.degToRadians(longB);
		double delta = lonBrad - lonArad;
		return Math.acos( Math.sin(latArad) * Math.sin(latBrad) + Math.cos(latArad) * Math.cos(latBrad) * Math.cos(delta)) * DistanceUtils.EARTH_RADIUS;
	}
	
}
