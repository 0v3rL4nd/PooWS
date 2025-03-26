package poo.app;

import poo.date.Data;
import poo.razionali.Razionale;
import poo.util.Array;

public class TestSort {
	public static void main( String[] args ) {
		String[] a= {"vino","alfa","zaino","lupo","abaco"};
		Data[] b= { new Data(), new Data(1,1,2000), new Data(31,12,1999) };
		Razionale[] c= { new Razionale(4,8), new Razionale(12,18), 
				new Razionale(8,20), new Razionale(40,30), new Razionale(18,60) };
	
		System.out.println( java.util.Arrays.toString(a) );
		Array.selectionSort(a);
		System.out.println( java.util.Arrays.toString(a) );
		
		System.out.println( java.util.Arrays.toString(b) );
		Array.insertionSort(b);
		System.out.println( java.util.Arrays.toString(b) );

		System.out.println( java.util.Arrays.toString(c) );
		Array.bubbleSort(c);
		System.out.println( java.util.Arrays.toString(c) );
	}//main
}//TestSort
