package poo.app;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestListIterator{
	public static void main( String[] args ){
		List<String> l=new LinkedList<>();
		for( String x: args ){//stringhe dalla riga di comando
		      boolean flag=false; //true ad inserimento effettuato
		      ListIterator<String> lit=l.listIterator(); 
		      while( lit.hasNext() && !flag ){
		            String s=lit.next();
                         if( s.compareTo(x)>=0 ){
                                lit.previous(); lit.add(x); flag=true;
                         }
                      }//while
                      if( !flag ) lit.add(x);
         }//for
         System.out.println(l);
	}//main
}//TestListIterator

