package poo.polinomi;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class PolinomioSet extends PolinomioAstratto{
	private Set<Monomio> poli=new TreeSet<>();
	
	public Iterator<Monomio> iterator(){ return poli.iterator(); }  
	public PolinomioSet factory() { return new PolinomioSet(); }
	public int size() { return poli.size(); }
	
	public void add( Monomio m ) {
		if( m.coeff()==0 ) return;
		if( !poli.contains(m) ) {
			poli.add(m);
			return;
		}
		Monomio q=null;
		Iterator<Monomio> it=poli.iterator();
		while( it.hasNext() ) {
			q=it.next();
			if( q.equals(m) ) {
				q=q.add(m);
				it.remove();
				break;
			}
		}
		if( q.coeff()!=0 ) poli.add(q);
	}//add
	
}//PolinomioSet
