package poo.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class Sorting {
	public static void main( String[] args ) {
		
		List<Integer> ls=new LinkedList<>( java.util.Arrays.asList(10,9,8,7,6,5,4,3,2,1) );
		ls.forEach( i->System.out.print(i+" ") ); System.out.println();
		/*
		Collections.sort(ls, new Comparator<>() {
			public int compare(Integer i, Integer j ) {
				return i-j;
			}
		} );
		ls.forEach( i->System.out.print(i+" ") ); System.out.println();
		*/
		Collections.sort( ls, (i,j)->{ return i-j; }
		);
		ls.forEach( i->System.out.print(i+" ") ); System.out.println();
		
		//TODO:
		//creare una lista di String ed esprimere l'ordinamento per
		//lunghezza crescente delle stringhe e a parita' di lunghezza in ordine alfabetico
		
	}
}
