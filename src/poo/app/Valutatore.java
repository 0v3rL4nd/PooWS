package poo.app;

import java.util.Scanner;

import poo.string.OperatoreScorretto;
import poo.string.ValutatoreEspressione;

public class Valutatore {
	public static void main( String[] args ) throws OperatoreScorretto{
		System.out.println("Valutatore interattivo di espressioni aritmetiche intere.");
		String expr=null;
		Scanner sc=new Scanner( System.in );
		ValutatoreEspressione v=new ValutatoreEspressione();
		for( ;; ) {
			System.out.print("expr> ");
			expr=sc.nextLine();
			if( expr.equalsIgnoreCase("STOP") ) break;
			try {
				//expr e' un'espressione
				int ris=v.valuta(expr);
				System.out.println(expr+"="+ris);
			}
			catch( Exception e ) {
				e.printStackTrace();
				if( e instanceof NumberFormatException )
					System.out.println("Espressione malformata per operando scorretto.");
				else if( e instanceof OperatoreScorretto ) {
					System.out.println("Espressione malformata per operatore scorretto.");
				}
				else System.out.println("Espressione errata.");
			}
		}
		System.out.println("Bye.");
		sc.close();
	}//main
}//Valutatore
