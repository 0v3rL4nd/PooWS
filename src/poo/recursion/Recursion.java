package poo.recursion;

public final class Recursion {
	private Recursion() {}
	
	public static boolean palindroma( String s ) {//anna
		if( s.length()<=1 ) return true;
		if( s.charAt(0)!=s.charAt(s.length()-1) ) return false;
		return palindroma( s.substring(1,s.length()-1) );  //tail recursion
	}//palindroma
	
	public static boolean palindromaIte( String s ) {
		int i=0, j=s.length()-1;
		while( i<j ) {
			if( s.charAt(i)!=s.charAt(j) ) return false;
			i++; j--;
		}
		return true;
	}//palindromaIte
	
	public static void scriviInverso( int n ) {
		if( n<0 ) throw new IllegalArgumentException();
		inverti(n);
	}
	
	private static void inverti(int n) {
		if( n<10 ) System.out.println(n);
		else {
			System.out.print(n%10);
			inverti(n/10);
		}
	}
	
	public static void scriviInversoIte( int n ) { //TODO come esercizio
		//se n=173 occorre scrivere su output 371, con un algoritmo iterativo
	}
	
	public static int max( int[] v ) {
		if( v==null || v.length==0 ) throw new IllegalArgumentException();
		return max( v, 0, v.length-1 );
	}//max
	
	private static int max( int[]v, int inf, int sup ) { //usare divide-et-impera
		if( inf==sup ) return v[inf];
		int med=(inf+sup)/2;
		int m1=max( v, inf, med ); //m1 e' il max del primo segmento
		int m2=max( v, med+1, sup ); //m2 � il max del secondo segmento
		return m1>m2?m1:m2;
	}//max
	
	public static int somma( int[]v ) {
		if( v==null || v.length==0 ) throw new IllegalArgumentException();
		return somma( v, 0, v.length-1 );
	}
	
	public static int somma( int[] v, int inf, int sup ) {//usare divide-et-impera
		return 0; //TODO per esercizio
	}
	
	public static <T extends Comparable<? super T>> int ricercaBinaria( T[]v, T x ) {
		return ricercaBinaria(v,x,0,v.length-1);
	}//ricercaBinaria
	
	private static <T extends Comparable<? super T>> int ricercaBinaria( T[] v, T x, int inf, int sup ) {
		return 0; //TODO per esercizio usando la tecnica divide-et-impera
	}//ricercaBinaria
	
	public static void main( String[] args ) {
		String s="radar";
		if( palindroma(s) ) System.out.println(s+" e' palindroma.");
		else System.out.println(s+" non e' palindroma.");
		
		int a[]= {2,5,1,-1,4,7};
		System.out.println("max("+java.util.Arrays.toString(a)+")="+max(a));
		
		scriviInverso(1734);
	}
}//Recursion
