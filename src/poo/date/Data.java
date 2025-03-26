package poo.date;

import java.util.GregorianCalendar;
public class Data implements Comparable<Data>{
   public final int G, M, A;
   public Data(){
      GregorianCalendar gc=new GregorianCalendar();
      G=gc.get( GregorianCalendar.DAY_OF_MONTH );
      M=gc.get( GregorianCalendar.MONTH )+1;
      A=gc.get( GregorianCalendar.YEAR );
   }
   public Data( int g, int m, int a ){
      if( a<0 || g<1 || g>durata(m,a) || m<1 || m>12 )
          throw new IllegalArgumentException("Data illegale.");
      G=g; M=m; A=a;
   }
   public Data( Data d ){ G=d.G; M=d.M; A=d.A; }

   public static boolean bisestile( int a ){ //helper method
      if( a<0 ) throw new IllegalArgumentException("Anno illegale.");
      if( a%4!=0 ) return false;
      if( a%100==0 && a%400!=0 ) return false;
      return true;
   }//bisestile

   public static int durata( int m, int a ){
      if( a<0 || m<1 || m>12 ) throw new IllegalArgumentException("Dati errati.");
      int d=0;
      switch( m ){
          case 1: case 3: case 5: case 7: case 8: case 10: case 12: d=31; break;
          case 2: d=bisestile(a)? 29 : 28; break;
          default: d=30;
      }
      return d;
   }//durata

   public Data giornoDopo(){
      int d=durata(M,A);
      int G1=0, M1=0, A1=0;
      if( G==d ){
         G1=1;
         if( M==12 ){ M1=1; A1=A+1; }
         else { M1=M+1; A1=A; }
      }
      else{ G1=G+1; M1=M; A1=A; }
      return new Data( G1, M1, A1 );
   }//giornoDopo

   public Data giornoPrima(){
      return null; //TODO
   }//giornoPrima

   public int distanza( Data d ){
      return 0; //TODO
   }//distanza

   public String toString(){
      return ""+G+"/"+M+"/"+A;
   }//toString
   
   public boolean equals( Object o ) {
	   return false; //TODO
   }//equals
   
   public int hashCode() {
	   return 0; //TODO
   }//hashCode

   public int compareTo( Data d ) {
	   if( A<d.A || A==d.A && M<d.M || A==d.A && M==d.M && G<d.G ) return -1;
	   if( this.equals(d) ) return 0;
	   return 1;
   }//compareTo
   
   public static void main( String[] args ){
      Data oggi=new Data();
      System.out.println("Oggi e' il: "+oggi.G+"/"+oggi.M+"/"+oggi.A);
      Data domani=oggi.giornoDopo();
      System.out.println("domani e' il: "+domani);
      Data data=new Data( 28, 2, 2000 );
      System.out.println("data ="+data+" anno bisestile?"+Data.bisestile(data.A) );
      System.out.println("data successiva a "+data+" e' "+data.giornoDopo() );
   }//main
}//Data