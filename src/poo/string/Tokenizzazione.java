package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Tokenizzazione {
	public static void main( String[] args ) {
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci una linea di testo: ");
		String linea=sc.nextLine();
		StringTokenizer st=new StringTokenizer(linea,",.;:!? ");
		
		
		System.out.println("Tokenizzazione mediante StringTokenizer");
		while( st.hasMoreTokens() ) {
			String word=st.nextToken();
			System.out.println(word);
		}
		System.out.println();
		sc.close();
		
		System.out.println("Tokenizzazione mediante Scanner");
		Scanner sl=new Scanner(linea);
		sl.useDelimiter("\\W+");
		while( sl.hasNext() ) {
			String tk=sl.next();
			System.out.println(tk);
		}
		System.out.println();
		sl.close();
		
		System.out.println("Tokenizzazione mediante split");
        String[] parole=linea.split("\\W+"); //delimitatori sono tutti i caratteri non di word

		for( int i=0; i<parole.length; ++i ) {
			System.out.println(parole[i]);
		}
		System.out.println("bye.");
	}
}
