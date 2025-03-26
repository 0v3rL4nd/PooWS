package poo.figure;

public class Quadrato extends Figura{

	public Quadrato( double lato ) {
		super(lato);
	}
	public double getLato() { return getDimensione(); }
	
	public double perimetro() {
		return 4*getDimensione();
	}//perimetro
	public double area() {
		double l=getDimensione();
		return l*l;
	}//area
	public String toString() {
		return "Quadrato("+getDimensione()+")";
	}//toString
	
	public boolean equals( Object x ) {
		if( !(x instanceof Quadrato) ) return false;
		if( x==this ) return true;
		Quadrato r=(Quadrato)x;
		return getDimensione()==r.getLato();
	}//equals
	
	public int hashCode() {
		int h=Double.valueOf(getDimensione()).hashCode();
		return h;
	}//hashCode
	
}//Quadrato
