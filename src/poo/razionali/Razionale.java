package poo.razionali;
import poo.util.Mat;

public class Razionale implements Comparable<Razionale>{
    public final int NUM, DEN;
    private static int contatore=0;

    public Razionale( final int nu, final int de ){
        int n=nu, d=de;
	if( d==0 ) throw new IllegalArgumentException("Denominatore nullo.");
        if( d<0 ) { n=(-1)*n; d=(-1)*d; }
        if( n!=0 ){
	    int MCD=Mat.mcd( Math.abs(n), d );
	    n=n/MCD; d=d/MCD;
	}
        NUM=n; DEN=d;
        contatore++;
    }
    public Razionale( Razionale r ){
        this.NUM=r.NUM; this.DEN=r.DEN;
        contatore++;
    }

    public static int razionaliEsistenti(){
	return contatore;
    }//razionaliEsistenti

    //poichè un razionale è immutabile, le operazioni
    //aritmetiche non possono modificare this ma devono
    //creare e restituire un nuovo oggetto Razionale
    //con il risultato dell'operazione

    public Razionale add( Razionale r ){
	//addiziona this con r
	int MCM=Mat.mcm( this.DEN, r.DEN );
	int numeratore=(MCM/this.DEN)*this.NUM+(MCM/r.DEN)*r.NUM;
	int denominatore=MCM;
	return new Razionale( numeratore, denominatore );
    }//add

    public Razionale sub( Razionale r ){
	//sottrae r da this
	//riconduce la sotrazione ad addizione, cambiando prima il segno del secondo operando
	return this.add( r.mul(-1) );
    }//add

    public Razionale mul( Razionale r ){
	//moltiplica this per r
	int numeratore=this.NUM*r.NUM;
	int denominatore=this.DEN*r.DEN;
 	return new Razionale( numeratore, denominatore );
    }//mul

    public Razionale mul( int scalare ){
	//moltiplica this per il numero scalare
	return new Razionale( this.NUM*scalare, this.DEN );
    }//mul

    public Razionale div( Razionale r ){
	//divide this per r
	return this.mul( new Razionale(r.DEN,r.NUM) );
    }//mul

    @Override
    public String toString(){
	String s="";
        if( NUM==0 || DEN==1 ) s=s+NUM;
	else s=s+NUM+"/"+DEN;
	return s;
    }//toString

    protected void finalize(){
        contatore--;
    }//finalize
    
    @Override
    public boolean equals( Object x ) {
    	if( !(x instanceof Razionale) ) return false;
    	if( x==this ) return true;
    	Razionale r=(Razionale)x;
    	return this.NUM==r.NUM && this.DEN==r.DEN;
    }//equals
    
    @Override
    public int hashCode() {
    	final int M=83;
    	int h=0;
    	h=h*M+NUM;
    	h=h*M+DEN;
    	return h;
    }//hashCode
    
    public int compareTo( Razionale r ) {
    	//riduzione allo stesso denominatore
    	int mcm=Mat.mcm(this.NUM, r.DEN);
    	//numeratori equivalenti al comune denominatore
    	int n1=(mcm/this.DEN)*this.NUM;
    	int n2=(mcm/r.DEN)*NUM;
    	//confronto dei numeratori
    	if( n1<n2 ) return -1;
    	if( n1==n2 ) return 0;
    	return 1;
    }//compareTo

    public static void main( String[] args ){
    	Razionale r1=new Razionale(4,14);
    	Razionale r2=new Razionale(40,50);
    	System.out.println(r1+"+"+r2+"="+r1.add(r2));
    	System.out.println(r1+"*"+r2+"="+r1.mul(r2));
    	System.out.println(r1+"*"+"6="+r1.mul(6));
    	System.out.println(r1+"-"+r2+"="+r1.sub(r2));
    	System.out.println(r1+":"+r2+"="+r1.div(r2));
    	System.out.println("NUM di "+r1+"="+r1.NUM);
        int c=Razionale.razionaliEsistenti();
        System.out.println("razionali esistenti="+c);
        
        System.out.println(r1.equals(r2)+" r1.hashCode()="+r1.hashCode()+" r2.hashCode()="+r2.hashCode());
    }//main

}//Razionale