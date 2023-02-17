package soc.unl;

import java.time.Duration;

/**
 * TODO: you need to write documentation for ALL classes and 
 * ALL non-trivial methods
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
	
	public Isotope(int atomicNumber, String elementName, String elementSymbol, double halfLife, Double mass) {
		this.atomicNumber = atomicNumber;
		this.elementName = elementName;
		this.elementSymbol = elementSymbol;
		this.halfLife = halfLife;
		this.mass = mass;
	}
	
	public Isotope(int atomicNumber, String elementName, String elementSymbol, Double halfLife) {
		this(atomicNumber, elementName, elementSymbol, halfLife, null);
	}
	
	public Isotope(Isotope isotope) {
		this(isotope.atomicNumber, isotope.elementName, isotope.elementSymbol, isotope.halfLife, isotope.mass);
	}	

	public Isotope(Isotope isotope, Double mass) {
		this(isotope.atomicNumber, isotope.elementName, isotope.elementSymbol, isotope.halfLife, mass);
	}	


	@Override
	public String toString() {
		return String.format("%s (%d-%s) %.2fg", this.elementName, this.atomicNumber, this.elementSymbol, this.mass);

	}
	
	private void doSomethingElse() {
		
	}
	
	/**
	 * Decays this isotope's mass by the given Duration
	 * @param year
	 */
	public void decay(Duration duration) {
		double result = this.mass * Math.pow(.5, ( (duration.toDays()/365.25) / this.halfLife));
		this.mass = result;
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
	
	

	
	
	

}
