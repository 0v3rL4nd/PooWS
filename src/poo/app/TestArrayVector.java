package poo.app;
import poo.util.Vector;

import java.util.Iterator;

import poo.util.ArrayVector;

public class TestArrayVector {
	public static void main( String[] args ) {
		Vector<String> vs=new ArrayVector<String>();
		String[] s= {"lupo","tana","abaco","zaino","java","dado"};
		for( int i=0; i<s.length; ++i ) {
			String x=s[i];
			int j=0;
			while( j<vs.size() ) {
				String y=vs.get(j);
				if( y.compareTo(x)>=0 ) {
					vs.add( j, x );
					break;
				}
				else j++;
			}
			if( j==vs.size() ) vs.add(x);
		}
		System.out.println(vs);
		
		Vector<Integer> vi=new ArrayVector<Integer>();
		for( int i=0; i<10; ++i ) {
			int x=10-i;
			int j=0;
			while( j<vi.size() ) {
				Integer y=vi.get(j); //unboxing automatico da Integer ad int
				if( y>=x ) { //anche y.compareTo(x)>=0 VA BENE
					vi.add( j, x ); //boxing automatico da int a Integer
					break;
				}
				else j++;
			}
			if( j==vi.size() ) vi.add( x );
		}
		System.out.println(vi);
		
		//esempio di uso dell'iteratore su vector
		for( Iterator<Integer> it=vi.iterator(); it.hasNext();  ) {
			int x=it.next();
			if( x==5 ) { it.remove(); break; }
		}
		System.out.println(vi);
		//esempio di uso del for-each
		for( int x: vi ) {
			System.out.println(x);
		}
		
	}//main
}//TestArrayVector
