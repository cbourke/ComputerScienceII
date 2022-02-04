package unl.cse;

import java.time.Duration;

/**
 * Models a radioactive isotope, see
 * 
 * More info can be found
 * <a href="https://en.wikipedia.org/wiki/Half-life">here</a>.
 * 
 * @author cbourke
 *
 */
public class Isotope {

	/**
	 * This is the base of the half life decay
	 */
	private final static double C = Math.exp(-0.693);

	private final int atomicNumber;
	private final String name;
	private final String symbol;
	private final double halfLife;
	private Double mass;

	public Isotope(int atomicNumber, String name, String symbol, double halfLife, Double mass) {
		this.atomicNumber = atomicNumber;
		this.name = name;
		this.symbol = symbol;
		this.halfLife = halfLife;
		this.mass = mass;
	}

	/**
	 * Decays this Isotope by the given number of years and returns how much mass
	 * the Isotope decayed by.
	 * 
	 * @param d
	 * @return
	 */
	public double decay(Duration d) {
		double remain = this.mass * Math.pow(C, ((d.toDays() / 365.25) / this.halfLife));
		double decayedMass = this.mass - remain;
		this.mass = remain;
		return decayedMass;
	}

	
	private Isotope() {		
		this(0, "Unknown", null, 0.0, null);
	}

	/**
	 * @param atomicNumber
	 * @param name
	 * @param symbol
	 * @param halfLife
	 */
	public Isotope(int atomicNumber, String name, String symbol, double halfLife) {
		super();
		this.atomicNumber = atomicNumber;
		this.name = name;
		this.symbol = symbol;
		this.halfLife = halfLife;
	}

	/**
	 * A copy constructor that makes a copy of the given Isotope but with the sample
	 * having the given mass
	 * 
	 * @param isotope
	 * @param mass
	 */
	public Isotope(Isotope isotope, double mass) {
		this.atomicNumber = isotope.atomicNumber;
		this.name = isotope.name;
		this.symbol = isotope.symbol;
		this.halfLife = isotope.halfLife;
		this.mass = mass;
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

	@Override
	public String toString() {
		return String.format("  %s (%d-%s) %f, %fkg", this.name, this.atomicNumber, this.symbol, this.halfLife,
				this.mass);
	}

//	public void setMass(double newMass) {
//		if(newMass < 0) {
//			throw new RuntimeException("Mass cannot be negative!");
//		}
//		this.mass = newMass;
//	}

}
