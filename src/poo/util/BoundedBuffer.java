package poo.util;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class BoundedBuffer<T> extends AbstractQueue<T> {
	private T[] buffer;
	private int n, in, out, size;
	public BoundedBuffer( int n ) {
		if( n<=0 ) throw new IllegalArgumentException();
		this.n=n;
		buffer=(T[])new Object[n];
		in=0; out=0; size=0;
	}
	
	//per ragioni didattiche si implementano TUTTI i metodi di interfaccia
	public int size() { return size; }
	
	public boolean contains( T x ) {
		int i=out;
		for( int s=0; s<size; ++s ) {
			if( buffer[i].equals(x) ) return true;
			i=(i+1)%n;
		}
		return false;
	}//contains

	public void clear() {
		int i=out;
		for( int s=0; s<size; ++s ) {
			buffer[i]=null;
			i=(i+1)%n;
		}
		in=0; out=0; size=0;
	}//clear
	
	public boolean isEmpty() { return size==0; }
	public boolean isFull() { return size==n; }
	
	public void offer( T x ) {
		if( size==n ) throw new RuntimeException("Buffer full!");
		buffer[in]=x; in=(in+1)%n; size++;
	}//offer
	
	public T poll() {
		if( size==0 ) throw new NoSuchElementException();
		T x=buffer[out]; buffer[out]=null; //non indispensabile
		out=(out+1)%n; size--;
		return x;
	}//poll	
	
	public T peek() {
		if( size==0 ) throw new NoSuchElementException();
		return buffer[out];
	}//peek

/*
	public String toString() {
		StringBuilder sb=new StringBuilder(200);
		sb.append("[");
		for( int i=out, j=size; j>0; --j, i=(i+1)%n ) {
			sb.append( buffer[i] );
			if( j-1>0 ) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}//toString
*/
	
	public Iterator<T> iterator(){ return new BBIterator(); }
	
	private class BBIterator implements Iterator<T>{
		private int cor=-1; //fuori dal buffer
		private boolean rimuovibile=false;
		
		public boolean hasNext() {
			if( cor==-1 ) return size>0;
			return (cor+1)%n !=in;
		}//hasNext
		
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			rimuovibile=true;
			if( cor==-1 ) cor=out;
			else cor=(cor+1)%n;
			return buffer[cor]; //cor e' consumato
		}//next
		
		public void remove() {
			if( !rimuovibile ) throw new IllegalStateException();
			rimuovibile=false;
			//rimuovere l'elemento puntato da cor
			for( int j=(cor+1)%n; j!=in; j=(j+1)%n ) {
				//scorrere l'elemento in j sulla posizione precedente
				buffer[(j-1+n)%n]=buffer[j];
			}
			size--;
			in=(in-1+n)%n;
			cor=(cor-1+n)%n;
		}//remove
		
	}//BBIterator
		
}//BoundedBuffer
