package poo.sistema;

public abstract class Sistema {
	private int n;
	public Sistema( double[][] a, double[] y ) {
		for( int i=0; i<a.length; ++i )
			if( a[i].length!=a.length ) 
				throw new IllegalArgumentException("Matrice non quadrata.");
		this.n=a.length;
		if( y.length!=n ) 
			throw new IllegalArgumentException("Sistema incompatibile per i termini noti.");
	}
	public int getN() { return n; }
	public abstract double[] risolvi();
}//Sistema
