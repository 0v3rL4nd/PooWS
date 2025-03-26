package poo.app;

import java.util.Scanner;

public class TestArgs {
	public static void main( String[] args ) {
		int[] v=null;
		
		if( args.length==0 ) {
			Scanner sc=new Scanner( System.in );
			System.out.print("n=");
			int n=sc.nextInt(); sc.nextLine();
			v=new int[n];
			System.out.println("Inserisci uno per line "+n+" interi");
			for( int i=0; i<v.length; ++i ) {
				v[i]=sc.nextInt(); sc.nextLine();
			}
			sc.close();
		}
		else {
			v=new int[ args.length ];
		    for( int i=0; i<v.length; ++i )
			   v[i]=Integer.parseInt( args[i] );
		}
		
		System.out.println( java.util.Arrays.toString(v) );
		
	}
}
