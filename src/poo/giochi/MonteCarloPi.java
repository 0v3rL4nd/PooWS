package poo.giochi;
import poo.geometria.Punto;

public class MonteCarloPi{

  public static void main( String[] args ){
	final int N=1000000;
	int M=0;
    Punto origine=new Punto();
    for( int i=0; i<N; ++i ){
        double x=Math.random()*2-1;
	    double y=Math.random()*2-1;
        Punto p=new Punto(x,y);
        double d=origine.distanza(p);
        if( d<=1 ) M++;
     }
     double pi=(double)(4*M)/N;
     System.out.printf( "PI greco approssimato=%1.5f%n", pi );
     System.out.printf( "Math.PI=%1.5f%n", Math.PI );
  }//main

}//MonteCarloPi