package poo.agendina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AgendinaAL extends AgendinaAstratta {
	private List<Nominativo> tabella;
	
	public AgendinaAL( int capacita ) {
		if( capacita<=0 ) throw new IllegalArgumentException();
		tabella=new ArrayList<Nominativo>( capacita );
	}
	public AgendinaAL() { this(17); }
	
	public int size() { return tabella.size(); }
	
	public void aggiungi( Nominativo n ) {
		int i=Collections.binarySearch(tabella,n);
		if( i>=0 ) {
			tabella.set(i, n);
		}
		else {
			i=0;
			boolean flag=false;
			while( i<tabella.size() && !flag ) {
				if( tabella.get(i).compareTo(n)>0 ) {
					tabella.add(i,n);
					flag=true;
				}
				else i++;
			}
			if( !flag ) tabella.add(n);
		}
	}//aggiungi
	
	public Iterator<Nominativo> iterator(){ 
		return tabella.iterator();
	}//iterator
	
	public void rimuovi( Nominativo n ) {
		int i=Collections.binarySearch(tabella,n);
		if(i>=0)tabella.remove(i);
	}//rimuovi
	
}//AgendinaAL
