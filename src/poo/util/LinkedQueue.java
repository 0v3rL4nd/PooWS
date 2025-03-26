package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> extends AbstractQueue<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	private Nodo<T> inizio=null, fine=null; //coda vuota
	private int size=0;
	
	//per ragioni didattiche si implementano TUTTI i metodi di interfaccia
	public int size() { return size; }
	public boolean contains( T x ) {
		Nodo<T> cor=inizio;
		while( cor!=null ) {
			if( cor.info.equals(x) ) return true;
			cor=cor.next;
		}
		return false;
	}//contains
	public void clear() {
		inizio=null; fine=null;
		size=0;
	}//clear
	
	public boolean isEmpty() { return inizio==null; }
	public boolean isFull() { return false; }
	
	public void offer( T x ) {
		Nodo<T> n=new Nodo<>();
		n.info=x; n.next=null; //n va posto alla fine della lista
		if( inizio==null ) inizio=n;
		else fine.next=n;
		fine=n;
		size++;
	}//offer
	
	public T poll() {
		if( inizio==null ) throw new NoSuchElementException();
		T x=inizio.info;
		inizio=inizio.next;
		if( inizio==null ) fine=null;
		size--;
		return x;
	}//pool
	
	public T peek() {
		if( inizio==null ) throw new NoSuchElementException();
		return inizio.info;
	}//peek

/*
	public String toString() {
		StringBuilder sb=new StringBuilder(150);
		sb.append("[");
		Nodo<T> cor=inizio;
		while( cor!=null ) {
			sb.append(cor.info);
			if( cor.next!=null ) sb.append(", ");
			cor=cor.next;
		}
		sb.append("]");
		return sb.toString();
	}//toString
*/	
	public Iterator<T> iterator(){ return new LinkedQueueIterator(); }
	
	private class LinkedQueueIterator implements Iterator<T>{
		Nodo<T> pre=null, cor=null;
		
		public boolean hasNext() {
			if( cor==null ) return inizio!=null;
			return cor.next!=null;
		}//hasNext
		
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			if( cor==null ) cor=inizio;
			else {
				pre=cor; cor=cor.next;
			}
			return cor.info; //consumiamo cor
		}//next
		
		public void remove() {
			if( pre==cor ) throw new IllegalStateException();
			//rimuoviamo il nodo puntato da cor
			if( cor==inizio ) {
				inizio=inizio.next;
				if( inizio==null ) fine=null;
			}
			else if( cor==fine ) {//esistono almeno 2 elementi
				pre.next=null;
				fine=pre;
			}
			else {
				//facciamo il bypass per rimuovere cor
				pre.next=cor.next;
			}
			size--;
			cor=pre; //eventualmente cor punta ancora ad un elemento gia' consumato
		}//remove
		
	}//LinkedQueueIterator
	
}//LinkedQueue
