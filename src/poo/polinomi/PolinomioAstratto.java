package poo.polinomi;

import java.util.Iterator;

public abstract class PolinomioAstratto implements Polinomio{
	public String toString() {
		StringBuilder sb=new StringBuilder(200);
		boolean flag=true; //true se siamo sul 1 monomio
		for( Monomio m: this ) {
			if( !flag && m.coeff()>0 ) sb.append("+");
			sb.append(m);
			if( flag ) flag=!flag;
		}
		return sb.toString();
	}//toString
	
	public boolean equals( Object x ) {
		if( !(x instanceof Polinomio ) ) return false;
		if( x==this ) return true;
		Polinomio p=(Polinomio)x;
		if( size()!=p.size() ) return false;
		Iterator<Monomio> i1=this.iterator(), i2=p.iterator();
		while( i1.hasNext() ) {
			Monomio m1=i1.next(), m2=i2.next();
			if( m1.coeff()!=m2.coeff() || m1.grado()!=m2.grado() ) return false;
		}
		return true;
	}//equals
	
	public int hashCode() {
		final int M=47;
		int h=0;
		for( Monomio m: this ) {
			String s=""+m.coeff()+m.grado();
			h=h*M+s.hashCode();
		}
		return h;
	}//hashCode

}//PolinomioAstratto
