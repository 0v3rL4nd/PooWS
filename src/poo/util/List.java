//Author: Libero Nigro
package poo.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<T> extends Iterable<T>{
	//L'interfaccia si ispira liberamente a quella di java.util.List<T>
	
	default int size() {
		int c=0;
		for( T x: this ) c++;
		return c;
	}//size
	
	default void clear() {
		Iterator<T> it=this.iterator();
		while( it.hasNext() ) {
			it.next();
			it.remove();
		}
	}//clear
	
	default boolean contains( T e ) {
		for( T x: this )
			if( x.equals(e) ) return true;
		return false;
	}//contains
	
	default void add( T e ) {
		ListIterator<T> lit=listIterator( size() );
		lit.add( e );
	}//add
	
	default void remove( T e ) {
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			T x=it.next();
			if( x.equals(e) ) { it.remove(); break; }
		}
	}//remove
	
	default boolean isEmpty() {
		return !listIterator().hasNext();
	}//isEmpty
	
	default boolean isFull() {
		return false;
	}//isFull
	
	static <T> void sort( List<T> lis, Comparator<? super T> c ){//bubble sort
		//default: bubble sort
		if( lis.size()<=1 ) return;
		boolean scambi=true;
		int limite=lis.size(), pus=0; //Posizione Ultimo Scambio
		while( scambi ) {
			ListIterator<T> lit=lis.listIterator();
			//certamente esistono almeno due elementi
			T x=lit.next(), y=null;
			scambi=false; //ottimismo
			while( lit.nextIndex()<limite ) {
				y=lit.next();
				if( c.compare(x,y)>0 ) {
					lit.set(x);
					lit.previous(); lit.previous(); //in quanto: y x^
					lit.set(y);
					lit.next(); lit.next(); //la seconda next equivale a: x=lit.next();
					scambi=true; pus=lit.previousIndex();
				}
				else { x=y; }
			}//while interno
			limite=pus;
		}//while esterno
	}//sort
	
	static <T> void sort( List<T> lis, Comparator<? super T> c, boolean flag ) {
		if( !flag ) sort(lis,c);
		else {
			//TODO mediante insertion sort
		}
	}//sort
	
	ListIterator<T> listIterator();
	ListIterator<T> listIterator( int pos );
}//List
