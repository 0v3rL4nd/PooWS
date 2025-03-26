package poo.sistema;

import poo.util.Mat;

public class GaussDiagonale extends Gauss{
	
	public GaussDiagonale( double[][] a, double[] y ) {
		super(a,y);
	}
	
	protected void triangolarizza() {
		//triangolarizza sotto la diagonale principale
		super.triangolarizza();
		
		//ora triangolarizza sopra la diagonale principale
		int n=getN();
		for( int j=1; j<n; ++j ) {
			for( int i=j-1; i>=0; --i ) {
				if( !Mat.sufficientementeProssimi(a[i][j],0) ) {
					double coeff=a[i][j]/a[j][j];
					for( int k=j; k<=n; ++k )
						a[i][k]=a[i][k]-coeff*a[j][k];
				}
			}
		}
		
	}//triangolarizza
	
	protected double[] calcolaSoluzione() {
		int n=getN();
		double[] x=new double[n];
		for( int i=0; i<n; ++i )
			x[i]=a[i][n]/a[i][i];
		return x;
	}//calcolaSoluzione
	
}//GaussDiagonale
