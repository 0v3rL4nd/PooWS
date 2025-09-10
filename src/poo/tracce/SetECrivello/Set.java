package poo.tracce.SetECrivello;

import java.util.*;

// Interfaccia Set generica
public interface Set<T> extends Iterable<T> {
    int size();
    boolean contains(T x);
    void clear();
    void add(T x);
    void remove(T x);
    Set<T> factory();
    Set<T> union(Set<T> s);
    Set<T> difference(Set<T> s);

    // Metodo di default per l'intersezione
    default Set<T> intersection(Set<T> s) {
        Set<T> result = factory();
        for (T element : this) {
            if (s.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    // Metodo di default per verificare se è vuoto
    default boolean isEmpty() {
        return size() == 0;
    }
}
