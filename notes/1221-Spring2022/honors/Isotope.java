package unl.cse.honors;

import java.time.Duration;

/**
 * Models a radioactive isotope.
 * 
 * @author cbourke
 *
 */
public class Isotope {

	public static double C = Math.exp(-0.693);

	private final int atomicNumber;
	private final String name;
	private final String symbol;
	private final double halfLife;
	private double mass;

	/**
	 * @param atomicNumber
	 * @param name
	 * @param symbol
	 * @param halfLife
	 * @param mass
	 */
	public Isotope(int atomicNumber, String name, String symbol, double halfLife, double mass) {
		this.atomicNumber = atomicNumber;
		this.name = name;
		this.symbol = symbol;
		this.halfLife = halfLife;
		this.mass = mass;
	}

	/**
	 * Default no-argument constructor
	 */
	private Isotope() {
		this(0, "Uknown", null, 0.0, 0.0);
	}

	/**
	 * A copy constructor for Isotope
	 * 
	 * @param isotope
	 */
	public Isotope(Isotope isotope, double mass) {
		this(isotope.atomicNumber, isotope.name, isotope.symbol, isotope.halfLife, mass);
	}

	@Override
	public String toString() {
		return String.format("  %s (%d-%s) %f", this.name, this.atomicNumber, this.symbol, this.mass);
	}

	public int getAtomicNumber() {
		return atomicNumber;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getHalfLife() {
		return halfLife;
	}

	public double getMass() {
		return mass;
	}

	/**
	 * Decays this <code>Isotope</code> by the given number of years and returns the
	 * mass it decayed by.
	 * 
	 * For more information, see
	 * <a href="https://en.wikipedia.org/wiki/Half-life">wikipedia</a>.
	 * 
	 * @param years
	 * @return
	 */
	public double decay(Duration d) {
		double remaining = this.mass * Math.pow(C, ( (d.toDays() / 365.25) / this.halfLife));
		double decayAmount = this.mass - remaining;
		this.mass = remaining;
		return decayAmount;
	}

//	public void setMass(double mass) {
//		if(mass < 0) {
//			throw new RuntimeException("invalid mass, must be non-negative!");
//		}
//		this.mass = mass;
//	}

}
