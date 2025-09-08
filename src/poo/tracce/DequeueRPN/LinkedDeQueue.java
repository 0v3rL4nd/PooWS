package poo.tracce.DequeueRPN;

import java.util.*;

/**
 * Implementazione concreta di DeQueue usando una lista concatenata
 */
class LinkedDeQueue<T> extends AbstractDequeue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Nodo della lista concatenata
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Costruttore
     */
    public LinkedDeQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void offer(T e) {
        Node<T> newNode = new Node<>(e);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("DeQueue is empty");
        }

        T data = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        size--;
        return data;
    }

    @Override
    public void push(T e) {
        Node<T> newNode = new Node<>(e);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("DeQueue is empty");
        }
        return head.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedDeQueueIterator();
    }

    /**
     * Iterator che attraversa dal primo (testa) all'ultimo (coda)
     */
    private class LinkedDeQueueIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}