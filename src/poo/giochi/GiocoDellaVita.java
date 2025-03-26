package poo.giochi;

public class GiocoDellaVita{
     private char[][] mappa, nuovaMappa;
     private int N, M;
     public GiocoDellaVita( int N, int M ){
          if( N<=0 || M<=0 ) throw new IllegalArgumentException("Dimensioni non positive.");
	  this.N=N; this.M=M;
          mappa=new char[N][M];
          nuovaMappa=new char[N][M];
          //riempimento casuale di mappa
          for( int i=0; i<N; ++i )
             for( int j=0; j<M; ++j )
                 if( Math.random()<0.5 ) mappa[i][j]='*';
                 else mappa[i][j]='.';
     }

     public void prossimaGenerazione(){
          for( int i=0; i<N; ++i )
             for( int j=0; j<M; ++j ){
                 int v=vicini(i,j);
                 if( mappa[i][j]=='*' )
                     nuovaMappa[i][j]=(v==2||v==3) ? '*' : '.';
                 else
                     nuovaMappa[i][j]=(v==3)? '*' : '.';
             }
           char[][] tmp=mappa;
           mappa=nuovaMappa;
           nuovaMappa=tmp;
     }//prossimaGenerazione

     private int vicini( int i, int j ){
		int conta=0;
		//se non ci si trova sulla prima riga, verifica a nord
		if( i>0 && mappa[i-1][j]=='*' ) conta++;
		//se non ci si trova sulla prima riga e nemmeno sull'ultima colonna, verifica a nord-est
		if( i>0 && j<M-1 && mappa[i-1][j+1]=='*' ) conta++;
		//usa ora la tua fantasia
		if( j<M-1 && mappa[i][j+1]=='*' ) conta++; //verifica a est
		if( i<N-1 && j<M-1 && mappa[i+1][j+1]=='*' ) conta++; //verifica a sud-est
		if( i<N-1 && mappa[i+1][j]=='*' ) conta++; //verifica a sud
		if( i<N-1 && j>0 && mappa[i+1][j-1]=='*' ) conta++; //verifica a sud-ovest
		if( j>0 && mappa[i][j-1]=='*' ) conta++; //verifica a ovest
		if( i>0 && j>0 && mappa[i-1][j-1]=='*' ) conta++; //verifica a nord-ovest
		return conta;
	 }//vicini

     public String toString(){
		String s="";
		for( int i=0; i<N; ++i ){
		   for( int j=0; j<M; ++j )
		       s=s+mappa[i][j];
		   s=s+"\n"; //aggiunge il fine linea
	    }
	    s=s+"\n";
	    return s;
     }//toString

     public static void main( String[] args ){//main di prova
		 System.out.println("Gioco della Vita di J. Conway");
		 GiocoDellaVita gdv=new GiocoDellaVita(8,12);
		 System.out.println("Situazione di partenza:");
		 System.out.println(gdv);
		 for( int k=0; k<10; ++k ){ //prime 10 generazioni
		    gdv.prossimaGenerazione();
		    System.out.println(""+(k+1)+"-esima generazione:");
		 	System.out.println(gdv);
		 }
     }//main

}//GiocoDellaVita