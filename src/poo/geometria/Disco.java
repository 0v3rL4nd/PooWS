package poo.geometria;

public class Disco extends Punto{
	private double raggio;
	public Disco( double raggio ) {
		super();
		if( raggio<=0 ) throw new IllegalArgumentException("Raggio non positivo.");
		this.raggio=raggio;
	}
	public Disco( double x, double y, double r ) {
		super(x,y);
		if( r<=0 ) throw new IllegalArgumentException("Raggio non positivo.");
		raggio=r;
	}
	public Disco( Punto p, double r ) {
		super(p);
		if( r<=0 ) throw new IllegalArgumentException("Raggio non positivo.");
		raggio=r;
	}
	public Disco( Disco d ) {
		super( d.getX(), d.getY() );
		this.raggio=d.raggio;
	}
	
	public double getRaggio() {
		return raggio;
	}//getRaggio
	
	public double perimetro() {
		return 2*Math.PI*raggio;
	}//perimetro
	
	public double area() {
		return raggio*raggio*Math.PI;
	}//area
	
	public String toString() {
		return "Disco di raggio="+raggio+" e centro "+super.toString();
	}//toString
	
	public static void main( String... args ) {
		Disco d1=new Disco(4);
		System.out.println(d1+" perimetro="+d1.perimetro()+" area="+d1.area());
		d1.muovi(5, 6);
		System.out.println(d1+" perimetro="+d1.perimetro()+" area="+d1.area());
		
		Punto p=new Punto(-3,5);
		System.out.println(p);
		
		double dist=d1.distanza(p);
		System.out.println("d="+dist);
		
		p=d1;
		System.out.println(p);
		
		double a=((Disco)p).area();
		System.out.println("a="+a);
		
		if( p instanceof Punto ) System.out.println("p e' un Punto");
		if( p instanceof Disco ) System.out.println("p e' un Disco...");
		if( p instanceof Object ) System.out.println("p e' un Object...");
		
		Object[] ao=new Object[4];
		ao[0]=new Punto(5,7);
		ao[1]=new String("Java is ... usa la tua fantasia...");
		ao[2]=new Disco(2,5,12);
		ao[3]=new poo.date.Data();
		
		for( int i=0; i<ao.length; ++i )
			System.out.println(ao[i]);
		
	}//main
}//Disco
