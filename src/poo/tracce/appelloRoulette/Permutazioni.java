package poo.tracce.appelloRoulette;

/*
scrivere un classe Permutazioni erede di backtracking che genera tutte le permutazioni di un array a di interi,
supposti tutti distinti, utilizzando la tecnica di backtracking..
Le permutazoni vanno fenerate utilizzando un array b di supporto, avente la stessa dimensione di a e visualizzate su standard output.
La classe Permutazioni riceve, a tempo di costruzione, l'array con gli elementi da permutare e solleva un'ecceione runtime se l'array contiene elementi duplicati.
includere nella classe permutazioni un metodo main di prova.
 */

import poo.backtracking.Backtracking;

import java.util.Collection;
import java.util.List;

import java.util.*;

public class Permutazioni extends Backtracking<Integer, Integer> {
    private int[] a;
    private int[] b;
    private boolean[] usato;

    public Permutazioni(int[] a) {
        if (haDuplicati(a)) {
            throw new RuntimeException("L'array contiene elementi duplicati");
        }
        this.a = a;
        this.b = new int[a.length];
        this.usato = new boolean[a.length];
    }

    private boolean haDuplicati(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean assegnabile(Integer p, Integer s) {
        return !usato[s];
    }

    @Override
    protected void assegna(Integer p, Integer s) {
        b[p] = a[s];
        usato[s] = true;
    }

    @Override
    protected void deassegna(Integer p, Integer s) {
        usato[s] = false;
    }

    @Override
    protected void scriviSoluzione(Integer p) {
        System.out.println(Arrays.toString(b));
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> punti = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            punti.add(i);
        }
        return punti;
    }

    @Override
    protected Collection<Integer> scelte(Integer p) {
        List<Integer> scelte = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            scelte.add(i);
        }
        return scelte;
    }

    @Override
    protected boolean esisteSoluzione(Integer p) {
        return p == a.length - 1;
    }

    public static void main(String[] args) {
        int[] numeri = {1, 2, 3};
        Permutazioni permutazioni = new Permutazioni(numeri);
        permutazioni.risolvi();
    }
}
