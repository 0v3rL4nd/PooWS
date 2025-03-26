package poo.app;

public class Demo{

    public static void main( String... args ){
         System.out.println("Esempio di metodo vararg");
         int i=m( 5, -3, 4, 12, 5 );
         System.out.println("i="+i);
         i=m( -4 );
         System.out.println("i="+i);
    }//main

    public static int m( double x, double...y ){
        for( int i=0; i<y.length; ++i ){
   	    if( x==y[i] ) return i;
        }
        return -1;
    }//m

}//Demo