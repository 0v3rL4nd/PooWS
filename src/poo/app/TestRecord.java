package poo.app;

record Esempio(int x, int y) {
	Esempio { //costruttore compatto, che completa il costruttore canonico
		if( x<=0 || y<=0 ) throw new IllegalArgumentException("x e y non positivi");
	}
}

public class TestRecord {
	public static void main( String[] args ) {
		Esempio e1=new Esempio(5,7);
		
		System.out.println(e1);
		
		int z=e1.x()+2*e1.y();
		
		Esempio e2=new Esempio(5,7);
		
		System.out.println(e1+"=="+e2+"? "+e1.equals(e2));
		
		Esempio e3=new Esempio(-2,8);
		System.out.println(e3);
	}
}
