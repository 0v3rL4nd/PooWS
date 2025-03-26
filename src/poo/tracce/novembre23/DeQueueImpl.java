package poo.tracce.novembre23;

import java.util.NoSuchElementException;

public class DeQueueImpl<T> extends AbstractDeQueue<T>{

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        T element;
        Node next;
        Node prev;

        private Node next(T e){
            Node n = new Node();
            n.element = e;
            n.next = null;
            n.prev = this;
            this.next = n;
            return n;
        }

        private Node prev(T e){
            Node n = new Node();
            n.element = e;
            n.prev = null;
            n.next = this;
            this.prev = n;
            return n;
        }
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object x) {
        Node n = tail;
        while(n!=null){
            if(n.element.equals(x)){
                return true;
            }
            n = n.prev;
        }
        return false;
    }

    @Override
    public void clear() {
        tail=null;
        head=null;
        size=0;

    }

    @Override
    public void offer(Object e) {
        size++;
        if( head == null){
            head=tail;
        }
        Node n = new Node();
        n.element = (T) e;
        n.next = null;
        n.prev = tail;
        tail = n;
    }

    @Override
    public Object poll() {
        try {
            if (head == null) {
                return null;
            }
            Node n = head.next;
            head.next = null;
            head = n;
            size--;
            return head;
        } catch (NoSuchElementException e) {
            return "Empty list";
        }
    }

    @Override
    public Object peek() {
        try {
            return head;
        }
        catch (NoSuchElementException e) {
            return "Empty list";
        }
    }

    @Override
    public Object pop() {
        try {
            if (tail == null) {
                return null;
            }
            Node n = tail.prev;
            tail.prev = null;
            tail = n;
            size--;
            return tail;
        }
        catch (NoSuchElementException e) {
            return "Empty list";
        }
    }

    @Override
    public void push(Object e) {
            size++;
            Node n = new Node();
            n.element = (T) e;
            n.next = head;
            n.prev = null;
            head = n;
            if (tail == null) {
                tail = head;
            }

    }


}
