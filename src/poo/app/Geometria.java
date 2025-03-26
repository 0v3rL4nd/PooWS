package poo.app;
import poo.geometria.Punto;
import poo.geometria.Triangolo;

public class Geometria{
   public static void main( String[] args ){
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

	Triangolo t=new Triangolo( p1, p2, p3 );
	System.out.println(t+" perimetro="+t.perimetro()+" area="+t.area()+" tipo="+t.tipo() );
   }//main
}//Geometria