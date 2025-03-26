package poo.util;

public final class Matrici {
	
	private Matrici() {}
	
	public static double[][] add( double[][] a, double[][] b ){
		if( a.length != b.length ) 
			throw new IllegalArgumentException("Numero di righe non uguali");
		if( a[0].length != b[0].length ) 
			throw new IllegalArgumentException("Prime righe non uguali in dimensione");
		for( int i=1; i<a.length; ++i )
			if( a[i].length != b[i].length || a[i].length!=a[0].length )
				throw new IllegalArgumentException("Righe incompatibili");
		double[][] somma=new double[a.length][a[0].length];
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a[i].length; ++j )
				somma[i][j]=a[i][j]+b[i][j];
		return somma;
	}//add
	
	public static double[][] sub( double[][] a, double[][] b ){
		return null; //TODO
	}//sub

	public static double[][] mul( double[][] a, double[][] b ){
		//TODO: test compatibilità di a e b rispetto a mul
		double[][] prodotto=new double[a.length][b[0].length]; //An,m X Bm,p = Cn,p
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<b[0].length; ++j ){
				//fai prodotto scalare tra a[i][.] e b[.][j]
				double ps=0;
				for( int k=0; k<b.length; ++k )
				    ps=ps+a[i][k]*b[k][j];
				prodotto[i][j]=ps;
			}
		return prodotto;
	}//mul

	public static double[][] trasposta( double[][] m ){
		return null; //TODO
	}//trasposta

	public static double[][] minore( double[][] a, int i, int j ){
		return null; //TODO
	}//minore
	
	public static double determinante( double[][] m ) {
		for( int i=0; i<m.length; ++i )
			if( m[i].length!=m.length ) 
				throw new IllegalArgumentException("Matrice non quadrata.");
		//calcolo del determinante con la riduzione di Gauss
		
		//copia la matrice m in a
		double[][] a=new double[m.length][m.length];
		for( int i=0; i<a.length; ++i )
			System.arraycopy(m[i], 0, a[i], 0, a.length);

		int n=a.length;
		int contaScambi=0;
		for( int j=0; j<n; ++j ) {
			if( Mat.sufficientementeProssimi(a[j][j], 0) ) {
				//pivoting
				//cerca di colmare la lacuna di a[j][j]==0
				int p=j+1;
				for( ; p<n; ++p )
					if( !Mat.sufficientementeProssimi(a[p][j],0) )
						break;
				if( p==n ) return 0;
				//scambia riga p con riga j
				double[] tmp=a[j];
				a[j]=a[p];
				a[p]=tmp;
				contaScambi++;
			}
			for( int i=j+1; i<n; ++i ) {
				//azzera tutti i coefficienti di a sulle righe da j+1 sino ad n-1
				if( !Mat.sufficientementeProssimi(a[i][j],0) ) {
					double coeff=a[i][j]/a[j][j];
					//sottrai dalla riga i la riga j moltiplicata per coeff
					for( int k=j; k<n; ++k )
						a[i][k]=a[i][k]-a[j][k]*coeff;
				}
			}
		}//for( int j...
		double d=1;
		for( int i=0; i<n; ++i )
			d=d*a[i][i];
		d=(contaScambi%2!=0) ? (-1)*d : d;
		return d;
	}//determinante
	
	public static void main( String[] args ) {
		double[][] a= {
				{1,2,3},
				{4,5,6},
				{-1,3,2}
		};
		double d=determinante(a);
		System.out.println("det="+d);
	}//main
	
}//Matrici
