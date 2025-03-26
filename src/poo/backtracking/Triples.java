package poo.backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Triples extends Backtracking<Integer, Integer> {
    private int[] a;
    private int x;
    private int numSol;
    private int[] b;
    private ArrayList<int[]> soluzioni = new ArrayList<>();

    public Triples(int[] a, int x) {
        if (!distinti(a) || a.length < 3) throw new RuntimeException();
        this.a = new int[a.length];
        System.arraycopy(a, 0, this.a, 0, a.length);
        this.b = new int[a.length];
        this.x = x;
    }

    private boolean distinti(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i != j) {
                    if (a[i] == a[j])
                        return false;
                }
            }
        }
        return true;
    }

    @Override
    protected boolean esisteSoluzione(Integer pds) {
        return true;
    }

    @Override
    protected boolean assegnabile(Integer pds, Integer s) {
        //devono essere distinti e diversi da 0
        for (int i = 0; i < pds; i++) {
            if (b[i] == s) return false;
        }
        return b[pds] != 0;
    }

    @Override
    protected void assegna(Integer ps, Integer s) {

    }

    @Override
    protected void deassegna(Integer ps, Integer s) {

    }

    @Override
    protected void scriviSoluzione(Integer integer) {

    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> puntiDiScelta = new ArrayList<>();
        for (int i = 0; i < b.length; i++) {
            puntiDiScelta.add(i);
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte(Integer integer) {
        Collection<Integer> scelte = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            scelte.add(i);
        }
        return scelte;
    }
}
