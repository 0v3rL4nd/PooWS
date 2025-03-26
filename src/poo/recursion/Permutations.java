package poo.recursion;

public class Permutations {
	private int[] a; //contiene gli n oggetti da permutare
	private int[] b; //su b si costruiscono le permutazioni
	private int numSol;
	
	//gli indici di b sono i punti di scelta
	//gli elementi di a sono le scelte ammissibili
	//vincoli: in un punto di scelta i noin si puo' assegnare
	//un elemento di a gia' assegnato in una posizione precedente ad i
	
	public Permutations( int[] a ) {
		//verificare che gli n oggetti in a sono distinti
		this.a=java.util.Arrays.copyOf(a, a.length);
		this.b=new int[a.length];
	}
	
	private boolean assegnabile( int i, int elem ) {
		for( int j=0; j<i; ++j )
			if( b[j]==elem ) return false;
		return true;
	}//assegnabile
	
	private void assegna( int i, int elem ) {
		b[i]=elem;
	}//assegna
	
	private void deassegna( int i, int elem ) {
	}//deassegna
	
	private void scriviSoluzione() {
		numSol++;
		System.out.println(numSol+": "+java.util.Arrays.toString(b));
	}//scriviSoluzione
	
	private void estendiPermutazione( int i ) {
		for( int j=0; j<a.length; ++j ) {//a[j] e' una scelta
			if( assegnabile(i,a[j]) ) {
				assegna(i,a[j]);
				if( i==b.length-1 ) scriviSoluzione();
				else estendiPermutazione(i+1);
				deassegna(i,a[j]);
			}
		}
	}//estendiPermutazione
	
	public void risolvi() {
		estendiPermutazione(0);
	}//permuta
	
	public static void main( String[] args ) {
		int[] v= {1,2,3,4,5};
		Permutations p=new Permutations(v);
		p.risolvi();
	}//main
}
