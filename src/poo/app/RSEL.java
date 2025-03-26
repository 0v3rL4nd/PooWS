package poo.app;

import java.util.Scanner;
import poo.sistema.Sistema;
import poo.sistema.Gauss;
import poo.sistema.GaussDiagonale;

public class RSEL {
	public static void main( String[] args ) {
		System.out.println("Risoluzione di un Sistema di n Equazioni Lineari in n Incognite.");
		Scanner sc=new Scanner ( System.in );
		System.out.print("n= ");
		int n=sc.nextInt(); sc.nextLine();
		double[][] a=new double[n][n];
		double[] y=new double[n];
		System.out.println("Fornisci i coefficienti della matrice "+n+"x"+n+" uno per linea.");
		for( int i=0; i<n; ++i )
			for( int j=0; j<n; ++j ) {
				System.out.print("a["+i+","+j+"]=");
				a[i][j]=sc.nextDouble(); sc.nextLine();
			}
		System.out.println("Fornisci ora i(gli) "+n+" termini noti.");
		for( int i=0; i<n; ++i ) {
			System.out.print("y["+i+"]=");
			y[i]=sc.nextDouble(); sc.nextLine();
		}
		Sistema s=new GaussDiagonale(a,y);
		System.out.println("Sistema di partenza.");
		System.out.println(s);
		double[] x=null;
		
		try {
			x=s.risolvi();
		}catch( RuntimeException e ) {
			System.out.println("Sistema Singolare!");
			System.exit(-1);
		}finally {
			sc.close();
		}
		
		System.out.println("Sistema triangolare.");
		System.out.println(s);
		
		System.out.println("Soluzione:");
		for( int i=0; i<n; ++i )
			System.out.printf( "x["+i+"]=%10.2f%n",x[i] );
		System.out.println("Bye.");
		
	}//main
}//RSEL
