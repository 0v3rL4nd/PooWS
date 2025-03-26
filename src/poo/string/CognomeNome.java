package poo.string;

import java.util.Scanner;

public class CognomeNome {
	public static void main( String[] args ) {
		Scanner sc=new Scanner( System.in );
		System.out.print("Scrivi Cognome e Nome di una persona: ");
		String linea=sc.nextLine();
		linea=linea.trim();
		
		int i=linea.indexOf(' ');
		
		String cognome=linea.substring(0,i);
		
		cognome=cognome.toUpperCase();
		
	    //int j=i;
	    //while( j<linea.length() && linea.charAt(j)==' ' ) ++j; //skip
	    
		//int j=linea.lastIndexOf(' ');
		
		int j=linea.lastIndexOf(' ', linea.length());
		
	    String nome=linea.substring(j+1,linea.length());
	    
	    nome=nome.toUpperCase();
	    
	    System.out.println(nome.charAt(0)+". "+cognome);
		sc.close();
		
	}//main
}//CognomeNome
