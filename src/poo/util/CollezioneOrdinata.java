package poo.util;

import java.util.Iterator;

public interface CollezioneOrdinata<T extends Comparable<? super T>> extends Iterable<T>{
	//concretizzare quanti piu' metodi e' possibile come metodi default
	default int size() {
		int c=0;
		for( T x: this ) c++;
		return c;
	}//size
	default void clear() {
		Iterator<T> it=iterator();
		while( it.hasNext() ) { it.next(); it.remove(); }
	}//clear
	default boolean contains( T x ) {
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			T y=it.next();
			if( y.equals(x) ) return true;
			if( y.compareTo(x)>0 ) return false;
		}
		return false;
	}//contains
	void add( T x );
	default void remove( T x ) {
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			T y=it.next();
			if( y.equals(x) ) { it.remove(); break; }
			if( y.compareTo(x)>0 ) return;
		}
	}//remove
	default T get( T x ) {
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			T y=it.next();
			if( y.equals(x) ) return y;
			if( y.compareTo(x)>0 ) return null;
		}
		return null;		
	}//get
	default boolean isEmpty() {
		return !iterator().hasNext();
	}//isEmpty
	default boolean isFull() { return false; }
}//CollezioneOrdinata
