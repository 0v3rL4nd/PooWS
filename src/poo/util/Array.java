package poo.util;

import java.util.PriorityQueue;

public final class Array {
	private Array() {};
	
	public static <T extends Comparable<? super T>> void selectionSort( T[] v ) {
		for( int j=v.length-1; j>0; --j ) {
			int iMax=0;
			for( int i=1; i<=j; ++i )
				if( v[i].compareTo(v[iMax])>0 ) iMax=i;
			//scambia v[iMax] con v{j]
			T tmp=v[j];
			v[j]=v[iMax];
			v[iMax]=tmp;
		}
	}//selectionSort
	
	public static <T extends Comparable<? super T>> void bubbleSort( T[] v ) {
		int indiceUltimoScambio=v.length-1;
		boolean scambi=true; //pessimismo
		while( scambi ) {
			scambi=false; //ottimismo!
			int j=indiceUltimoScambio;
			for( int i=0; i<=j-1; ++i ) {
				if( v[i].compareTo(v[i+1])>0 ) {
					//scambia v[i] con v[i+1]
					T park=v[i];
					v[i]=v[i+1];
					v[i+1]=park;
					scambi=true; //registra che c'è stato uno scambio
					indiceUltimoScambio=i;
				}
			}//for
		}//while
	}//bubbleSort
	
	public static <T extends Comparable<? super T>> void insertionSort( T[] v ) {
		for( int i=0; i<v.length; ++i ) {
			//ordina elemento v[i]
			T x=v[i];
			int j=i;
			while( j>0 && v[j-1].compareTo(x)>0 ) {
				v[j]=v[j-1];
				--j;
			}
			v[j]=x; //insertion sort di x
		}
	}//insertionSort
	
	public static void selectionSort( int[] v ) {
		//TODO
	}//selectionSort
	
	public static void selectionSort( double[] v ) {
		//TODO
	}//selectionSort
	
	//similmente per bubbleSort e per insertionSort
	
	public static <T> int ricercaLineare( T[] v, T x ) {
		for( int i=0; i<v.length; ++i )
			if( v[i].equals(x) ) return i;
		return -1;
	}//ricercaLineare

	
	public static <T extends Comparable<? super T>> int ricercaBinaria( T[] v, T x ) {
		//PRE: v e' ordinato per valori crescenti
		int inf=0, sup=v.length-1;
		while( inf<=sup ) {
			int med=(inf+sup)/2;
			if( v[med].equals(x) ) return med;
			if( v[med].compareTo(x)>0 ) sup=med-1;
			else inf=med+1;
		}
		return -1;		
	}//ricercaBinaria
	
	public static <T extends Comparable<? super T>> int ricercaBinaria( Vector<T> v, T x ) {
		//PRE: v e' ordinato per valori crescenti
		int inf=0, sup=v.size()-1;
		while( inf<=sup ) {
			int med=(inf+sup)/2;
			if( v.get(med).equals(x) ) return med;
			if( v.get(med).compareTo(x)>0 ) sup=med-1;
			else inf=med+1;
		}
		return -1;			
	}//ricercaBinaria
	
	public static <T extends Comparable<? super T>> int binarySearch( T[] v, T x ) {
		if( v.length==0 ) return -1;
		return binarySearch(v,x,0,v.length-1);
	}//binarySearch
	
	private static <T extends Comparable<? super T>> int binarySearch( T[] v, T x, int inf, int sup ) {
		if( inf>sup ) return -1;
		int med=(inf+sup)/2;
		if( v[med].equals(x) ) return med;
		if( v[med].compareTo(x)>0 ) return binarySearch(v,x,inf,med-1);
		return binarySearch(v,x,med+1,sup);
	}//binarySearch
	
	public static <T extends Comparable<? super T>> void mergeSort( T[] a ) {
		mergeSort( a, 0, a.length-1 );
	}//mergeSort
	
	private static <T extends Comparable<? super T>> void mergeSort( T[] a, int inf, int sup ) {
		if( inf<sup ) {
			int med=(inf+sup)/2;
			mergeSort( a, inf, med );
			mergeSort( a, med+1, sup );
			merge( a, inf, med, sup );
		}
	}//mergeSort
	
	private static <T extends Comparable<? super T>> void merge( T[] a, int inf, int med, int sup ) {
		T[] aus=(T[]) new Comparable[sup-inf+1];
		int i=inf, j=med+1, k=0;
		while( i<=med && j<=sup ) {
			if( a[i].compareTo(a[j])<0 ) {
				aus[k]=a[i]; ++i; 
			}
			else {
				aus[k]=a[j]; ++j; 
			}
			++k;
		}
		//gestione dei residui
		while( i<=med ) {
			aus[k]=a[i]; ++i; ++k;
		}
		while( j<=sup ) {
			aus[k]=a[j]; ++j; ++k;
		}
		//copia aus su a
		for( k=0; k<aus.length; ++k )
			a[k+inf]=aus[k];
	}//merge
	
	public static <T extends Comparable<? super T>> void heapSort( T[] a ) {
		Heap<T> h=new Heap<>(a.length);
		for( T x: a ) h.add(x); //1st step
		for( int i=0; i<a.length; ++i ) a[i]=h.remove(); //2nd step
	}//heapSort
	
	public static <T extends Comparable<? super T>> void heap_sort( T[] a ) {
		PriorityQueue<T> h=new PriorityQueue<>(a.length);
		for( T x: a ) h.offer(x); //1st step
		for( int i=0; i<a.length; ++i ) a[i]=h.poll(); //2nd step
	}//heapSort
	
	public static void main( String[] args ) {
		Integer[] v= {10,9,8,7,6,5,4,3,2,1};
		System.out.println("Situazione di partenza:");
		System.out.println(java.util.Arrays.toString(v));
		Array.heap_sort( v );
		System.out.println("Situazione finale:");
		System.out.println(java.util.Arrays.toString(v));
	}//main
		
}//Array
