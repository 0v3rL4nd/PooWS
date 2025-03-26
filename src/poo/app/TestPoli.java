package poo.app;

import poo.polinomi.Monomio;
import poo.polinomi.Polinomio;
import poo.polinomi.PolinomioAL;
import poo.polinomi.PolinomioLL;
import poo.polinomi.PolinomioMap;
import poo.polinomi.PolinomioSet;

public class TestPoli {
	
	public static void main( String[] args ) {
		Polinomio p1=new PolinomioLL();
		p1.add( new Monomio(0,4) );
		p1.add( new Monomio(-2,3) );
		p1.add( new Monomio(3,5) );
		p1.add( new Monomio(4,3) );
		p1.add(  new Monomio(-6,0));
		p1.add( new Monomio(5,7) );
		System.out.println(p1);
		
		Polinomio p2=new PolinomioSet();
		p2.add( new Monomio(-2,5) );
		p2.add( new Monomio(8,0) );
		p2.add( new Monomio(-1,3) );
		System.out.println(p2);
		
		Polinomio p3=p1.add(p2);
		System.out.println("polinomio somma: "+p3);
		
		Polinomio p4=p1.mul(p2);
		System.out.println("polinomio prodotto: "+p4);
		
		System.out.println("derivata prima del prodotto: "+p4.derivata());
		System.out.println("prodotto valutato in x=1: "+p4.valore(1));
	}//main
	
}//TestPoli
