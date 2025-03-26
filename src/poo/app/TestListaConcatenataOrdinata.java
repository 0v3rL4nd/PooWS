package poo.app;

import java.util.Iterator;

import poo.util.CollezioneOrdinata;
import poo.util.ListaConcatenataOrdinata;
import poo.util.ListaDoppiaOrdinata;

public class TestListaConcatenataOrdinata {
	public static void main( String[] args ) {
		CollezioneOrdinata<String> lista=new ListaDoppiaOrdinata<>();
		lista.add("lupo"); lista.add("abaco"); lista.add("dado");
		lista.add("casa"); lista.add("zaino"); lista.add("fuoco");
		System.out.println(lista+" size="+lista.size());
		System.out.println("casa e' presente? "+lista.contains("casa"));
		lista.remove("zaino");
		System.out.println(lista+" size="+lista.size());
		
		for( String s: lista )
			System.out.println(s);
		System.out.println();
		
		Iterator<String> it=lista.iterator();
		while( it.hasNext() ) {
			it.next();
			it.remove();
		}
		System.out.println(lista+" size="+lista.size());
	}//main
}//TestListaConcatenataOrdinata
