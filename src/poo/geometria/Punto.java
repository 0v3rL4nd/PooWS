package poo.geometria;

public class Punto{
	private double x, y;
	public Punto(){
	    this(0,0);
        }//costruttore di default
	public Punto( double x, double y ){//costruttore "normale"
	    this.x=x; this.y=y;
	}
	public Punto( Punto p ){ x=p.x; y=p.y; }//costruttore di copia
	public double getX(){ return x; }
	public double getY(){ return y; }
	public void muovi( double x, double y ){
	    this.x=x; this.y=y;
	}//muovi
	public double distanza( Punto p ){
            return Math.sqrt( (p.x-this.x)*(p.x-this.x)+(p.y-this.y)*(p.y-this.y) );
	}//distanza
	public String toString(){
	    return "Punto("+x+","+y+")";
	}//toString
   public static void main( String[] args ){//main di prova di Punto
	Punto p1=new Punto();
	Punto p2=new Punto(-3,4);
	double d=p1.distanza(p2);
	System.out.println("distanza("+p1+","+p2+")="+d);
	p1.muovi( 2,5 );
	System.out.println(p1);

	d=p1.distanza(p2);

	System.out.println("distanza("+p1+","+p2+")="+d);
	String s=String.format("%1.2f",d);
	System.out.println("distanza("+p1+","+p2+")="+s);

	Punto p3=new Punto(p1); //NO aliasing tra p1 e p3
	p3.muovi(-5,-6);
   }//main
}//Punto