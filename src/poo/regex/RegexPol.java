package poo.regex;

import java.util.Scanner;

public class RegexPol {
	public static void main( String[] args ) {
		System.out.println("Input e riconoscimento di un polinomio.");
		
		String SIGN="[\\-\\+]";
		String COEF="\\d+";
		String LETT="[xX](\\^([2-9]|1\\d)\\d*)?";
		
		String UMON="("+COEF+"|"+LETT+"|"+COEF+LETT+")"; 
		
		String POL="\\-?"+UMON+"("+SIGN+UMON+")*";
		
		Scanner sc=new Scanner( System.in );
		String s=null;
		try {
			for(;;){
				System.out.print("Polinomio INVIO [solo INVIO per terminare] >> ");
				s=sc.nextLine();
				if( s.length()==0 ) break;
				if( s.matches(POL) ){
					System.out.println(s+" è un polinomio.");
				}
				else
					System.out.println("Input "+s+" non riconosciuto.");
			}
		}finally {
			System.out.println("Bye!");
			sc.close();
		}
	}
}
