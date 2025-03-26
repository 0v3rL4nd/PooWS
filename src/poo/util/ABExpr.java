package poo.util;

import java.util.List;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class ABExpr {
	private static class Nodo{
		Nodo fS, fD;
	}
	private static class NodoOperando extends Nodo{
		int val;
		public String toString() { return ""+val; }
	}
	private static class NodoOperatore extends Nodo{
		char op;
		public String toString() { return ""+op; }
	}
	private Nodo radice=null;
	
	public ABExpr( String expr ) {
		//verifica expr con regex
		String EXPR="(\\(|\\d+)([\\+\\-\\*/\\(\\)]|\\d+)+";
		if( !expr.matches(EXPR) ) 
			throw new IllegalArgumentException(expr+" e' malformata.");
		//costruisci l'albero
		StringTokenizer st=new StringTokenizer(expr,"+-*/()",true);
		radice=buildEspressione(st);
	}
	
	private Nodo buildOperando( StringTokenizer st ) {
		String tk=st.nextToken();
		if( tk.charAt(0)=='(' ) return buildEspressione(st);
		NodoOperando nopn=new NodoOperando();
		nopn.val=Integer.parseInt(tk);
		nopn.fS=null; nopn.fD=null;
		return nopn;
	}//buildOperando
	
	private Nodo buildEspressione( StringTokenizer st ) {
		Nodo radice=buildOperando(st);
		while( st.hasMoreTokens() ) {
			char op=st.nextToken().charAt(0);
			if( op==')' ) return radice;
			Nodo nopnd=buildOperando(st);
			NodoOperatore nopt=new NodoOperatore();
			nopt.op=op;
			nopt.fS=radice; nopt.fD=nopnd;
			radice=nopt;
		}
		return radice;
	}//buildEspressione
	
	public int valuta() {
		if( radice==null ) throw new IllegalArgumentException();
		return valuta( radice );
	}//valuta
	private int valuta( Nodo r ) {
		if( r instanceof NodoOperando )
			return ((NodoOperando)r).val;
		int v1=valuta( r.fS );
		int v2=valuta( r.fD );
		char op=((NodoOperatore)r).op;
		switch( op ) {
		case '+': return v1+v2;
		case '-': return v1-v2;
		case '*': return v1*v2;
		case '/': return v1/v2;
		default: throw new RuntimeException(op+" operatore inatteso.");
		}
	}//valuta
	
	public void inOrder( List<String> ls ) {
		inOrder( radice, ls );
	}//inOrder
	private void inOrder( Nodo radice, List<String> ls ) {
		if( radice!=null ) {
			if( radice instanceof NodoOperatore ) ls.add("(");
			inOrder( radice.fS, ls );
			ls.add( radice.toString() );
			inOrder( radice.fD, ls );
			if( radice instanceof NodoOperatore ) ls.add(")");
		}
	}//inOrder
	
	public void preOrder( List<String> ls ) {
		preOrder( radice, ls );
	}//preOrder
	private void preOrder( Nodo radice, List<String> ls ) {
		if( radice!=null ) {
			ls.add( radice.toString() );
			preOrder( radice.fS, ls );
			preOrder( radice.fD, ls );
		}
	}//preOrder
	
	public void postOrder( List<String> ls ) {
		postOrder( radice, ls );
	}//postOrder
	private void postOrder( Nodo radice, List<String> ls ) {
		if( radice!=null ) {
			postOrder( radice.fS, ls );
			postOrder( radice.fD, ls );
			ls.add( radice.toString()+" " );
		}
	}//postOrder
	
	public String toString() {
		List<String> l=new LinkedList<>();
		inOrder(l);
		String s="";
		for( String x: l )
			s=s+x+" ";
		return s;
	}//

	public String toString( List<String> ls ) {
		String s="";
		for( String x: ls )
			s=s+x+" ";
		return s;		
	}//toString
	
	public static void main( String[] args ) {
		String expr="(5*(6+7))-(18/5)+4";
		ABExpr ae=new ABExpr(expr);
		System.out.println(ae);
		System.out.println(expr+"="+ae.valuta());
		List<String> ls=new LinkedList<>();
		ae.postOrder(ls);
		System.out.println( "RPN="+ae.toString(ls) );
		
		ls.clear();
		ae.preOrder(ls);
		System.out.println( "PRE="+ae.toString(ls) );
		ls.clear();
		ae.inOrder(ls);
		System.out.println( "SIM="+ae.toString(ls) );
	}//main
	
}//ABExpr
