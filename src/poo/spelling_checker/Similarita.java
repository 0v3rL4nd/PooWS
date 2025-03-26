package poo.spelling_checker;

import java.util.*;

public class Similarita {
	
	static int min( int...a ) {
		int min=a[0];
		for( int i=1; i<a.length; ++i )
			if( a[i]<min ) min=a[i];
		return min;
	}
	
	static int lev( String a, String b ) {
		if( a.length()==0 ) return b.length();
		if( b.length()==0 ) return a.length();
		if( a.charAt(0)==b.charAt(0) ) return lev( a.substring(1),b.substring(1) );
		return 1+min( lev( a.substring(1),b ),
				      lev( a,b.substring(1) ),
				      lev( a.substring(1),b.substring(1) ));
	}//lev
	
	static double dJaccard( String a, String b ) {
		a=a.toUpperCase(); b=b.toUpperCase();
		Set<Character> s=new HashSet<>();
		Map<Character,Integer> ma=new HashMap<>();
		for( int i=0; i<a.length(); ++i ) {
			char c=a.charAt(i); s.add(c);
			if( !ma.containsKey(c) ) ma.put(c,0);
			ma.put(c,ma.get(c)+1);
		}
		Map<Character,Integer> mb=new HashMap<>();
		for( int i=0; i<b.length(); ++i ) {
			char c=b.charAt(i); s.add(c);
			if( !mb.containsKey(c) ) mb.put(c,0);
			mb.put(c,mb.get(c)+1);
		}
		int ic=0; //intersection cardinality
		for( char c: s ) {
			if( ma.containsKey(c) && mb.containsKey(c) ) ic=ic+ma.get(c)+mb.get(c);
		}
		int uc=a.length()+b.length(); //union cardinality
		return 1-(double)ic/uc;
	}//dJaccard
	
	public static void main( String[] args ) {
		String a="kitten", b="sitting";
		System.out.println("Levenshtein("+a+","+b+")="+lev(a,b));
		System.out.println("dJaccard("+a+","+b+")="+dJaccard(a,b));
		a="casa"; b="home";
		System.out.println("Levenshtein("+a+","+b+")="+lev(a,b));
		System.out.println("dJaccard("+a+","+b+")="+dJaccard(a,b));
		a="algorithm"; b="algroithm";
		System.out.println("Levenshtein("+a+","+b+")="+lev(a,b));
		System.out.println("dJaccard("+a+","+b+")="+dJaccard(a,b));
		a="algorithm"; b="algorithm";
		System.out.println("Levenshtein("+a+","+b+")="+lev(a,b));
		System.out.println("dJaccard("+a+","+b+")="+dJaccard(a,b));
		a="package"; b="pachage";
		System.out.println("Levenshtein("+a+","+b+")="+lev(a,b));
		System.out.println("dJaccard("+a+","+b+")="+dJaccard(a,b));
	}
}//Similarita
