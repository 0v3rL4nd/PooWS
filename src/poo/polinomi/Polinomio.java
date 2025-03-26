package poo.polinomi;

public interface Polinomio extends Iterable<Monomio>{
	
	default int size() {
		int c=0;
		for( Monomio m: this ) c++;
		return c;
	}//size
	
	void add( Monomio m );
	
	default Polinomio add( Polinomio p ) {
		Polinomio somma=factory(); //vuoto
		for( Monomio m1: this ) somma.add(m1);
		for( Monomio m2: p ) somma.add(m2);
		return somma;
	}//add
	
	default Polinomio mul( Polinomio p ) {
		Polinomio prodotto=factory();
		for( Monomio m: this )
			prodotto=prodotto.add( p.mul(m) );
		return prodotto;
	}//mul
	
	default Polinomio mul( Monomio m ) {
		Polinomio prodotto=factory();
		for( Monomio q: this )
			prodotto.add( q.mul(m) );
		return prodotto;
	}//mul
	
	default double valore( double x ) {
		double v=0;
		for( Monomio m: this )
			v=v+m.coeff()*Math.pow( x, m.grado() ); 
		return v;
	}//valore
	
	default Polinomio derivata() {
		Polinomio d=factory();
		for( Monomio m: this ) {
			if( m.grado()!=0 )
				d.add( new Monomio(m.coeff()*m.grado(), m.grado()-1 ) );
		}
		return d;
	}//derivata
	
	Polinomio factory();
	
}//Polinomio
