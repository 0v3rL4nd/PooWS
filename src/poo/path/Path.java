package poo.path;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import poo.backtracking.Backtracking;

/*
class Pair{
	int i, j;
	public Pair( int i, int j ) {
		this.i=i; this.j=j;
	}
	public String toString() {
		return"<"+i+","+j+">";
	}
}//Pair
*/
record Pair( int i, int j ) {
	public String toString() {
		return"<"+i+","+j+">";
	}
};

public class Path extends Backtracking<Pair,Pair>{
	private int a[][];
	private LinkedList<Pair> path=new LinkedList<>(); //copia dei ps
	private int numSol=0;
	
	public Path( int[][] a ) {
		this.a=new int[a.length][];
		for( int i=0; i<a.length; ++i ) {
			if( a[i].length!=a.length ) 
				throw new IllegalArgumentException();
			this.a[i]=java.util.Arrays.copyOf(a[i], a[i].length);
		}
	}
	
	protected boolean esisteSoluzione( Pair p ) {
		return path.getLast().i()==a.length-1 && 
			   path.getLast().j()==a.length-1;
	}//esisteSoluzione

	protected boolean ultimaSoluzione( Pair p ) {
		return numSol==100;
	}//ultimaSoluzione
	
	protected  List<Pair> puntiDiScelta(){
		path.addLast( new Pair(0,0) );
		return path;
	}//puntiDiScelta
	
	protected Collection<Pair> scelte( Pair p ){
		List<Pair> s=new LinkedList<>();
		int k=a[p.i()][p.j()];
		if( p.i()+k<a.length )
			s.add( new Pair(p.i()+k,p.j()) );
		if( p.i()-k>=0 )
			s.add( new Pair(p.i()-k,p.j()) );
		if( p.j()+k<a.length )
			s.add( new Pair(p.i(),p.j()+k) );
		if( p.j()-k>=0 )
			s.add( new Pair(p.i(),p.j()-k) );
		return s;
	}//scelte
	
	protected boolean assegnabile( Pair p, Pair s ) {
		if( p.i()==p.j() && s.i()==s.j() ) 
			return false; //movimento diagonale non consentito
		for( Pair x: path )
			if( x.equals(s) ) return false; //scelta già incontrata
		return true;
	}//assegnabile
	
	protected void assegna( Pair p, Pair s ) {
		path.addLast( new Pair(s.i(),s.j()) );
	}//assegna
	
	protected void deassegna( Pair p, Pair s ) {
		path.removeLast();
	}//deassegna
	
	protected void scriviSoluzione( Pair p ) {
		numSol++;
		System.out.println(""+numSol+" "+path);
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

		new Path( a ).risolvi();
		System.out.println("Fine soluzioni.");
	}
	
}//Path

