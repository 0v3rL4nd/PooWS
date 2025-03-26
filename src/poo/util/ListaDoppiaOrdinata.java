package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoppiaOrdinata<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
	
	private static class Nodo<E>{
		E info;
		Nodo<E> next, prior;
	}//Nodo
	
	private Nodo<T> testa=null; //inizialmente la lista e' vuota
	private int size=0;

	//per ragioni didattiche si implementano TUTTI i metodi di interfaccia
	public int size() { return size; }
	
	public void clear() {
		testa=null; size=0;
	}//clear
	
	public boolean contains( T x ) {
		Nodo<T> cor=testa;
		while( cor!=null ) {
			if( cor.info.equals(x) ) return true;
			if( cor.info.compareTo(x)>0 ) return false; //ottimizziamo la ricerca lineare
			cor=cor.next; //avanziamo cor sul prossimo nodo
		}
		return false;
	}//contains
	
	public T get( T x ) {
		Nodo<T> cor=testa;
		while( cor!=null ) {
			if( cor.info.equals(x) ) return cor.info;
			if( cor.info.compareTo(x)>0 ) return null; //ottimizziamo la ricerca lineare
			cor=cor.next; //avanziamo cor sul prossimo nodo
		}
		return null;		
	}//get
	
	public boolean isEmpty() {
		return testa==null; //oppure: size==0
	}//isEmpty
	
	public boolean isFull() {
		return false;
	}//isFull
	
	public void add( T x ) {
		Nodo<T> n=new Nodo<>();
		n.info=x; n.next=null; n.prior=null;
		if( testa==null || testa.info.compareTo(x)>0 ) {//primi due casi
			//n va aggiunto in testa
			n.next=testa; //cosi si colloca n PRIMA della testa o capolista
			if( testa!=null ) testa.prior=n;
			testa=n;
		}
		else {//x va inserito dopo il primo elemento
			//troviamo la posizione del primo elemento >= ad x
			Nodo<T> pre=testa, cor=testa.next; //assegnazioni "furbe"
			while ( cor!=null && cor.info.compareTo(x)<0 ) {
				//avanziamo di una posizione sulla lista
				pre=cor;
				cor=cor.next;
			}
			//ultimi due casi: inserimento intermedio o dopo l'ultimo
			n.next=cor; //n va prima di cor e dopo pre
			n.prior=pre;
			//certamente pre!=null
			pre.next=n;
			if( cor!=null ) cor.prior=n;
		}
		size++;
	}//add
	
	public void remove( T x ) {//rimuove la prima occorrenza di x
		if( testa==null || testa.info.compareTo(x)>0 ) return;
		
		Nodo<T> cor=testa; //una sola variabile cor e' sufficiente
		
		while( cor!=null && cor.info.compareTo(x)<0 ) {
			//vai avanti
			cor=cor.next;
		}
		
		if( cor!=null && cor.info.equals(x) ) {//abbiamo trovato x
			//1 caso: x si trova nel primo elemento - va cambiata la testa della lista
			if( cor==testa ) { 
				testa=testa.next;
				if( testa!=null ) testa.prior=null;
			}
			else {
				//casi 2 e 3: si rimuove un nodo dal secondo all'ultimo
				//sono trattati facendo un bypass
				cor.prior.next=cor.next; //fa il bypass in avanti
				if( cor.next!=null ) cor.next.prior=cor.prior; //bypass all'indietro
			}
			size--;
		}
		
	}//remove
	
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
			if( cor==testa ) {
				testa=testa.next;
				if( testa!=null ) testa.prior=null;
			}
			else {
				pre.next=cor.next; //bypass in avanti
				//se non siamo sull'ultimo nodo
				if( cor.next!=null ) cor.next.prior=cor.prior; //bypass indietro
			}
			cor=pre; //arretrare cor per impedire una remove dopo una remove
			size--;
		}//remove
		
	}//IteratoreSuLista
	
}//ListaDoppiaOrdinata
