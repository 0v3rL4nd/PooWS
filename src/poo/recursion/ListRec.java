package poo.recursion;
import java.util.Iterator;
import java.util.NoSuchElementException;
import poo.util.CollezioneOrdinata;

public class ListRec<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	private Nodo<T> testa=null;
	
	public boolean isEmpty() { return testa==null; }
	public boolean isFull() { return false; }
	
	@Override
	public int size() {
		return size(testa);
	}//size
	
	private int size( Nodo<T> testa ) {
		if( testa==null ) return 0;
		return 1+size( testa.next ); //1+size della lista residua
	}//size
	
	@Override
	public boolean contains( T x ) {
		return contains( testa, x ); //delegazione del compito al metodo private
	}//contains
	
	private boolean contains( Nodo<T> testa, T x ) {
		if( testa==null ) return false;
		if( testa.info.equals(x) ) return true; //x è nel nodo testa
		if( testa.info.compareTo(x)>0 ) return false;
		return contains( testa.next, x );
	}//contains
	@Override
	public T get( T x ) {
		return get( testa, x );
	}//get
	
	private T get( Nodo<T> testa, T x ) {
		if( testa==null ) return null;
		if( testa.info.equals(x) ) return testa.info;
		if( testa.info.compareTo(x)>0 ) return null;
		return get( testa.next, x ); //la ricerca continua sulla lista residua
	}//get
	
	@Override
	public void clear() {
		testa=null;
	}//clear
	
	@Override
	public void add( T x ) {
		testa=add( testa, x );
	}//add
	
	private Nodo<T> add( Nodo<T> testa, T x ){
		if( testa==null || testa.info.compareTo(x)>=0 ) {
			//inserimento in testa o in lista vuota
			Nodo<T> n=new Nodo<>(); n.info=x; n.next=testa;
			return n;
		}
		testa.next=add( testa.next, x ); //rimando ricorsivo sulla lista residua
		return testa;
	}//add
	
	@Override
	public void remove( T x ) {
		testa=remove(testa,x);
	}//remove
	
	private Nodo<T> remove( Nodo<T> testa, T x ){
		if( testa==null || testa.info.compareTo(x)>0 ) return testa;
		if( testa.info.equals(x) ) return testa.next;
		testa.next=remove( testa.next, x );
		return testa;
	}//remove
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		toString( testa, sb );
		sb.append("]");
		return sb.toString();
	}//toString
	
	private void toString( Nodo<T> testa, StringBuilder sb ) {
		if( testa==null ) return;
		sb.append( testa.info );
		if( testa.next!=null ) sb.append(", ");
		toString( testa.next, sb );
	}//toString
	
	public static void main( String[] args ) {
		ListRec<Integer> lista=new ListRec<>();
		lista.add(10); lista.add(3); lista.add(6); lista.add(-1);
		System.out.println(lista+" size="+lista.size());
		System.out.println("Esiste -1? "+lista.contains(-1));
		lista.remove(-1);
		System.out.println("Esiste -1? "+lista.contains(-1));
		System.out.println(lista+" size="+lista.size());
	}//main
	
	public Iterator<T> iterator(){ return new IteratoreSuLista(); }
	
	private class IteratoreSuLista implements Iterator<T>{//mai static
		private Nodo<T> pre=null, cor=null; //cor punta all'elemento corrente
		
		public boolean hasNext() {
			if( cor==null ) return testa!=null;
			//cor punta ad un elemento GIA' consumato
			return cor.next!=null;
		}//hasNext
		
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			if( cor==null ) cor=testa;
			else {
				pre=cor; cor=cor.next;
			}
			return cor.info; //stiamo consumando l'elemento puntato da cor
		}//next
		
		public void remove() {
			//condizione per rimuovere e' che pre sia != cor
			if( pre==cor ) throw new IllegalStateException();
			//il nodo corrente e' sempre puntato da cor
			if( cor==testa ) testa=testa.next;
			else {
				pre.next=cor.next; //bypass
			}
			cor=pre; //arretrare cor per impedire una remove dopo una remove
		}//remove
		
	}//IteratoreSuLista
}//ListRec
