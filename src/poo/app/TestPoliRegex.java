package poo.app;

import java.util.Scanner;
import java.util.StringTokenizer;
import poo.polinomi.Monomio;
import poo.polinomi.Polinomio;
import poo.polinomi.PolinomioLL;


public class TestPoliRegex {
	
	static Polinomio valueOf( String poli ) {
		String UMON="(\\d+|(\\d+)?[xX](\\^\\d+)?)";
		String POLINOMIO="\\-?"+UMON+"([\\+\\-]"+UMON+")*";
		
		if( !poli.matches(POLINOMIO) ) 
			throw new IllegalArgumentException(poli+" non e' un polinomio.");

		Polinomio p=new PolinomioLL();
		
		StringTokenizer st=new StringTokenizer(poli,"+-",true);
		
		while( st.hasMoreTokens() ) {
			String tk=st.nextToken();
			int sign=1;
			if( tk.matches("[\\+\\-]") ){ 
				if( tk.matches("\\-") ) sign=-1;
				tk=st.nextToken(); //in ogni caso avanza sul prossimo token
			}
			
			if( tk.matches("\\d+") ) {
				p.add( new Monomio(Integer.parseInt(tk)*sign,0));
			}
			else {
				// tk.matches("(\\d+)?[xX](\\^\\d+)?"))
				tk=tk.toUpperCase();
				
				int i=tk.indexOf('X');
				
				int coef=1, grado=1; //ipotesi pessimistica
				if( i>0 ) {
					coef=Integer.parseInt( tk.substring(0,i) );
				}
				
				int j=tk.indexOf('^');
				if( j>=0 ) {
					grado=Integer.parseInt(tk.substring(j+1));
				}
				p.add(new Monomio(coef*sign,grado));
			}
		}//while
		return p;
	}//valueOf
	
	public static void main( String[] args ) {
		System.out.println("Input e aritmetica di polinomi.");
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci 1 polinomio: ");
		String poli1=sc.nextLine();
		System.out.print("Fornisci 2 polinomio: ");
		String poli2=sc.nextLine();
		Polinomio p1=valueOf( poli1 );
		Polinomio p2=valueOf( poli2 );
		System.out.println("("+p1+")+("+p2+")="+p1.add(p2));
		System.out.println("("+p1+")*("+p2+")="+p1.mul(p2));
	}//main
	
}//TestPoliRegex


