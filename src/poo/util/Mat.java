package poo.util;

public final class Mat{

    private static final double EPS=1.0E-8;

    private Mat(){}

    public static int mcd( int x, int y ){
	if( y==0 ) return x;
        return mcd(y, x%y);
    }//mcd

    public static int mcm( int x, int y ){
        return (x*y)/mcd(x,y);
    }//mcm

    public static boolean sufficientementeProssimi( double x1, double x2 ){
        return Math.abs(x1-x2)<EPS;
    }//sufficientementeProssimi

}//Mat