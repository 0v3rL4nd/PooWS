package poo.tracce.SetECrivello;

import java.util.*;

public class SetLL<T> implements Set<T> {

    LinkedList<T> list;

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean contains(T x) {
        for(T elem: list){
            if(elem.equals(x)) return true;
        }
        return true;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(T x) {
        list.add(x);
    }

    @Override
    public void remove(T x) {
        Iterator<T> it = iterator();;
        while(it.hasNext()){
            T elem = it.next();
            if(elem.equals(x)){
                list.remove(x);
            }
        }
    }

    @Override
    public Set<T> factory() {
        return new SetLL<T>();
    }

    @Override
    public Set<T> union(Set<T> s) {
        Set<T> result = factory();
        // Aggiungi tutti gli elementi di questo set
        for (T element : this) {
            result.add(element);
        }
        // Aggiungi tutti gli elementi dell'altro set
        for (T element : s) {
            result.add(element);
        }
        return result;
    }

    @Override
    public Set<T> difference(Set<T> s) {
        Set<T> result = factory();
        for (T element : this) {
            if (!s.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    // Main per testare l'implementazione
    public static void main(String[] args) {
        // Creazione dei set s e t
        SetLL<Integer> s = new SetLL<>();
        SetLL<Integer> t = new SetLL<>();

        // Popolamento set s: 7 4 10 7 6 2
        s.add(7);
        s.add(4);
        s.add(10);
        s.add(7); // duplicato, non verrà aggiunto
        s.add(6);
        s.add(2);

        // Popolamento set t: 2 14 6
        t.add(2);
        t.add(14);
        t.add(6);

        System.out.println("Set s: " + s);
        System.out.println("Set t: " + t);

        // Operazioni sui set
        Set<Integer> unionSet = s.union(t);
        Set<Integer> differenceSet = s.difference(t);
        Set<Integer> intersectionSet = s.intersection(t);

        System.out.println("\nOperazioni sui set:");
        System.out.println("s ∪ t = " + unionSet);
        System.out.println("s - t = " + differenceSet);
        System.out.println("s ∩ t = " + intersectionSet);

    }
}
