
package poo.figure;
public abstract class Figura{
	
	private double dimensione;
	
	protected Figura ( double dim ){ this.dimensione =dim; }
	protected double getDimensione(){ return dimensione ;}
	
	public abstract double perimetro();
	public abstract double area();
	
}//Figura
