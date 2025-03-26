package poo.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {
	private static final long serialVersionUID = -468361862090250266L;
	private final int[] array;
	private final int inf, sup;
	private final int MAX=8192;//2^13
	public ParallelMergeSort( final int[] array, final int inf, final int sup ) {
		this.array = array;
		this.inf = inf;
		this.sup = sup;
	}
	
	public void mergeSort() {
		mergeSort( array, 0, array.length-1 );
	}//mergeSort
	
	private void mergeSort( int[] array, int inf, int sup  ) {
		if( inf<sup ) {
			int mid=(inf+sup)/2;
			mergeSort(array,inf,mid);
			mergeSort(array,mid+1,sup);
			merge(array,inf,mid,sup);
		}
	}//mergeSort

	private void merge(int[] array, int inf, int mid, int sup) {
		int[] aus=new int[sup-inf+1];
		int i=inf, j=mid+1, k=0;
		while( i<=mid && j<=sup ) {
			if( array[i]<array[j] ) { aus[k]=array[i]; i++; k++; }
			else { aus[k]=array[j]; j++; k++; }
		}
		while( i<=mid ) {
			aus[k]=array[i]; i++; k++;
		}
		while( j<=sup ) {
			 aus[k]=array[j];j++; k++;
		}
		for( i=inf; i<=sup; ++i )
			array[i]=aus[i-inf];
	}//merge
	
	protected void compute() {
		if( inf<sup ){
		    if( sup-inf<=MAX ) { // Sequential recursive implementation
		    	mergeSort( array, inf, sup );
		    } 
		    else { // Parallel implementation		    	
		      final int mid=(inf+sup)/2;	      
		      final ParallelMergeSort left = new ParallelMergeSort(array, inf, mid);
		      final ParallelMergeSort right = new ParallelMergeSort(array, mid+1, sup);
		      invokeAll( left, right );
		      merge( array, inf, mid, sup );
		    }
		}
	}//compute
	
	public static boolean sorted( int[] array ) {
		for( int i=1; i<array.length-1; ++i )
			if( array[i]<array[i-1] ) return false;
		return true;
	}//sorted
	
	public static void main( String[] args ) {
		final int N=10_000_000;
		System.out.println("N="+N);
		int[] v=new int[N];
		for( int i=0; i<N; ++i ) v[i]=N-i;
		System.out.println("Initial sorted: "+sorted(v));
		
		long start=System.currentTimeMillis(); //o System.nanoTime();
		new ParallelMergeSort(v, 0, v.length - 1).mergeSort();
		System.out.println("Sequential recursive SET="+(System.currentTimeMillis()-start)+" ms");
		System.out.println("Final sorted: "+sorted(v));
		
		for( int i=0; i<N; ++i ) v[i]=N-i;
		System.out.println("Initial sorted: "+sorted(v)); 
		start=System.currentTimeMillis();

		final ForkJoinPool forkJoinPool = 
			new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);
		forkJoinPool.invoke( new ParallelMergeSort(v, 0, v.length - 1) );	
		System.out.println("Parallel fork/join PET="+(System.currentTimeMillis()-start)+" ms");
		System.out.println("Final sorted: "+sorted(v));
		
		for( int i=0; i<N; ++i ) v[i]=N-i;
		System.out.println("Initial sorted: "+sorted(v));
		start=System.currentTimeMillis();
		java.util.Arrays.parallelSort(v);
		System.out.println("Arrays.parallelSort PET="+(System.currentTimeMillis()-start)+" ms");
		System.out.println("Final sorted: "+sorted(v)); 
	}//main
	
}//ParallelMergeSort

