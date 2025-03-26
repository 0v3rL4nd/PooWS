package poo.polinomi;

public record Monomio(int coeff, int grado) implements Comparable<Monomio>{
	public Monomio{
		if( grado<0 ) throw new IllegalArgumentException("Grado negativo.");
	}
	
	public int compareTo( Monomio m ) {
		if( this.grado>m.grado ) return -1;
		if( this.equals(m) ) return 0;
		return 1;
	}//compareTo
	
	public boolean equals( Object x ) {
		if( !(x instanceof Monomio) ) return false;
		if( x==this ) return true;
		Monomio m=(Monomio)x;
		return this.grado==m.grado;
	}//equals
	public int hashCode() {
		return grado;
	}//hashCode
	
	public Monomio add( Monomio m ) {
		if( !this.equals(m) ) throw new RuntimeException("Monomi non simili.");
		return new Monomio(coeff+m.coeff, grado);
	}//add
	
	public Monomio mul( Monomio m ) {
		return new Monomio( coeff*m.coeff, grado+m.grado );
	}//mul
	
	public Monomio mul( int scalare ) {
		return new Monomio( coeff*scalare, grado );
	}//mul
	
	public String toString() {
		String s="";
		if( coeff<0 && Math.abs(coeff)!=0 ) s=s+"-";
		if( Math.abs(coeff)!=1 ) s=s+Math.abs(coeff);
		else if( grado==0 ) s=s+"1";
		if( coeff!=0 ) {
			if( grado!=0 ) s=s+"x";
			if( grado>1 ) s=s+"^"+grado;
		}
		return s;
	}//toString
	
	public static void main( String[] args ) {
		Monomio m1=new Monomio(-2,5);
		System.out.println(m1);
		Monomio m2=new Monomio(4,5);
		System.out.println("("+m1+")"+"+("+m2+")="+m1.add(m2));
		System.out.println("("+m1+")"+"*("+m2+")="+m1.mul(m2));
		System.out.println("("+m1+")"+"+("+m2.mul(-1)+")="+m1.add(m2.mul(-1)));
	}//main
	
}//Monomio
