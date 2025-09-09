package poo.tracce.Bag;

import java.util.Comparator;

public interface Bag<T> extends Iterable <T>{
    int cardinality (); // usa la tua fantasia
    void clear (); // usa la tua fantasia
    int multiplicity (T x); // ritorna il numero di volte in cui x è presente in this, di seguito abbreviata m (x)
    void add (T x) ; // aggiunge una singola occorrenza di x
    void add (T x, int multiplicity); // usa la tua fantasia
    void addAll(Bag<T> b) ; // usa la tua fantasia
    boolean remove(T x) ; // rimuove una singola occorrenza di x e restituisce true se this è modificato
    boolean removeAll(T x); // rimuove tutte le occorrenza di x in this
    Bag<T> factory (); // usa la tua fantasia
    Bag <T> factory (Comparator<T> c) ; // crea un factor da mantenere ordinato secondo c
    boolean included (Bag<T> b); //ritorna true se this è contenuto in b

    Bag<T> union (Bag <T> b) ; // ritorna il bag contenente gli x di this e di b, e con max(this.m(x), b.m(x) )Prendo il massimo della molteplicità


    Bag<T> difference(Bag<T> b) ; // ritorno un nuovo bag this-b per ogni elemento x appartenente a this :     max(this.m(x)-b.m(x), 0)


    Bag<T> intersection(Bag <T> b); // ritorna un bag con gli elementi comuni x e con min (this.m(x), b.m(x))

}// Bag