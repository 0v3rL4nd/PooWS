package poo.regex;

import java.util.Scanner;

public class RegexExpr {
	public static void main( String[] args ) {
		Scanner sc=new Scanner( System.in );
		String SIMPLE="\\d+([\\+\\-\\*/]\\d+)*";
		String EXPR="(\\d+|[\\+\\-\\*/\\(\\)])+";
		for(;;) {
			System.out.print("expr> ");
			String linea=sc.nextLine();
			if( linea.length()==0 ) break;
			if( linea.matches(SIMPLE) ) {
				System.out.println("Espr Simple="+linea);
			}
			else if( linea.matches(EXPR) ) {
				System.out.println("Expr="+linea);
			}
			else System.out.println(linea+" non e' una espressione valida");
		}
		System.out.println("Bye.");
		sc.close();
	}
}//RegexExpr
