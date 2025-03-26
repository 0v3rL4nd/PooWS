package poo.agendina;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class AgendinaLL extends AgendinaAstratta{
	private LinkedList<Nominativo> tabella=new LinkedList<>();
	
	public int size() { return tabella.size(); }
	
	public void aggiungi( Nominativo n ) {
		//non si fa la ricerca binaria per ovvie ragioni
		ListIterator<Nominativo> lit=tabella.listIterator();
		boolean flag=false;
		while( lit.hasNext() && !flag ) {
			Nominativo x=lit.next();
			if( x.equals(n) ) lit.set(n); //update
			else if( x.compareTo(n)>0 ) {
				lit.previous(); //arretra il cursore del list iterator
				lit.add(n); flag=true;
			}
		}
		if( !flag ) lit.add(n);
	}//aggiungi
	
	public void rimuovi( Nominativo n ) {
		tabella.remove(n);
	}//rimuovi
	
	public Iterator<Nominativo> iterator(){ return tabella.iterator(); }
	
}//AgendinaLL
