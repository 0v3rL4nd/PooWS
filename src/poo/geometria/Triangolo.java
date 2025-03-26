package poo.geometria;

public class Triangolo{
    private Punto p1, p2, p3;
    private double a, b, c;

    public enum TIPO{ EQUILATERO, ISOSCELE, SCALENO };

    public Triangolo( Punto p1, Punto p2, Punto p3 ){
	a=p1.distanza(p2);
        b=p2.distanza(p3);
	c=p3.distanza(p1);
	//controlliamo che esista il triangolo
        if( a>=b+c || b>=a+c || c>=a+b )
           throw new IllegalArgumentException("Triangolo inesistente.");
	this.p1=new Punto(p1);
        this.p2=new Punto(p2);
	this.p3=new Punto(p3);
    }
    public Triangolo( Triangolo t ){
	this.a=t.a; this.b=t.b; this.c=t.c;
        this.p1=new Punto( t.p1 );
	this.p2=new Punto( t.p2 );
	this.p3=new Punto( t.p3 );
    }

    public double getA(){ return a; }
    public double getB(){ return b; }
    public double getC(){ return c; }

    public Punto[] vertici(){
        Punto[] v={ new Punto(p1), new Punto(p2), new Punto(p3) };
        return v;
    }//vertici

    public double perimetro(){
	return a+b+c;
    }//perimetro

    public double area(){
	double s=(a+b+c)/2;
	return Math.sqrt( s*(s-a)*(s-b)*(s-c) );
    }//area

    public TIPO tipo(){
		if( a==b && a==c ) return TIPO.EQUILATERO;
		if( a==b || a==c || b==c ) return TIPO.ISOSCELE;
        return TIPO.SCALENO;
    }//tipo

    public String toString(){
	return "Triangolo("+p1+","+p2+","+p3+")";
    }//toString

    public static void main( String[] args ){//main di test
        Punto p=new Punto( 2, 5 );
	Punto q=new Punto( 3, -4 );
	Punto r=new Punto( 0,-7 );
        Triangolo t=new Triangolo( p, q, r );
        double perimetro=t.perimetro();
	double area=t.area();
	System.out.println("Perimetro="+perimetro+" area="+area );
	System.out.println("Tipo del triangolo="+t.tipo());
    }//main


}//Triangolo