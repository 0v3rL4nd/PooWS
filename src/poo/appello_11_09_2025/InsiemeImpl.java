package poo.appello_11_09_2025;

import java.util.*;

public class InsiemeImpl<T extends Comparable<? super T>> extends InsiemeAstratto<T> {
//ERRORE!! avevo scritto implements Insieme<T>...
    private final LinkedList<T> insieme;

    public InsiemeImpl(LinkedList<T> insieme) {
        this.insieme = insieme;
    }

    public InsiemeImpl() {
        this.insieme = new LinkedList<>();
    }

    @Override
    public boolean eVuoto() {
        return insieme.isEmpty();
    }

    @Override
    public void svuota() {
        ListIterator<T> it = insieme.listIterator();
        while (it.hasNext()) {
            T x = (T) it.next();
            insieme.remove(x);
        }
    }

    @Override
    public boolean contiene(T x) {
        ListIterator<T> it = insieme.listIterator();
        while (it.hasNext()) {
            T y = (T) it.next();
            if (x.equals(y)) return true;
        }
        return false;
    }

    @Override
    public boolean aggiungi(T x) {
        ListIterator<T> it = insieme.listIterator();
        while (it.hasNext()) {
            T y = (T) it.next();
            if (x.equals(y)) return false;
        }
        insieme.add(x);
        return true;
    }

    @Override
    public boolean rimuovi(T x) {
        ListIterator<T> it = insieme.listIterator();
        while (it.hasNext()) {
            T y = (T) it.next();
            if (x.equals(y)) {
                insieme.remove(x);
                return true;
            }
        }
        return false;
    }

    @Override
    public Insieme<T> unione(Insieme<T> i) {
        Insieme<T> result = new InsiemeImpl<>();
        for (T element : this) {
            result.aggiungi(element);
        }
        for (T element : i) {
            result.aggiungi(element);
        }
        return result;
    }


    @Override
    public Insieme<T> intersezione(Insieme<T> i) {
        Insieme<T> result = new InsiemeImpl<>();
        for (T element : this) {
            if (i.contiene(element)) {
                result.aggiungi(element);
            }
        }
        return result;
    }

    @Override
    public Insieme<T> differenza(Insieme<T> i) {
        Insieme<T> result = new InsiemeImpl<>();
        for (T element : this) {
            if (!i.contiene(element)) {
                result.aggiungi(element);
            }
        }
        return result;
    }

    @Override
    public Insieme<T> differenzaSimmetrica(Insieme<T> i) {
        Insieme<T> result;
        Insieme<T> a_b = this.differenza(i);
        Insieme<T> b_a = i.differenza(this); //i.differenza(i).... ho per caso bevuto?!
        result = a_b.unione(b_a);
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return insieme.iterator();
    }

    public static Insieme<Integer> crivello(int N) {
        InsiemeImpl<Integer> crivello = new InsiemeImpl<>();
        for (int i = 2; i <= N; i++) {
            crivello.aggiungi(i);
        }
        for (int x = 2; x <= Math.sqrt(N); x++) {
            if (crivello.contiene(x)) {
                for (int multiplo = x * 2; multiplo <= N; multiplo += x) {
                    crivello.rimuovi(multiplo);
                }
            }
        }

        return crivello;
    }

    public static void main (String[]args){
            System.out.println("Crivello di Eratostene");
            Scanner sc = new Scanner(System.in);
            System.out.print("Inserisci un numero: ");
            int N = sc.nextInt();
            while(N<=2){
                System.out.println("Inserire numero maggiore di 2: ");
                N = sc.nextInt();
            }
            Insieme<Integer> primi = crivello(N);
            System.out.println("Numeri primi fino a " + N + ": " + primi);
    }
}
