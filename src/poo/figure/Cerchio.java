package poo.figure;

import poo.util.Mat;

public class Cerchio extends Figura{
	public Cerchio( double r ) {
		super(r);
	}
	public double getRaggio() { return getDimensione(); }
	public double perimetro() {
		return 2*Math.PI*getDimensione();
	}//perimetro
	public double area() {
		double r=getDimensione();
		return r*r*Math.PI;
	}//area
	public String toString() { return "Cerchio("+getDimensione()+")"; }
	public boolean equals( Object x ) {
		if( !(x instanceof Cerchio) ) return false;
		if( x==this ) return true;
		Cerchio c=(Cerchio)x;
		return Mat.sufficientementeProssimi(getRaggio(), c.getRaggio());
	}//equals
	public int hashCode() {
		return Double.valueOf( getDimensione() ).hashCode();
	}//hashCode
}//Cerchio
