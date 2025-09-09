package poo.tracce.Bag;

import java.util.*;

public class BagImpl<T> implements Bag<T> {
    List<T> bag = new ArrayList<>();

    public int cardinality() {
        int counter = 0;
        for(T elem: bag){
            counter+=multiplicity(elem);
        }
        return counter;
    }

    @Override
    public void clear() {
        for(T elem: bag){
            bag.remove(elem);
        }

    }

    @Override
    public int multiplicity(T x) {
        int multiplicity = 0;
        for(T elem: this){
            if(this.equals(x)){
                multiplicity++;
            }
        }
        return multiplicity;
    }

    @Override
    public void add(T x) {
        bag.add(x);
    }

    @Override
    public void add(T x, int multiplicity) {
        for(int i = 0; i< multiplicity; i++){
            bag.add(x);
        }
    }

    @Override
    public void addAll(Bag<T> b) {
        for(T elem: b){
            bag.add(elem);
        }

    }

    @Override
    public boolean remove(T x) {
        return bag.remove(x); // Metodo più semplice e corretto
    }

    @Override
    public boolean removeAll(T x) {
        boolean removed = false;
        Iterator<T> it = bag.iterator();
        while(it.hasNext()) {
            T elem = it.next();
            if(elem.equals(x)) {
                it.remove();
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public Bag<T> factory() {
        return new BagImpl<T>();
    }

    @Override
    public Bag<T> factory(Comparator<T> c) {
        // Per questa implementazione base, ignoriamo il comparatore
        // In un'implementazione avanzata, potremmo usare una TreeMap per l'ordinamento
        return new BagImpl<T>();
    }

    @Override
    public boolean included(Bag<T> b) {
        // Questo bag è incluso in b se ogni elemento di questo bag
        // ha molteplicità <= alla molteplicità in b
        Set<T> elementiUnici = new HashSet<>(bag);
        for(T elem : elementiUnici) {
            if(this.multiplicity(elem) > b.multiplicity(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Bag<T> union(Bag<T> b) {
        Bag<T> union = factory();

        // Aggiungiamo tutti gli elementi di questo bag
        for(T elem : this) {
            union.add(elem);
        }

        // Aggiungiamo tutti gli elementi dell'altro bag
        for(T elem : b) {
            union.add(elem);
        }

        return union;
    }

    @Override
    public Bag<T> difference(Bag<T> b) {
        Bag<T> difference = factory();

        // Per ogni elemento unico in questo bag
        Set<T> elementiUnici = new HashSet<>(bag);
        for(T elem : elementiUnici) {
            int molteplicita = this.multiplicity(elem) - b.multiplicity(elem);
            if(molteplicita > 0) {
                difference.add(elem, molteplicita);
            }
        }

        return difference;
    }

    @Override
    public Bag<T> intersection(Bag<T> b) {
        Bag<T> intersection = factory();

        // Per ogni elemento unico in questo bag
        Set<T> elementiUnici = new HashSet<>(bag);
        for(T elem : elementiUnici) {
            int molteplicita = Math.min(this.multiplicity(elem), b.multiplicity(elem));
            if(molteplicita > 0) {
                intersection.add(elem, molteplicita);
            }
        }

        return intersection;
    }

    @Override
    public Iterator<T> iterator() {
        return bag.iterator();
    }
}

