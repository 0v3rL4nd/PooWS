package poo.util;

import java.util.Iterator;

/*
 * Interfaccia liberamente ispirata a java.util.List.
 * L'obiettivo è praticare con un array di Object in modo flessibile.
 */
public interface Vector<T> extends Iterable<T>{//ADT
	/*
	 * Ritorna la dimensione effettiva del vector, ossia
	 * quanti elementi sono effettivamente presenti nel vector.
	 */
	@SuppressWarnings("unused")
	default int size() {
		int c=0;
		for( T x: this ) c++;
		return c;
	}//size
	/*
	 * Svuota il vector. Subito dopo size=0.
	 */
	default void clear() {
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			it.next();
			it.remove();
		}
	}//clear
	/*
	 * Ritorna true se x è presente nel vector.
	 */
	default boolean contains( T x ) {
		for( T e: this )
			if( e.equals(x) ) return true;
		return false;
	}//contains
	/*
	 * Ritorna true se il vector è vuoto.
	 */
	default boolean isEmpty() {
		return size()==0;
	}//isEmpty
	/*
	 * Si aspetta 0<=indice<size, altrimenti genera una IndexOutOfBoundsException.
	 * Ritorna l'oggetto a quell'indice.
	 */
	default T get( int indice ) {
		if( indice<0 || indice>=size() ) 
			throw new IndexOutOfBoundsException();
		int i=0;
		for( T x: this ) {
			if( i==indice ) return x;
			i++;
		}
		return null;
	}//get
	/*
	 * Si aspetta 0<=indice<size, altrimenti genera una IndexOutOfBoundsException.
	 * Assegna alla posizione indice del vettore l'elemento x.
	 * Dopo questa set, get(indice) ritorna x.
	 */
	void set( int indice, T x );
	/*
	 * Ritorna l'indice della prima occorrenza di x nel vector, ammesso che x esista;
	 * altrimenti ritorna -1.
	 */
	default int indexOf( T x ) {
		int i=0;
		for( T e: this ) {
			if( e.equals(x) ) return i;
			i++;
		}
		return -1;	
	}//indexOf
	/*
	 * Estende il vector aggiungendo x come ultimo elemento. Dopo
	 * questa operazione la size e' incrementata di 1.
	 */
	default void add( T x ) {
		add( size(), x );
	}//add
	/*
	 * Si aspetta 0<=indice<=size altrimenti genera una IndexOutOfBoundsException.
	 * Inserisce x in posizione indice del vector, spostando prima gli elementi
	 * da indice a size-1 di una posizione a destra.
	 */
	void add( int indice, T x );
	/*
	 * Si aspetta 0<=indice<size altrimenti genera una IndexOutOfBoundsException.
	 * Rimuove l'elemento a quell'indice spostando di uno posto a sinistra tutti
	 * gli elementi da indice+1 a size-1
	 */
	void remove( int indice );
	/*
	 * Rimuove la prima occorrenza dell'elemento x nel vector.
	 * Equivale a remove( indexOf(x) ).
	 */
	default void remove( T x ) {
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			T e=it.next();
			if( e.equals(x) ) {
				it.remove();
				return;
			}
		}
	}//remove
	/*
	 * Si aspetta 0<=da,a<size ed in piu' da<=a.
	 * Crea e ritorna il contenuto del vector [da, a[
	 */
	Vector<T> subVector( int da, int a );
}//Vector
