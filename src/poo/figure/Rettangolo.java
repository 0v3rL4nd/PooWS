package poo.figure;

public class Rettangolo extends Figura{
	private double altezza;
	public Rettangolo( double base, double altezza ) {
		super(base);
		this.altezza=altezza;
	}
	public double getBase() { return getDimensione(); }
	public double getAltezza() { return altezza; }
	public double perimetro() {
		return getDimensione()*2+altezza*2;
	}//perimetro
	public double area() {
		return getDimensione()*altezza;
	}//area
	public String toString() {
		return "Rettangolo("+getDimensione()+","+altezza+")";
	}//toString
	public boolean equals( Object x ) {
		if( !(x instanceof Rettangolo) ) return false;
		if( x==this ) return true;
		Rettangolo r=(Rettangolo)x;
		return getDimensione()==r.getBase() && altezza==r.altezza;
	}//equals
	
	public int hashCode() {
		final int M=43;
		int h=Double.valueOf(getDimensione()).hashCode();
		h=h*M+Double.valueOf(altezza).hashCode();
		return h;
	}//hashCode
}//Rettangolo
