package poo.progettini;

import java.util.*;

public interface BigInt extends Comparable<BigInt>, Iterable<Integer> {
	//Assumo di memorizzare il mio numero al contrario per semplificare le operazioni
	default String value() {; //ritorna il valore del BigInt sottoforma di stringa di caratteri
		StringBuffer sb = new StringBuffer();
		for(Integer i:this)
			sb.append(i);
		return sb.reverse().toString();
	}
	
	default int length() { //ritorna il numero di cifre di questo BigInt
		int len=0;
		for(@SuppressWarnings("unused") Integer i: this)
			len++;
		return len;
	}
	
	BigInt factory( int x );
	
	default BigInt incr() {
		return this.add(factory(1));
	}
	
	default BigInt decr() {//eccezione se this � zero
		
		//Se intendo BigInt 0 come len=1
//		boolean flag=false;
//		int primaCifra=0;
//		for(Integer i:this) {
//			if(!flag) {
//				primaCifra = i;
//				flag = true;
//				continue;
//			}
//			flag = false;
//			break;
//		}
//		if(primaCifra == 0 && flag)//se ho una sola cifra ed � 0
//			throw new IllegalArgumentException();
//		
		
		//se assumo che bigint 0 abbia 0 come len
		if (this.length()==0) throw new IllegalArgumentException();
		return this.sub(factory(1));
	} 
	
	BigInt add( BigInt a );
//		BigInt ret = factory(0);
//		
//		Iterator<Integer> itThis = this.iterator();
//		Iterator<Integer> itA = a.iterator();
//		int n=1;
//		boolean riporto = false;
//		
//		while(itThis.hasNext() && itA.hasNext()) {
//			int n1 = itThis.next();
//			int n2 = itA.next();
//			
//			int r = n1+n2;
//			if(riporto) {
//				r++;
//				riporto = false;
//			}
//			
//			if(r>9) {
//				riporto = true;
//				r-=10;
//			}
//			ret.add(factory(r*n));
//			n*=10;//per arrivare alla cifra i-esima
//		}
//		
//		//a questo punto devo aggiungere gli ultimi (eventuali) riporti
//		while(itThis.hasNext()) {
//			int n1 = itThis.next();
//			if(riporto) {
//				n1++;
//				riporto = false;
//			}
//			
//			if (n1>9) {
//				n1-=10;
//				riporto = true;
//			}
//			ret.add(a);
//			n*=10;
//		}
//		return ret;
//	}
	
	BigInt sub( BigInt s );//ritorna un BigInt con la differenza tra this e d; atteso this>=d
	
	default BigInt mul( BigInt m ) {
		BigInt res = factory(0);
		BigInt temp = m;
		try {
			for(;;) {
				temp = temp.decr();
				res.add(this);
			}
		}catch(IllegalArgumentException e) {}
		return res;
	}
	
	default BigInt div( BigInt d ) {
		BigInt res = factory(0);
		BigInt temp = this;
		try {
			for(;;) {
				temp.sub(d);
				res.incr();
			}
		}catch(IllegalArgumentException e) {}
		return res;
	};//ritorna il quoziente della divisione intera tra this e d; atteso this>=d
	BigInt rem( BigInt d ); //ritorna il resto della divisione intera tra this e d; atteso this>=d
	BigInt pow( int exponent ); //calcola la potenza this^exponent
}
