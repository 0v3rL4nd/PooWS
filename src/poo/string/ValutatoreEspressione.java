package poo.string;

import java.util.StringTokenizer;

public class ValutatoreEspressione {
	private String expr;
	
	public int valuta( String expr ) throws OperatoreScorretto{
		//verificare con una regex che expr sia accettabile
		String EXPR="([\\+\\-\\*/\\(\\)]\\d+)+";
		//TODO
		this.expr=expr;
		StringTokenizer st=new StringTokenizer( expr, "+-*/()", true );
		int risultato=valutaEspressione( st );
		return risultato;
	}//valuta
	
	private int valutaOperando( StringTokenizer st ) throws OperatoreScorretto{
		String token=st.nextToken();
		if( token.charAt(0)=='(' ) return valutaEspressione(st);
		else return Integer.parseInt(token);
	}//valutaOperando
	
	private int valutaEspressione( StringTokenizer st ) throws OperatoreScorretto{
		int ris=valutaOperando(st);
		while( st.hasMoreTokens() ) {
			char op=st.nextToken().charAt(0);
			if( op==')' ) return ris;
			int opnd=valutaOperando(st);
			switch( op ) {
				case '+': ris=ris+opnd; break;
				case '-': ris=ris-opnd; break;
				case '*': ris=ris*opnd; break;
				case '/': ris=ris/opnd; break;
				default: throw new OperatoreScorretto(""+op);
			}//switch
		}
		return ris;
	}//valutaEspressione
	
	public String toString() {
		return expr;
	}//toString
}//ValutatoreEspressione
