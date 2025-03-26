package poo.recursion;

public class NQueens {
	
	private int n;
	private boolean board[][];
	private int numSol=0;
	
	public NQueens( int n ) {
		if( n<=3 ) throw new IllegalArgumentException(n+" atteso >3");
		this.n=n;
		board=new boolean[n][n]; //tutti false inizialmente
	}
	
	private boolean assegnabile( int r, int c ) {
		//e' assegnabile una regina sulla posizione <r,c>?
		for( int i=r-1; i>=0; --i )
			if( board[i][c] ) return false; //c'e' attacco a nord
		for( int i=r-1, j=c-1; i>=0 && j>=0; --i, --j )
			if( board[i][j] ) return false; //c'e' attacco a nord-ovest
		for( int i=r-1, j=c+1; i>=0 && j<=n-1; --i, ++j )
			if( board[i][j] ) return false; //c'e' attacco a nord-est
		return true;
	}//assegnabile
	
	private void assegna( int r, int c ) {
		board[r][c]=true;
	}//assegna
	
	private void deassegna( int r, int c ) {
		board[r][c]=false;
	}//deassegna
	
	private void scriviSoluzione() {
		numSol++;
		System.out.print(numSol+": ");
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<n; ++j ) {
				if( board[i][j] ) {
					System.out.print("<"+i+","+j+">");
					break;
				}
			}
		}
		System.out.println();
	}//scriviSoluzione
	
	private void collocaRegina( int r ) {
		for( int c=0; c<n; ++c ) {
			if( assegnabile(r,c) ) {
				assegna(r,c);
				if( r==n-1 ) scriviSoluzione();
				else collocaRegina(r+1);
				deassegna(r,c);
			}
		}
	}//collocaRegina
	
	public void risolvi() {
		collocaRegina(0);
	}//risolvi
	
	public static void main( String[] args ) {
		NQueens nq=new NQueens(8);
		nq.risolvi();
	}//main
	
}//NQueens
