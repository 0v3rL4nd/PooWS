package poo.polinomi;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class PolinomioMap extends PolinomioAstratto{
	private Map<Monomio,Monomio> poli=new TreeMap<>();

	public Iterator<Monomio> iterator(){ return poli.values().iterator(); }
	public PolinomioMap factory() { return new PolinomioMap(); }
	public int size() { return poli.size(); }

	public void add( Monomio m ) {
		if( m.coeff()==0 ) return;
		if( poli.containsKey(m) ) {
			Monomio q=poli.get(m);
			q=q.add(m);
			if( q.coeff()==0 ) poli.remove(m);
			else poli.put(m, q);
		}
		else poli.put(m, m);
	}//add

}//PolinomioMap
