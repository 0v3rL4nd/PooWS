package poo.tracce.appello1907024;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public class AbstractBag<T> implements Bag<T>{

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            T elem = it.next();
            sb.append(elem).append("(").append(multiplicity(elem)).append(")");
            if (it.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int cardinality() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int multiplicity(T x) {
        return 0;
    }

    @Override
    public void add(T x) {

    }

    @Override
    public void add(T x, int multiplicity) {

    }

    @Override
    public void addAll(Bag<T> b) {

    }

    @Override
    public boolean remove(T x) {
        return false;
    }

    @Override
    public boolean removeAll(T x) {
        return false;
    }

    @Override
    public Bag<T> factory() {
        return null;
    }

    @Override
    public Bag<T> factory(Comparator<T> c) {
        return null;
    }

    @Override
    public boolean included(Bag<T> b) {
        return false;
    }

    @Override
    public Bag<T> union(Bag<T> b) {
        return null;
    }

    @Override
    public Bag<T> difference(Bag<T> b) {
        return null;
    }

    @Override
    public Bag<T> intersection(Bag<T> b) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
