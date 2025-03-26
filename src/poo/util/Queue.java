package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Queue<T> extends Iterable<T> {
	default int size() {
		int c=0;
		for( T e: this ) c++;
		return c;
	}//size
	default boolean contains( T x ) {
		for( T e: this )
			if( e.equals(x) ) return true;
		return false;
	}//contains
	default void clear() {
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			it.next(); it.remove();
		}
	}//clear
	default boolean isEmpty() {
		return !iterator().hasNext();
	}//isEmpty
	default boolean isFull() {
		return false;
	}//isFull
	void offer( T x ); //arriva in coda x
	default T poll() { //rimuove dalla testa e ritorna l'elemento
		Iterator<T> it=iterator();
		if( !it.hasNext() ) throw new NoSuchElementException();
		T x=it.next(); it.remove();
		return x;
	}//poll
	default T peek() { //come poll() ma non rimuove
		Iterator<T> it=iterator();
		if( !it.hasNext() ) throw new NoSuchElementException();
		return it.next();
	}//peek

}//Queue
