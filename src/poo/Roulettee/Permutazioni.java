package Roulettee;

import BACKTRACKING_CON_ECCEZIONE_PER_FILE.Backtracking;

import java.io.IOException;
import java.util.*;

public class Permutazioni extends Backtracking<Integer, Integer> {
    private int[] a;
    private int[] b;
    private int numSol;

    private int somma = 0;

    public Permutazioni(int[] a, int x) {
        if (!distinti(a)) {
            throw new IllegalArgumentException("Array non distinto");
        }
        this.a = new int[a.length];
        System.arraycopy(a, 0, this.a, 0, a.length);
        this.b = new int[a.length];
        somma = x;
    }

    private boolean distinti(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i != j) {
                    if (a[i] == a[j]) return false;
                }
            }
        }
        return true;
    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == a.length - 1;
    }

    @Override
    protected boolean assegnabile(Integer pds, Integer s) {
        //gli elementi devono essere distinti
        for (int i = 0; i < pds; i++) {
            if (b[i] == s) return false;
        }
        return true;
    }

    @Override
    protected void assegna(Integer ps, Integer s) {
        b[ps] = s;
    }

    @Override
    protected void deassegna(Integer ps, Integer s) {

    }

    @Override
    protected void scriviSoluzione(Integer integer) {
        int s = 0;
        for (int i = 0; i < b.length; i++) {
            s += b[i];
        }
        if (s == somma) {
            numSol++;
            System.out.println("Soluzione " + numSol + " " + Arrays.toString(b)+" "+s);
        }
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> puntiDiScelta = new LinkedList<>();
        for (int i = 0; i < b.length; i++) {
            puntiDiScelta.add(i);
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte(Integer integer) {
        Collection<Integer> scelte = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            scelte.add(a[i]);
        }
        return scelte;
    }

    public static void main(String[] args) throws IOException {
        int[] a = {1, 2, 3};
        Permutazioni p = new Permutazioni(a,6);
        p.risolvi();
    }
}
