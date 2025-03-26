package poo.app;

import java.util.Scanner;

public class TestRegex {
	public static void main( String[] args ) {
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci una espressione: ");
		String expr=sc.nextLine();
		
		String SIMPLE_EXPR="\\d+([\\+\\-\\*/]\\d+)*";
		
		String EXPR="([\\+\\-\\*/\\(\\)]|\\d+)+";
		
		if( expr.matches(EXPR) )
			System.out.println(expr+" e' una espressione corretta.");
		else
			System.out.println(expr+" e' una espressione scorretta.");
			
	}
}
