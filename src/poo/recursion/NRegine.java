package poo.recursion;

public class NRegine {
	private int n, numSol=0;
	private boolean board[][];
	public NRegine( int n ) {
		if( n<=3 ) throw new IllegalArgumentException();
		this.n=n;
		board=new boolean[n][n];
	}
	private void collocaRegina( int r ) {
		for( int c=0; c<n; ++c ) {
			if( assegnabile(r,c) ) {
				assegna(r,c);
				if( r==n-1 ) scriviSoluzione();
				else collocaRegina( r+1 );
				deassegna(r,c);
			}
		}
	}//collocaRegina
	
	private boolean assegnabile( int r, int c ) {
		//verifica a nord
		for( int i=r-1; i>=0; --i )
			if( board[i][c] ) return false;
		//verifica a nord-est
		for( int i=r-1,j=c+1; i>=0 && j<n; --i,++j )
			if( board[i][j] ) return false;
		//verifica a nord-ovest
		for( int i=r-1,j=c-1; i>=0 && j>=0; --i,--j )
			if( board[i][j] ) return false;
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
		System.out.print("Soluzione #"+numSol+": ");
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<n; ++j ) 
				if( board[i][j] ) {
					System.out.print("<"+i+","+j+">");
					break;
				}		
		}
		System.out.println();
	}//scriviSoluzione
	
	public void risolvi() {
		collocaRegina(0);
	}//risolvi
	
	public static void main( String[] args ) {
		NRegine nr=new NRegine(8);
		System.out.println("Inizio soluzioni");
		nr.risolvi();
		System.out.println("Fine soluzioni");
	}//main
	
}//NRegine
