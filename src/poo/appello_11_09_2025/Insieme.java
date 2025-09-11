package poo.appello_11_09_2025;

import java.util.Iterator;

public interface Insieme<T extends Comparable<? super T>> extends Iterable<T>{
    default int cardinalità(){
        Iterator it = this.iterator();
        int conta = 0;
        while(it.hasNext()){
            T y = (T) it.next();
            conta++;
        }
        return conta;
    }
    boolean eVuoto();
    void svuota();
    boolean contiene(T x);
    boolean aggiungi(T x);
    boolean rimuovi(T x);
    Insieme<T> unione(Insieme<T> i);
    Insieme<T> intersezione(Insieme<T> i);
    Insieme<T> differenza(Insieme<T> i);
    Insieme<T> differenzaSimmetrica(Insieme<T> i);
}
