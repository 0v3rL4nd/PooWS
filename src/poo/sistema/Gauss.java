package poo.sistema;
import poo.util.Mat;

public class Gauss extends Sistema{
	protected double[][] a;
	public Gauss( double[][] a, double[] y ) {
		super(a,y);
		this.a=new double[a.length][a.length+1];
		for( int i=0; i<a.length; ++i ) {
			for( int j=0; j<a.length; ++j )
				this.a[i][j]=a[i][j];
			this.a[i][a.length]=y[i];   
		}
	}
	public double[] risolvi() {
		triangolarizza();
		double[] x=calcolaSoluzione();
		return x;
	}//risolvi

	protected void triangolarizza() {
		int n=getN();
		for( int j=0; j<n; ++j ) {
			if( Mat.sufficientementeProssimi(a[j][j], 0) ) {
				//pivoting
				//cerca di colmare la lacuna di a[j][j]==0
				int p=j+1;
				for( ; p<n; ++p )
					if( !Mat.sufficientementeProssimi(a[p][j],0) )
						break;
				if( p==n ) throw new RuntimeException("Sistema singolare.");
				//scambia riga p con riga j
				double[] tmp=a[j];
				a[j]=a[p];
				a[p]=tmp;

			}
			for( int i=j+1; i<n; ++i ) {
				//azzera tutti i coefficienti di a sulle righe da j+1 sino ad n-1
				if( !Mat.sufficientementeProssimi(a[i][j],0) ) {
					double coeff=a[i][j]/a[j][j];
					//sottrai dalla riga i la riga j moltiplicata per coeff
					for( int k=j; k<=n; ++k )
						a[i][k]=a[i][k]-a[j][k]*coeff;
				}
			}
		}
	}//triangolarizza

	protected double[] calcolaSoluzione() {
		int n=getN();
		double[] x=new double[n];
		for( int i=n-1; i>=0; --i ) {
			double sm=a[i][n];
			for( int j=i+1; j<n; ++j )
				sm=sm-a[i][j]*x[j];
			x[i]=sm/a[i][i];
		}
		return x;
	}//calcolaSoluzione

	public String toString() {
		StringBuilder sb=new StringBuilder(500);
		int n=getN();
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<=n; ++j )
				sb.append( String.format("%10.2f", a[i][j]) );
			sb.append("\n");
		}
		return sb.toString();
	}//toString
}//Gauss
