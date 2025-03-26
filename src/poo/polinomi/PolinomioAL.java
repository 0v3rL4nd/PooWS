package poo.polinomi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PolinomioAL extends PolinomioAstratto{
	private List<Monomio> poli=new ArrayList<>();
	
	public Iterator<Monomio> iterator(){ return poli.iterator(); }
	public PolinomioAL factory() { return new PolinomioAL(); }
	public int size() { return poli.size(); }
	
	public void add( Monomio m ) {
		if( m.coeff()==0 ) return;
		int i=Collections.binarySearch(poli, m);
		if( i>=0 ) {
			Monomio q=poli.get(i);
			q=q.add(m);
			if( q.coeff()!=0 ) poli.set(i, q);
			else poli.remove(i);
		}
		else {
			for( i=0; i<poli.size(); ++i ) {
				if( poli.get(i).compareTo(m)>0 ) break;
			}
			poli.add(i,m);
		}
	}//add
}//PolinomioAL
