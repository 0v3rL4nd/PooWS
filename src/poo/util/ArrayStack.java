package poo.util;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class ArrayStack<T> extends AbstractStack<T> {
	private T[] contenuto;
	private int n, size;
	public ArrayStack( int n ) {
		if( n<=0 ) throw new IllegalArgumentException();
		this.n=n;
		contenuto=(T[])new Object[n];
		size=0;
	}
	public ArrayStack() { this(17); }
	
	//per ragioni didattiche si implementano TUTTI i metodi di interfaccia
	public int size() { return size; }
	public boolean contains( T x ) {
		for( T y: contenuto )
			if( y.equals(x) ) return true;
		return false;
	}//contains 
	public void clear(){
	    for( int i=0; i<size; ++i ) contenuto[i]=null;
	    size=0;
	}//clear
	public boolean isEmpty() { return size==0; }
	
	public void push( T x ) {
		if( size==n ) {
			contenuto=java.util.Arrays.copyOf(contenuto, 2*n);
			n=2*n;
		}
		contenuto[size]=x; //size punta al primo libero dell'array
		size++;
	}//push
	
	public T pop() {
		if( size==0 ) throw new NoSuchElementException();
		T x=contenuto[size-1]; 
		contenuto[size-1]=null; //non indispensabile ma ok
		size--;
		return x;
	}//pop
	
	public T peek() {
		if( size==0 ) throw new NoSuchElementException();
		return contenuto[size-1];
	}//peek
/*	
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			sb.append( it.next() );
			if( it.hasNext() ) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}//toString
*/	
	public Iterator<T> iterator(){ return new ArrayStackIterator(); }
	
	private class ArrayStackIterator implements Iterator<T>{
		private int cor=size; //fuori dallo stack
		private boolean rimuovibile=false;
		
		public boolean hasNext() {
			return cor>0; 
		}//hasNext
		
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			rimuovibile=true;
			T x=contenuto[cor-1];
			cor--;
			return x;
		}//next
		
		public void remove() {
			if( !rimuovibile ) throw new IllegalStateException();
			rimuovibile=false;
			//rimuovere l'elemento puntato da cor
			//left shift
			for( int j=cor+1; j<size; ++j )
				contenuto[j-1]=contenuto[j];
			size--;
			//lasciando inalterato cor esso punta ad un elemento consumato
		}//remove
		
	}//ArrayStackIterator
	
}//ArrayStack
