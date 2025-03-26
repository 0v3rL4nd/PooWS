package poo.polinomi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class PolinomioLL extends PolinomioAstratto{
	List<Monomio> poli=new LinkedList<>();
	
	@Override
	public PolinomioLL factory() { return new PolinomioLL(); }
	public Iterator<Monomio> iterator(){ return poli.iterator(); }
	public int size() { return poli.size(); }
	
	public void add( Monomio m ) {
		if( m.coeff()==0 ) return;
		ListIterator<Monomio> lit=poli.listIterator();
		boolean flag=false; 
		while( lit.hasNext() && !flag ) {
			Monomio q=lit.next();
			if( q.equals(m) ) {
				q=q.add(m);
				if( q.coeff()==0 ) lit.remove();
				else lit.set(q);
				flag=true;
			}
			else if( q.compareTo(m)>0 ) { 
				lit.previous(); lit.add(m); flag=true;
			}
		}
		if( !flag ) lit.add(m);
	}//add
	
}//PolinomioLL
