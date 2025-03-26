package poo.tracce.novembre23;

public interface DeQueue<T> {

    int size();
    boolean contains( T x );
    void clear();
    void offer( T e );
    T poll();
    T peek();
    T pop();
    void push( T e );


}
