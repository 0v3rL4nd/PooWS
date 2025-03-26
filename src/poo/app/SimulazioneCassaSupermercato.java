package poo.app;

//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Scanner;

import poo.util.Queue;
import poo.util.BoundedBuffer;
import poo.util.LinkedQueue;

public class SimulazioneCassaSupermercato {
	public static void main( String[] args ) {
		System.out.println("Simulazione della fila di attesa di una cassa di supermercato");
		//aA nickname  INVIO
		//pP INVIO
		//STOP INVIO
		Scanner sc=new Scanner(System.in);
		//Queue<String> coda=new LinkedList<>();
		Queue<String> coda=new BoundedBuffer(4); //new LinkedQueue<>();
		String COMANDO="([Ss][Tt][Oo][Pp]|[aA]\\s+\\w+|[pP])";
		for(;;) {
			System.out.print(">> ");
			String linea=sc.nextLine();
			if( !linea.matches(COMANDO) ) {
				System.out.println("Linea comando scorretta.");
			}
			else if( linea.equalsIgnoreCase("STOP") ){
				System.out.println("Residuo: "+coda);
				break;
			}
			else {
				char com=Character.toUpperCase(linea.charAt(0));
				switch( com ) {
				case 'A': int i=linea.lastIndexOf(' ');
				          String nick=linea.substring(i+1);
				          try {
				        	  coda.offer(nick);
				        	  System.out.println(coda);
				          }catch( Exception e ) {
				        	  System.out.println("Coda piena!");
				        	  System.out.println(coda);
				          }
				          break;
				default : 
						  if( coda.isEmpty() )
							  System.out.println("Coda vuota!");
						  else {
							  String tizio=coda.poll();
							  System.out.println("Esce "+tizio+"!");
							  System.out.println(coda);
						  }
				}//switch
			}
		}
	}
}
