package poo.agendina;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AgendinaSet extends AgendinaAstratta{
	private Set<Nominativo> tabella=new TreeSet<>();
	
	public int size() { return tabella.size(); }
	
	public Iterator<Nominativo> iterator(){ return tabella.iterator(); }
	
	public void aggiungi( Nominativo n ) {
		tabella.remove(n);
		tabella.add(n);
	}//aggiungi
	
	public void rimuovi( Nominativo n ) {
		tabella.remove(n);
	}//rimuovi
	
}//AgendinaSet
