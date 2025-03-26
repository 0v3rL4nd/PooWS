package poo.app;

import java.util.Scanner;

public class TestPolinomio {
	public static void main( String[] args ) {
		//ESEMPIO INPUT: -3x^4+2x^9-4+6x^4+x
		String UMON="(\\d+|(\\d+)?[xX](\\^\\d+)?)";
		String POL="\\-?"+UMON+"([\\+\\-]"+UMON+")*";
		
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci un polinomio: ");
		String linea=sc.nextLine();
		if( !linea.matches(POL) ) System.out.println(linea+" non e' un polinomio.");
		else {
			System.out.println(linea+" e' un polinomio.");
		}
		//TODO
		//1: data una stringa corretta polinomio, estrarre i suoi
		//monomi e costruire il corrispondente oggetto Polinomio
		//2: successivamente, leggere due polinomi/stringa da tastiera
		//ricostuire i due oggetti polinomi e quindi effettuare
		//operazioni aritmetiche su di essi, visualizzando i risultati.
	}
}
