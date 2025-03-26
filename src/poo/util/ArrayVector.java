package poo.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayVector<T> extends AbstractVector<T>{
	private int n, size;
	private T[] array;
	private int contaModifiche=0;
	public ArrayVector( int n ) {
		if( n<0 ) throw new IllegalArgumentException();
		this.n=n;
		this.size=0;
		array=(T[])new Object[n];
	}
	public ArrayVector() {
		this(17);
	}
	public ArrayVector( Vector<T> v ) {
		n=v.size();
		array=(T[])new Object[n];
		for( int i=0; i<n; ++i )
			array[i]=v.get(i);
		size=n;
	}
	
	public void clear() {
		size=0;
		contaModifiche++;
	}//clear
	
	public int size() { return size; }
	
	public boolean isEmpty() {
		return size==0;
	}//isEmpty
	
	public int indexOf( T elem ) {
		for( int i=0; i<size; ++i )
			if( array[i].equals(elem) ) return i;
		return -1;
	}//indexOf
	
	public T get( int i ) {
		if( i<0 || i>=size ) throw new IndexOutOfBoundsException();
		return array[i];
	}//get
	
	public void set( int i, T x ) {
		if( i<0 || i>=size ) throw new IndexOutOfBoundsException();
		array[i]=x;
		contaModifiche++;
	}//set
	 
	public void add( T elem ) {
		if( size==n ) {
			array=java.util.Arrays.copyOf(array, n*2);
			n=2*n;
		}
		array[size]=elem;
		size++;
		contaModifiche++;
	}//add
	
	public void add( int i, T x ) {
		if( i<0 || i>size ) throw new IndexOutOfBoundsException();
		if( size==n ) {
			array=java.util.Arrays.copyOf(array, n*2);
			n=2*n;
		}	
		//shift destro di una posizione da i a size-1
		for( int j=size-1; j>=i; --j )
			array[j+1]=array[j];
		array[i]=x;
		size++;
		contaModifiche++;
	}//add
	
	public void remove( int indice ) {
		if( indice<0 || indice>=size ) throw new IndexOutOfBoundsException();
		//shift sinistro di tutti gli elementi da indice+1 sino a size-1
		for( int j=indice+1; j<size; ++j )
			array[j-1]=array[j];
		array[size-1]=null;
		size--;
		if( size<n/2 ) {
			array=java.util.Arrays.copyOf(array, n/2);
			n=n/2;
		}
		contaModifiche++;
	}//remove
	
	public void remove( T x ) {
		int i=indexOf(x);
		if( i==-1 ) return;
		remove(i);
	}//remove
	
	public Vector<T> subVector( int da, int a ) {
		if( da<0 || da>=size || a<0 || a>=size || da>a ) throw new IndexOutOfBoundsException();
		Vector<T> v=new ArrayVector<T>(a-da);
		for( int i=da; i<a; ++i )
			v.add( array[i] );
		return v;
	}//subVector
	
	public Iterator<T> iterator(){
		return new ArrayVectorIterator();
	}//iterator
	
	private class ArrayVectorIterator implements Iterator<T>{
		//cor e' la freccia dell'iteratore
		//vale -1 all'inizio, oppure punta all'ultimo elemento gia' consumato
		//ossia punta all'elemento corrente
		private int cor=-1;
		private boolean rimuovibile=false;
		private int mioContatore=contaModifiche;
		
		public boolean hasNext() {
			return cor<size-1;
		}//next
		
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			if( mioContatore!=contaModifiche ) throw new ConcurrentModificationException();
			cor++;
			rimuovibile=true;
			return array[cor];
		}//next
		
		public void remove() {
			if( !rimuovibile ) throw new IllegalStateException();
			rimuovibile=false;
			if( mioContatore!=contaModifiche ) throw new ConcurrentModificationException();
			//rimuovere l'elemento corrente
			//1a soluzione: ArrayVector.this.remove(cor);
			//2 soluzione: shift left esplicito
			for( int j=cor+1; j<size; ++j )
				array[j-1]=array[j];
			cor--;
			size--;
			contaModifiche++;
			mioContatore++;
		}//remove
		
	}//ArrayVectorIterator
	
}//ArrayVector
