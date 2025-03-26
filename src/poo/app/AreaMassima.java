package poo.app;

import poo.figure.Cerchio;
import poo.figure.Figura;
import poo.figure.Quadrato;
import poo.figure.Rettangolo;
import poo.figure.Rombo;

public class AreaMassima {
	public static void main( String[] args ) {
		Figura[] figure= {
		   new Cerchio(5),
		   new Rettangolo(4,7),
		   new Quadrato(6),
		   new Rombo(4,9),
		   new Cerchio(8)
		};
		figuraAreaMassima(figure);
		System.out.println("Bye.");
	}//main
	static void figuraAreaMassima( Figura[] f ) {
		Figura fam=null;
		double am=0;
		for( int i=0; i<f.length; ++i ) {
			double a=f[i].area();
			if( a>am ) { fam=f[i]; am=a; }
		}
		System.out.println("Figura di area massima: "+fam+" area massima="+am);
	}//figuraAreaMassima
}//AreaMassima
