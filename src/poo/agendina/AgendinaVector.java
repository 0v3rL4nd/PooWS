package poo.agendina;

import java.util.Iterator;
import poo.util.*;

public class AgendinaVector extends AgendinaAstratta {
	private Vector<Nominativo> tabella;
	public AgendinaVector( int n ) {
		if( n<=0 ) throw new IllegalArgumentException();
		tabella=new ArrayVector<>(n);
	}
	public AgendinaVector() {
		this(17);
	}
	
	public int size() {//ok per efficienza
		return tabella.size();
	}//size
	
	public void aggiungi( Nominativo n ) {
		int i=poo.util.Array.ricercaBinaria(tabella,n);
		if( i>=0 ) tabella.set(i, n); //update dell'elemento
		else {
			i=0;
			boolean flag=false;
			while( i<tabella.size() && !flag ) {
				Nominativo x=tabella.get(i);
				if( x.compareTo(n)>0 ) {
					tabella.add(i,n); flag=true;
				}
				else i++;
			}
			if( !flag ) tabella.add(n);
		}
	}//aggiungi
	
	public void rimuovi( Nominativo n ) {//ok per efficienza
		int i=poo.util.Array.ricercaBinaria(tabella,n);
		if( i<0 ) return;
		tabella.remove(i);
	}//rimuovi
	
	public Nominativo cerca( Nominativo n ) {//ok per efficienza
		int i=poo.util.Array.ricercaBinaria(tabella,n);
		if( i<0 ) return null;
		return tabella.get(i);
	}//cerca
	
	public Iterator<Nominativo> iterator(){
		return tabella.iterator();
	}//iterator
}//AgendinaVector
