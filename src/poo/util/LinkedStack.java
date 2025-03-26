package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> extends AbstractStack<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	private Nodo<T> testa=null;
	private int size=0;
	
	//per ragioni didattiche si implementano TUTTI i metodi di interfaccia
	public int size() { return size; }
	public boolean contains( T x ) {
		Nodo<T> cor=testa;
		while( cor!=null ) {
			if( cor.info.equals(x) ) return true;
			cor=cor.next;
		}
		return false;
	}//contains
	public void clear() {
		testa=null; size=0;
	}//clear
	public boolean isEmpty() {
		return testa==null; //anche: size==0
	}//isEmpty
	public void push( T x ) {
		Nodo<T> n=new Nodo<>();
		n.info=x;
		//inseriamo n in testa allo stack
		n.next=testa;
		testa=n;
		size++;
	}//push
	public T pop() {
		if( testa==null ) throw new NoSuchElementException();
		T x=testa.info;
		testa=testa.next; //toglie effettivamente il nodo di testa
		size--;
		return x;
	}//pop
	public T peek() {
		if( testa==null ) throw new NoSuchElementException();
		return testa.info;
	}//peek

/*
	public String toString() {
		Nodo<T> cor=testa;
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		while( cor!=null ) {
			sb.append( cor.info );
			if( cor.next!=null ) sb.append(", ");
			cor=cor.next;
		}
		sb.append("]");
		return sb.toString();
	}//toString
*/	
	public Iterator<T> iterator() { return new StackIterator(); }
	
	private class StackIterator implements Iterator<T>{
		Nodo<T> pre=null, cor=null;
		public boolean hasNext() {
			if( cor==null ) return testa!=null;
			return cor.next!=null;
		}//hasNext
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			if( cor==null ) cor=testa;
			else {
				pre=cor; cor=cor.next;
			}
			return cor.info; //"consumo" cor.info
		}//next
		public void remove() {
			if( pre==cor ) throw new IllegalStateException();
			//rimuoviamo l'elemento/nodo corrente
			if( cor==testa ) testa=testa.next;
			else {
				pre.next=cor.next; //bypass
			}
			size--;
			cor=pre;
		}//remove
	}//StackIterator
	
}//LinkedStack
