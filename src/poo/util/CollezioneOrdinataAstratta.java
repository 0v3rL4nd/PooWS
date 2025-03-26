package poo.util;

import java.util.Iterator;

public abstract class CollezioneOrdinataAstratta<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			sb.append(it.next());
			if( it.hasNext() ) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}//
	public boolean equals( Object x ) {
		//TODO come esercizio
		return false;
	}
	public int hashCode() {
		//TODO come esercizio
		return 0;
	}
}
