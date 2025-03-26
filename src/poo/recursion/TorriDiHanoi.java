package poo.recursion;

import java.util.Stack;

public class TorriDiHanoi {
	enum Pin{ SX, CL, DX }
	private int n;
	public TorriDiHanoi( int n ) {
		if( n<=1 ) throw new IllegalArgumentException();
		this.n=n;
	}
	
	public void risolvi() {
		muoviIte(n,Pin.SX,Pin.CL,Pin.DX);
	}//risolvi
	
	private void sposta1disco( Pin da, Pin a ) { 
		System.out.println("Sposta 1 disco da "+da+" a "+a);
	}//sposta1disco
	
	private void muovi( int n, Pin sorg, Pin aus, Pin dest ) {
		if( n==1 ) sposta1disco( sorg, dest );
		else {
			muovi( n-1, sorg,dest,aus );
			sposta1disco(sorg,dest);
			muovi( n-1, aus,sorg,dest );
		}
	}//muovi
	
	private void muoviIte( int n, Pin sorg, Pin aus, Pin dest ){
		record AD(int n, Pin sorg, Pin aus, Pin dest){} //Area Dati
		Stack<AD> stack=new Stack<>(); 
		stack.push( new AD( n, sorg, aus, dest ) ); //simula la 1 chiamata a muovi
		while( stack.size()!=0 ){
		   AD ad=stack.pop(); //attivazione corrente del metodo
		   if( ad.n()==1 ) sposta1disco( ad.sorg(), ad.dest() );
		   else{
		       stack.push( new AD(ad.n()-1, ad.aus(), ad.sorg(), ad.dest()) );
		       stack.push( new AD(1, ad.sorg(), ad.aus(), ad.dest()) );
		       stack.push( new AD(ad.n()-1, ad.sorg(), ad.dest(), ad.aus()) );
		   }
		}
		
	}//muoviIte
	/*
	 L'idea e' di fare la mimica dello stack delle aree dati del metodo ricorsivo
	 e di schedulare su uno stack esplicito le aree dati delle chiamate ricorsive
	 avendo cura di riportare le push in senso inverso a come compaiono le
	 chiamate ricorsive nell'algoritmo originale.
	 Importante: eventuali altre azioni intermedie tra chiamate ricorsive
	 vanno anch'esse schedulate sullo stack, altrimenti c'e' il rischio che
	 possano essere eseguite prima del tempo (un errore concettuale).
	 */

	public static void main( String[] args ) {
		TorriDiHanoi th=new TorriDiHanoi(5);
		th.risolvi();
	}//main
	
}//TorriDiHanoi
