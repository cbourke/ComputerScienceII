package uno.ece;

import java.time.Duration;

/**
 * A class that models an isotope.
 * 
 * @author cbourke
 *
 */
public class Isotope {

	private final int atomicNumber;
	private final String elementName;
	private final String elementSymbol;
	private final double halfLife;
	private Double mass;

	public Isotope(int atomicNumber, String elementName, String elementSymbol, double halfLife) {
		this(atomicNumber, elementName, elementSymbol, halfLife, null);
	}

	public Isotope(int atomicNumber, String elementName, String elementSymbol, double halfLife, Double mass) {
		this.atomicNumber = atomicNumber;
		this.elementName = elementName;
		this.elementSymbol = elementSymbol;
		this.halfLife = halfLife;
		this.mass = mass;
	}

	/**
	 * A copy constructor: it creates a new (deep) copy of the given
	 * <code>Isotope</code> instance.
	 * 
	 * @param isotope
	 */
	public Isotope(Isotope isotope) {
		// copy that into this...
		this(isotope.atomicNumber, isotope.elementName, isotope.elementSymbol, isotope.halfLife, isotope.mass);
	}

	/**
	 * A copy constructor that creates a new copy of the given <code>isotope</code>
	 * but with the specified mass.
	 * 
	 * @param isotope
	 * @param mass
	 */
	public Isotope(Isotope isotope, Double mass) {
		this(isotope.atomicNumber, isotope.elementName, isotope.elementSymbol, isotope.halfLife, mass);
	}

	@Override
	public String toString() {
		return String.format("%s (%d-%s), %.2f", this.elementName, this.atomicNumber, this.elementSymbol, this.mass);
	}

	public int getAtomicNumber() {
		return atomicNumber;
	}

	public String getElementName() {
		return elementName;
	}

	public String getElementSymbol() {
		return elementSymbol;
	}

	public double getHalfLife() {
		return halfLife;
	}

	public Double getMass() {
		return mass;
	}

	/**
	 * Decays this isotope by the given {@link Duration}.
	 * 
	 * @param year
	 */
	public void decay(Duration d) {
		this.mass = this.mass * Math.pow(.5, ( (d.toDays()/365.25) / this.halfLife));
	}
	

}
