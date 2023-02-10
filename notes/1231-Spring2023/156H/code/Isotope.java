package unl.soc;

import java.time.Duration;

public class Isotope {
	
	private final int atomicNumber;
	private final String elementName;
	private final String elementSymbol;
	private final double halfLife;
	private Double mass;
	//Very Good Reason

	public Isotope(int atomicNumber, String elementName, String elementSymbol, double halfLife, Double mass) {
		this.atomicNumber = atomicNumber;
		this.elementName = elementName;
		this.elementSymbol = elementSymbol;
		this.halfLife = halfLife;
		this.mass = mass;
	}

	public Isotope(int atomicNumber, String elementName, String elementSymbol, double halfLife) {
		this(atomicNumber, elementName, elementSymbol, halfLife, null);
	}
	
	/** 
	 * A copy constructor that copies the given Isotope into this 
	 * Isotope.
	 * @param isotope
	 */
	public Isotope(Isotope isotope) {
		this(isotope.atomicNumber, isotope.elementName, isotope.elementSymbol, isotope.halfLife, isotope.mass);
	}

	/**
	 * A copy constructor that copies the given Isotope except for the
	 * <code>mass</code> 
	 * 
	 * @param isotope
	 * @param mass
	 */
	public Isotope(Isotope isotope, double mass) {
		this(isotope.atomicNumber, isotope.elementName, isotope.elementSymbol, isotope.halfLife, mass);
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

	@Override
	public String toString() {
		return String.format("%s (%d-%s)\n", elementName, atomicNumber, elementSymbol);
	}
	
	/**
	 * Decays this radioactive isotope's mass by an appropriate amount
	 * given the decay period (in years).
	 * 
	 * @param year
	 * @return
	 */
	public void decay(Duration d) {
		double result = this.mass * Math.pow(.5, ( (d.toDays()/365.25) / this.halfLife));
		this.mass = result;
	}


}
