package poo.tracce.DequeueRPN;

import java.util.*;

/**
 * Interfaccia DeQueue che rappresenta un ADT che può comportarsi come coda o stack
 */
interface DeQueue<T> extends Iterable<T> {

    /**
     * Ritorna il numero di elementi della collezione
     */
    int size();

    /**
     * Ritorna true se x è presente nella collezione, false altrimenti
     */
    default boolean contains(T x) {
        for (T element : this) {
            if (Objects.equals(element, x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Svuota la collezione
     */
    void clear();

    /**
     * Aggiunge e in coda
     */
    void offer(T e);

    /**
     * Rimuove e ritorna l'elemento di testa
     * @throws NoSuchElementException se la collezione è vuota
     */
    T poll();

    /**
     * Aggiunge e in testa
     */
    void push(T e);

    /**
     * Rimuove e ritorna l'elemento di testa
     * @throws NoSuchElementException se la collezione è vuota
     */
    default T pop() {
        return poll(); // pop() e poll() fanno la stessa cosa: rimuovono dalla testa
    }

    /**
     * Come poll() o pop() ma senza rimuovere l'elemento
     * @throws NoSuchElementException se la collezione è vuota
     */
    T peek();

    /**
     * Verifica se la collezione è vuota
     */
    default boolean isEmpty() {
        return size() == 0;
    }
}