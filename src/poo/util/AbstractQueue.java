package poo.util;

import java.util.Iterator;

public abstract class AbstractQueue<T> implements Queue<T> {
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		for( T x: this )
			sb.append(x+", ");
		if( sb.length()>1 ) sb.setLength( sb.length()-2 );
		sb.append("]");
		return sb.toString();
	}//toString
	public boolean equals( Object x ) {
		if( !(x instanceof Queue) ) return false;
		if( x==this ) return true;
		Queue<T> s=(Queue<T>)x;
		if( size()!=s.size() ) return false;
		Iterator<T> i1=iterator(), i2=s.iterator();
		while( i1.hasNext() ) {
			if( !i1.next().equals(i2.next()) ) return false;
		}
		return true;
	}//equals
	public int hashCode() {
		int h=0;
		for( T e: this )
			h=h*31+e.hashCode();
		return h;
	}//hashCode
}
