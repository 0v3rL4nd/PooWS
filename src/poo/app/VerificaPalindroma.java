package poo.app;

import java.util.Scanner;
//import java.util.Stack;
import poo.util.Stack;
import poo.util.ArrayStack;
import poo.util.LinkedStack;


public class VerificaPalindroma {
	public static void main( String[] args ) {
		//string1$string2
		
		Scanner sca=new Scanner( System.in );
		System.out.print("Fornisci una linea string1$string2: ");
		String linea=sca.nextLine();
		
		String PALI="\\w+\\$\\w+";
		if( !linea.matches(PALI) ) throw new RuntimeException("Linea inattesa.");
		
		//Stack<Character> stack=new Stack<>();
		Stack<Character> stack=new ArrayStack<>(); //new LinkedStack<>();
		int i=0;
		while( linea.charAt(i)!='$' ) {
			stack.push( linea.charAt(i) );
			i++;
		}
		//skip $
		i++;
		while( !stack.isEmpty() && i<linea.length() ) {
			char x=stack.pop(); //elemento affiorante dello stack
			char y=linea.charAt(i);
			if( x!=y ) {
				break;
			}
			i++;
		}
		if( i<linea.length() || !stack.isEmpty() )
			System.out.println(linea+" non e' palindroma.");
		else
			System.out.println(linea+" e' palindroma.");
		sca.close();
	}
}
