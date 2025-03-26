package poo.util;

import java.util.Scanner;

public class BucketSort{
	public static void main( String[] args ){
		Scanner sc=new Scanner(System.in);
		int []b=new int[101]; //indici da 0 a 100
		//l’array b è inizializzato a tutti 0
		for(;;){
			int x=sc.nextInt();
			if( x<0||x>100) break;
			b[x]++; //conta questo x
		}
		System.out.print("[");
		boolean flag=true; //siamo sul primo valore
		for( int i=0; i<b.length; ++i )
			if( b[i]>0 ) {
				for( int j=0; j<b[i]; ++j ) {
					if( flag ) {
						System.out.printf("%1d",i);
						flag=false;
					}
					else
						System.out.printf("%4d",i);
				}
			}
		System.out.println("]");
		sc.close();
	}//main
}//BucketSort

