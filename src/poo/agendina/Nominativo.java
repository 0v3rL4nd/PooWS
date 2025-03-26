package poo.agendina;

import java.io.Serializable;

public record Nominativo(String cognome, String nome, String prefisso, String telefono) 
	implements Comparable<Nominativo>,Serializable{

	private static final long serialVersionUID = 0L;
	
	public int compareTo( Nominativo n ) {
		if( this.cognome.compareTo(n.cognome)<0 ||
			this.cognome.equals(n.cognome) && this.nome.compareTo(n.nome)<0 
		   ) return -1;
		if( this.equals(n) ) return 0;  
		return 1;
	}//compareTo
	
	public boolean equals( Object x ) {
		if( !(x instanceof Nominativo) ) return false;
		if( x==this ) return true;
		Nominativo n=(Nominativo)x;
		return this.cognome.equals(n.cognome) && this.nome.equals(n.nome);
	}//equals
	
	public int hashCode() {
		final int M=83;
		int h=cognome.hashCode();
		h=h*M+nome.hashCode();
		return h;
	}//hashCode
	
	public String toString() {
		return cognome+" "+nome+" "+prefisso+"-"+telefono;
	}//toString
	
	public static void main( String[] args ) {
		Nominativo n1=new Nominativo("russo","pallino","0984","474536");
		Nominativo n2=new Nominativo("russo","ermanno","02","1234567");
		System.out.println(n1);
		if( n1.compareTo(n2)<0 )
			System.out.println(n1+" precede "+n2);
		else
			System.out.println(n2+" precede "+n1);
	}//main
	
}//Nominativo
