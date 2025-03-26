package poo.path;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import poo.backtracking.Backtracking;

public class Path2 extends Backtracking<Integer,Integer>{
	private int a[][];
	private int n; //variabile di istanza introdotta per convenienza
	private LinkedList<Integer> path=new LinkedList<>(); //alias della lista dei ps
	private int numSol=0;
	
	public Path2( int[][] a ) {
		this.a=new int[a.length][];
		for( int i=0; i<a.length; ++i ) {
			if( a[i].length!=a.length ) throw new IllegalArgumentException();
			this.a[i]=java.util.Arrays.copyOf(a[i], a[i].length);
		}
		this.n=a.length;
	}
	
	protected boolean esisteSoluzione( Integer p ) {
		int i=path.getLast()/n, j=path.getLast()%n;
		return i==n-1 && j==n-1;
	}//esisteSoluzione

	protected boolean ultimaSoluzione( Integer p ) {
		return numSol==5;
	}//ultimaSoluzione
	
	protected  List<Integer> puntiDiScelta(){
		path.addLast(0);
		return path;
	}//puntiDiScelta
	
	protected Collection<Integer> scelte( Integer p ){
		List<Integer> s=new LinkedList<>();
		int k=a[p/n][p%n], i=p/n, j=p%n;
		if( i+k<n ) s.add( (i+k)*n+j );
		if( i-k>=0 ) s.add( (i-k)*n+j );
		if( j+k<n ) s.add( i*n+j+k );
		if( j-k>=0 ) s.add( i*n+j-k );
		return s;
	}//scelte
	
	protected boolean assegnabile( Integer p, Integer s ) {
		int p_i=p/n, p_j=p%n, s_i=s/n, s_j=s%n;
		if( p_i==p_j && s_i==s_j ) return false; //movimento diagonale non consentito
		for( Integer x: path )
			if( x.equals(s) ) return false; //scelta già incontrata
		return true;
	}//assegnabile
	
	protected void assegna( Integer p, Integer s ) {
		path.addLast( s );
	}//assegna
	
	protected void deassegna( Integer p, Integer s ) {
		path.removeLast();
	}//deassegna
	
	protected void scriviSoluzione( Integer p ) {
		numSol++;
		System.out.print(""+numSol+" ");
		path.forEach( r->System.out.print("<"+(r/n)+","+(r%n)+">") );
		System.out.println();
	}//scriviSoluzione
	
	public static void main( String...args ) {
		int[][] a={
			{3,0,2,3},
			{2,4,2,1},
			{2,2,1,3},
			{1,1,1,2}
		};

		int[][] b={
			{ 7, 1, 3, 5, 3, 6, 1, 1, 7, 5 },
			{ 2, 3, 6, 1, 1, 6, 6, 6, 1, 2 },
			{ 6, 1, 7, 2, 1, 4, 7, 6, 6, 2 },
			{ 6, 6, 7, 1, 3, 3, 5, 1, 3, 4 },
			{ 5, 5, 6, 1, 5, 4, 6, 1, 7, 4 },
			{ 3, 5, 5, 2, 7, 5, 3, 4, 3, 6 },
			{ 4, 1, 4, 3, 6, 4, 5, 3, 2, 6 },
			{ 4, 4, 1, 7, 4, 3, 3, 1, 4, 2 },
			{ 4, 4, 5, 1, 5, 2, 3, 5, 3, 5 },
			{ 3, 6, 3, 5, 2, 2, 6, 4, 2, 1 }
		};

		new Path2( a ).risolvi();
		System.out.println("Fine soluzioni.");
	}
	
}//Path

