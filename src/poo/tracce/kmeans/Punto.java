package poo.tracce.kmeans;

import java.util.Objects;

public class Punto {

    double x, y;

    public Punto() {
        this.x = 0;
        this.y = 0;
    }

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distanza(Punto p){
        return  Math.sqrt( Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2) );
    }

    public Punto puntoMedio(Punto p){
        return new Punto( (this.x + p.x) / 2, (this.y + p.y) / 2 );
    }

    @Override
    public boolean equals(Object o) {
        if(this == o ) return true;
        if( !(o instanceof Punto )) return false;
        Punto p = (Punto) o;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() {
        int h = 31;
        return (int) (x * h + h * y);
    }

    @Override
    public String toString() {
        return "< " + x + ", " + y + " >";
    }
}


