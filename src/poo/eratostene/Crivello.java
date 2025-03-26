package poo.eratostene;

import java.util.Iterator;

public interface Crivello extends Iterable<Integer>{
	default int size() {
		int c=0;
		for( Iterator<Integer> it=iterator(); it.hasNext(); it.next(), c++ );
		return c;
	}
	void filtra();
}
