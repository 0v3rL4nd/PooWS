package poo.util;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class ABR<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> fS, fD;
	}
	private Nodo<T> radice=null;
	
	@Override
	public int size() {
		return size(radice);
	}//size
	private int size( Nodo<T> radice ) {
		if( radice==null ) return 0;
		return 1+size(radice.fS)+size(radice.fD);
	}//size
	
	public void clear() { radice=null; }
	public boolean isEmpty() { return radice==null; }
	public boolean isFull() { return false; }
	
	@Override
	public boolean contains( T x ) {
		return contains( radice, x );
	}//contains
	private boolean contains( Nodo<T> radice, T x ) {
		if( radice==null ) return false;
		if( radice.info.equals(x) ) return true;
		if( radice.info.compareTo(x)>0 ) return contains(radice.fS,x);
		return contains(radice.fD,x);
	}//contains
	
	@Override
	public T get( T x ) {
		return get( radice, x );
	}//get 
	private T get( Nodo<T> radice, T x ) {
		if( radice==null ) return null;
		if( radice.info.equals(x) ) return radice.info;
		if( radice.info.compareTo(x)>0 ) return get(radice.fS,x);
		return get( radice.fD,x );
	}//get
	
	@Override
	public void add( T x ) {
		radice=add( radice,x );
		//addIte(x);
	}//add
	private Nodo<T> add( Nodo<T> radice, T x ){
		if( radice==null ) {
			Nodo<T> n=new Nodo<>(); 
			n.info=x; //fS e fD sono inizializzati da Java a null
			return n;
		}
		if( radice.info.compareTo(x)>0 ) radice.fS=add( radice.fS,x );
		else radice.fD=add( radice.fD,x );
		return radice;
	}//add
	
	private void addIte( T x ) {
		boolean direzione=false; //fittizia - ma true indice SX etc
		Nodo<T> padre=null, figlio=radice;
		while( figlio!=null ) {
			if( figlio.info.compareTo(x)>0 ) {
				direzione=true; 
				padre=figlio; figlio=figlio.fS;
			}
			else {
				direzione=false;
				padre=figlio; figlio=figlio.fD;
			}
		}
		Nodo<T> n=new Nodo<>();
		n.info=x; //null fS e fD by default
		if( radice==null ) radice=n;
		else {
			if( direzione ) padre.fS=n;
			else padre.fD=n;
		}
	}//addIte
	
	@Override
	public void remove( T x ) {
		radice=remove( radice, x );
	}//remove
	private Nodo<T> remove( Nodo<T> radice, T x ){
		if( radice==null ) return radice;
		if( radice.info.compareTo(x)>0 ) {
			radice.fS=remove(radice.fS,x);
			return radice;
		}
		if( radice.info.compareTo(x)<0 ) {
			radice.fD=remove( radice.fD, x );
			return radice;
		}
		//qui abbiamo trovato x in radice.info
		if( radice.fS==null && radice.fD==null ) {//radice e' un nodo foglia
			return null;			
		}
		if( radice.fS==null ) {//nodo con il solo figlio fD
			return radice.fD;
		}
		if( radice.fD==null ) {
			return radice.fS;
		}
		//radice ammette entrambi i figli
		//1 caso: la radice del sotto albero destro e' minimo del sotto albero destro
		if( radice.fD.fS==null ) {
			radice.info=radice.fD.info; //promozione
			radice.fD=radice.fD.fD;
			return radice;
		}
		//caso piu' generale
		Nodo<T> padre=radice.fD, figlio=radice.fD.fS;
		while( figlio.fS!=null ) {
			padre=figlio;
			figlio=figlio.fS;
		}
		radice.info=figlio.info; //promozione
		padre.fS=figlio.fD;
		return radice;
	}//remove
	
	public void inOrder( List<T> ls ) {
		inOrder( radice, ls );
	}//inOrder
	private void inOrder( Nodo<T> radice, List<T> ls ) {
		if( radice!=null ) {
			inOrder( radice.fS, ls );
			ls.add( radice.info ); //visita la radice
			inOrder( radice.fD, ls );
		}
	}//inOrder
	
	public void postOrder( List<T> ls ) {
		postOrder( radice, ls );
	}//postOrder
	private void postOrder( Nodo<T> radice, List<T> ls ) {
		if( radice!=null ) {
			postOrder( radice.fS, ls );
			postOrder( radice.fD, ls );
			ls.add( radice.info );
		}
	}//postOrder
	
	public void preOrder( List<T> ls ) {
		preOrder( radice, ls );
	}//preOrder
	private void preOrder( Nodo<T> radice, List<T> ls ) {
		if( radice!=null ) {
			ls.add( radice.info );
			preOrder( radice.fS, ls );
			preOrder( radice.fD, ls );
		}
	}//preOrder
	
	//depth first visit - o visita a ventaglio
	//breadth first visit - o visita a scandaglio
	
	public void visitaPerLivelli( List<T> ls ) {
		if( radice==null ) return;
		Queue<Nodo<T>> coda=new LinkedList<>();
		coda.offer(radice);
		while( !coda.isEmpty() ) {
			Nodo<T> n=coda.poll();
			//visitiamo n
			ls.add( n.info );
			//scheduliamo ora la visita dei figli di radice
			if( n.fS!=null ) coda.offer( n.fS );
			if( n.fD!=null ) coda.offer( n.fD );
		}
	}//visitaPerLivelli
	
	public boolean bilanciato() {
		return bilanciato(radice);
	}//bilanciato
	private boolean bilanciato( Nodo<T> radice ) {
		if( radice==null ) return true;
		int cardS=size(radice.fS);
		int cardD=size(radice.fD);
		if( Math.abs(cardS-cardD)>1 ) return false;
		return bilanciato(radice.fS) && bilanciato(radice.fD);
	}//bilanciato
	
	
	public void build( T[] a ) {
		radice=build(a,0,a.length-1);
	}
	private Nodo<T> build( T[] a, int inf, int sup ){
		return null; //TODO
	}
	
	@Override
	public Iterator<T> iterator(){
		return new ABRIterator();
	}//iterator
	
	private class ABRIterator implements Iterator<T>{
		private List<T> lista=new LinkedList<>();
		private Iterator<T> it;
		private T cor=null;
		public ABRIterator() {
			inOrder( lista );
			it=lista.iterator();
		}
		public boolean hasNext() {
			return it.hasNext();
		}
		public T next() {
			cor=it.next();
			return cor;
		}
		public void remove() {
			if( cor==null ) throw new IllegalStateException();
			ABR.this.remove( cor ); //toglie cor dall'albero
			lista.remove(cor); //e dalla lista
			cor=null;
		}
	}//ABRIterator
	
	public static void main( String[] args ) {
		ABR<Integer> a=new ABR<>();
		a.add(12); a.add(3); a.add(25); a.add(-1);
		a.add(17); a.add(7); a.add(-2);
		System.out.println("size="+a.size());
		System.out.println("Contenuto dell'albero");
		System.out.println(a);
		a.remove(12);
		System.out.println(a);
		System.out.println("bilanciato di a? "+a.bilanciato());
		List<Integer> lista=new LinkedList<>();
		a.visitaPerLivelli(lista);
		System.out.println("Visita per Livelli: ");
		for( int x: lista )
			System.out.print(x+" ");
		System.out.println();
	}//main
	
}//ABR
