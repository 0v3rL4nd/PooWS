package poo.agendina;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class AgendinaMap extends AgendinaAstratta{
	private Map<Nominativo,Nominativo> tabella=new TreeMap<>();
	
	public int size() { return tabella.size(); }
	
	public Iterator<Nominativo> iterator(){
		return tabella.values().iterator();
	}//iterator
	
	public void aggiungi( Nominativo n ) {
		tabella.put(n, n);
	}//aggiungi
	
	public void rimuovi( Nominativo n ) {
		tabella.remove(n);
	}//remove
	
}//AgendinaMap
