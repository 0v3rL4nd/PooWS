package poo.tracce.AlberoEspressione_Permutazioni_Cruciverba;

import java.util.*;

class Permutazioni {
    private int[] a;  // array originale
    private int[] b;  // array per la permutazione corrente
    private boolean[] usato;  // array per tracciare gli elementi usati

    public Permutazioni(int[] a) {
        this.a = a.clone();
        this.b = new int[a.length];
        this.usato = new boolean[a.length];
    }

    public void risolvi() {
        System.out.println("Generazione permutazioni dell'array: " + Arrays.toString(a));
        permuta(0);
    }

    private void permuta(int ps) {
        if (ps == a.length) {
            scriviSoluzione();
            return;
        }

        for (int s = 0; s < a.length; s++) {
            if (assegnabile(s, ps)) {
                assegna(s, ps);
                permuta(ps + 1);
                deassegna(s, ps);
            }
        }
    }

    private boolean assegnabile(int s, int ps) {
        return !usato[s];
    }

    private void assegna(int s, int ps) {
        b[ps] = a[s];
        usato[s] = true;
    }

    private void deassegna(int s, int ps) {
        usato[s] = false;
    }

    private void scriviSoluzione() {
        System.out.println(Arrays.toString(b));
    }

    // Main di test
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        Permutazioni perm = new Permutazioni(array);
        perm.risolvi();
    }
}
