package poo.eratostene;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class CrivelloSet extends CrivelloAstratto {
	private Set<Integer> crivello=new LinkedHashSet<>();
	private final int N;
	public CrivelloSet( final int N ) {
		if( N<2 ) throw new IllegalArgumentException(N+" minore 2.");
		this.N=N;
		for( int i=2; i<=N; ++i ) crivello.add(i);
	}
	@Override
	public void filtra() {
		for( int x=2; x<=Math.round(Math.sqrt(N)); x=(x==2)?x+1:x+2 ) {
			if( crivello.contains(x) ) {//x e' il prossimo minimo
				//rimuoviamo i multipli di x/minimo
				int multiplo=x*2;
				while( multiplo<=N ) {
					crivello.remove(multiplo); //se non c'è, no problem
					multiplo=multiplo+x;
				}
			}
		}
	}
	
	public Iterator<Integer> iterator(){ return crivello.iterator(); }
	
	public static void main( String[] args ) {
		int N=10000;
		Crivello c=new CrivelloSet(N);
		System.out.println("Numeri primi tra 2 e "+N);
		c.filtra();
		System.out.println(c);
	}//main
	
}//Crivello
