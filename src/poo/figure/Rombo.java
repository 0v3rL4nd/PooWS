package poo.figure;

public class Rombo extends Figura{
	private double diagMagg;
	public Rombo( double diagMin, double diagMagg ) {
		super( diagMin );
		this.diagMagg=diagMagg;
	}
	public double getDiagMin() { return getDimensione(); }
	public double getDiagMagg() { return diagMagg; }
	public double lato() { 
		double d1=diagMagg/2, d2=getDimensione()/2;
		return Math.sqrt(d1*d1+d2*d2);
	}//lato
	public double perimetro() {
		return 4*lato();
	}//perimetro
	public double area() {
		return (getDimensione()*diagMagg)/2;
	}//area
	
	public String toString() {
		return "Rombo("+getDimensione()+","+diagMagg+")";
	}//rombo
	
	public boolean equals( Object x ) {
		if( !(x instanceof Rombo) ) return false;
		if( x==this ) return true;
		Rombo r=(Rombo)x;
		return getDimensione()==r.getDiagMin() && diagMagg==r.diagMagg;
	}//equals
	
	public int hashCode() {
		final int M=43;
		int h=Double.valueOf(getDimensione()).hashCode();
		h=h*M+Double.valueOf(diagMagg).hashCode();
		return h;
	}//hashCode
}//Rombo
