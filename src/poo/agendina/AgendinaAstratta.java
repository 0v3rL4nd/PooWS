package poo.agendina;

import java.util.Iterator;

import poo.util.Vector;

public abstract class AgendinaAstratta implements Agendina{
	
	public String toString() {
		StringBuilder sb=new StringBuilder(300);
		for( Nominativo n: this ) {
			sb.append(n+"\n");
		}
		return sb.toString();
	}//toString
	
	public boolean equals( Object o ) {
		if( !(o instanceof Agendina) ) return false;
		if( o==this ) return true;
		Agendina v=(Agendina)o;
		if( this.size()!=v.size() ) return false;
		Iterator<Nominativo> i1=this.iterator(), i2=v.iterator();
		while( i1.hasNext() ) {
			Nominativo e1=i1.next();
			Nominativo e2=i2.next();
			if( !e1.equals(e2) ) return false;
		}
		return true;
	}//equals
	
	public int hashCode() {
		final int M=83;
		int h=0;
		for( Nominativo e: this )
			h=h*M+e.hashCode();
		return h;
	}//hashCode
	
}//AgendinaAstratta
