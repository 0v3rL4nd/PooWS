package poo.backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Permutazioni extends Backtracking<Integer, Integer> {

    private int[] a, b;
    private int numSol;

    public Permutazioni(int[] a) {
        if (a.length < 1) {
            throw new IllegalArgumentException();
        }
        this.a = new int[a.length];
        this.b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            this.a[i] = a[i];
        }

    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == b.length-1;
    }

    @Override
    protected boolean assegnabile(Integer pds, Integer s) {
        for (int i = 0; i <= pds; i++) {
            if (b[i] == s) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void assegna(Integer ps, Integer integer) {
        b[ps] = integer;
    }

    @Override
    protected void deassegna(Integer ps, Integer integer) {

    }

    @Override
    protected void scriviSoluzione(Integer integer) {
        numSol++;
        System.out.println("Soluzione numero " + numSol);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> puntiDiScelta = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
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

    public static void main(String[] args) {
        int [] a = {1,2,3};
        Permutazioni p = new Permutazioni(a);
        p.risolvi();
    }
}
