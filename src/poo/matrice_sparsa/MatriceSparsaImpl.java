package poo.matrice_sparsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MatriceSparsaImpl extends MatriceSparsaAstratta{
	private Map<Integer,Map<Integer,Double>> ms=new HashMap<>();
	private int N, M; //nr righe e nr colonne
	private String REALE="\\-?(\\d+|(\\d+)?\\.\\d+)([eE][\\-\\+]?\\d{1,3})?[DdFf]?";
	private String RIGA="i=\\d+\\s+j=\\d+\\s+v="+REALE;
	public MatriceSparsaImpl( int N, int M, String nomeFile ) throws IOException {
		if( N<0 || M<0 ) throw new IllegalArgumentException();
		this.N=N; this.M=M;
		BufferedReader br=new BufferedReader( new FileReader(nomeFile) );
		try {
			for(;;) {
				String linea=br.readLine();
				if( linea==null ) break;
				if( !linea.matches(RIGA) )
					throw new RuntimeException("File scorretto.");
				StringTokenizer st=new StringTokenizer(linea,"i=j=v= ");
				int i=Integer.parseInt(st.nextToken());
				int j=Integer.parseInt(st.nextToken());
				double v=Double.parseDouble(st.nextToken());
				if( v==0 ) continue;
				if( !ms.containsKey(i) ) ms.put(i, new HashMap<>() );
				Map<Integer,Double> riga=ms.get(i);
				riga.put(j,v);
			}
		}finally {
			br.close();
		}
	}
	public MatriceSparsaImpl( int N, int M ) {
		this.N=N; this.M=M;
	}
	public double get( int i, int j ) {
		if( i<0 || i>N || j<0 || j>M ) throw new IllegalArgumentException();
		if( !ms.containsKey(i) ) return 0;
		Map<Integer,Double> riga=ms.get(i);
		if( !riga.containsKey(j) ) return 0;
		return riga.get(j);
	}//get
	public void set( int i, int j, double v ) {
		if( i<0 || i>N || j<0 || j>M ) throw new IllegalArgumentException();
		if( v==0 ) {
			//se esiste <i,j> non zero, va rimosso
			if( !ms.containsKey(i) ) return;
			if( !ms.get(i).containsKey(j) ) return;
			ms.get(i).remove(j); return;
		}
		if( !ms.containsKey(i) ) ms.put(i, new HashMap<>() );
		ms.get(i).put(j, v);
	}//set
	public MatriceSparsa factory( int nr, int nc ) {
		return new MatriceSparsaImpl(nr,nc);
	}//factory
	
	public int nr_righe() { return N; }
	public int nr_colonne() { return M; }
	
	public static void main( String[] args ) throws IOException{
		Scanner sc=new Scanner( System.in );
		System.out.print("Nome file dati: ");
		String nomeFile=sc.nextLine();
		MatriceSparsa ms=new MatriceSparsaImpl(3,3,nomeFile);
		System.out.println("Matrice sparsa:");
		System.out.println(ms);
		double d=ms.determinanteG();
		System.out.println("Determinante secondo Gauss="+d);
		MatriceSparsa a=new MatriceSparsaImpl(3,3,nomeFile);
		MatriceSparsa somma=ms.add(a);
		System.out.println("matrice somma con sè stessa:");
		System.out.println(somma);
		MatriceSparsa prod=ms.mul(a);
		System.out.println("matrice prodotto con sè stessa:");
		System.out.println(prod);		
		sc.close();
	}//main
}//MatriceSparsaImpl
