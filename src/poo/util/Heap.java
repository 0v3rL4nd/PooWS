package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<? super T>> implements Iterable<T>{
	private T[] heap;
	private int size; //punta all'ultimo occupato
	private int n; //capacita' dell'array heap
	
	@SuppressWarnings("unchecked")
	public Heap( int n ) {
		if( n<1 ) throw new IllegalArgumentException();
		this.n=n;
		heap=(T[]) new Comparable[n+1];
		size=0;
	}
	public Heap() { this(17); }
	
	public int size() { return size; }
	public boolean contains( T x ) {
		for( int i=1; i<=size; ++i )
			if( heap[i].equals(x) ) return true;
		return false;
	}//contains
	public void clear() {
		for( int i=1; i<=size; ++i ) heap[i]=null;
		size=0;
	}//clear
	
	public void add( T x ) {
		if( size==n ) {
			heap=java.util.Arrays.copyOf(heap, n*2+1);
			n=2*n;
		}
		size++;
		heap[size]=x; 
		int i=size; //figlio
		while( i>1 ) {//riaggiusta dell'heap upward
			if( heap[i].compareTo(heap[i/2])<0 ) {
				T tmp=heap[i]; heap[i]=heap[i/2]; //scambia figlio col padre
				heap[i/2]=tmp;
				i=i/2;
			}
			else break;
		}
	}//add
	
	public void offer( T x ) { add(x); }
	
	public T remove() {
		if( size==0 ) throw new NoSuchElementException();
		T x=heap[1];
		heap[1]=heap[size]; heap[size]=null;
		size--;
		//riaggiusta l'heap downward
		int i=1; //padre
		while( i<=size/2 ) {
			int j=2*i, k=2*i+1; //i due figli. Ma esiste k?
			int z=j; //ipotesi: il primo figlio e' il più piccolo
			if( k<=size && heap[k].compareTo(heap[z])<0 ) z=k;
			if( heap[i].compareTo(heap[z])>0 ) {
				//scambia
				T tmp=heap[i]; heap[i]=heap[z]; heap[z]=tmp;
				i=z;
			}
			else break;
		}
		return x;
	}//remove
	public T poll() { return remove(); }
	
	public void remove( T x ) {
		if( size==0 ) return;
		if( x.equals(heap[1]) ) {
			remove();
			return;
		}
		int i=1;
		for( ; i<=size; ++i )
			if( heap[i].equals(x) ) break;
		if( i>size ) return;
		int inf=i+1, sup=size;
		size=i-1;
		for( int j=inf; j<=sup; ++j )
			this.add( heap[j] );
		heap[sup]=null;
	}//remove
	
	public T peek() {
		if( size==0 ) throw new NoSuchElementException();
		return heap[1];
	}//peek
	
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");		
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			sb.append(it.next());
			if( it.hasNext() ) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}//toString
	
	public Iterator<T> iterator(){
		return new HIterator();
	}//iterator
	
	private class HIterator implements Iterator<T>{
		private T cur=null;
		private List<T> lista=new LinkedList<>();
		private Iterator<T> it;
		public HIterator() {
			for( int i=1; i<=size; ++i )
				lista.add( heap[i] );
			it=lista.iterator();
		}
		public boolean hasNext() {
			return it.hasNext();
		}
		public T next() {
			cur=it.next();
			return cur;
		}
		public void remove() {
			if( cur==null ) throw new IllegalStateException();
			Heap.this.remove(cur);
			it.remove();
			cur=null;
		}
	}//HIterator
	
	public static void main( String [] args ) {
		Heap<Integer> hi=new Heap<>();
		hi.add(43); hi.add(93);
		hi.add(60); hi.add(57); hi.add(59);
		hi.add(84); hi.add(75);  hi.add(58);
		hi.add(96); hi.add(91); 
		System.out.println(hi);

		Iterator<Integer> it=hi.iterator();
		while( it.hasNext() ) {
			int x=it.next();
			System.out.println("next="+x);
			if( x==75 ) it.remove();
			else if( x==59 ) it.remove();
		}
		System.out.println(hi);
		
		hi.remove(84);
		System.out.println(hi);
		
	}//main
}//Heap
