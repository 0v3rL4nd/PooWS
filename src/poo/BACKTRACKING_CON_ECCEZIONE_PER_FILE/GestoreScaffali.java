package BACKTRACKING_CON_ECCEZIONE_PER_FILE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

record Coppia(int i, int j) {
}

public class GestoreScaffali extends Backtracking<Coppia, Integer> {
    private int maxPortata;
    private int[][] scaffalatura;
    private List<Integer> pacchiDaSistemare = new LinkedList<>();
    private int numSol;

    private int scaffali;
    private int slot;

    public GestoreScaffali(int n, int m, int maxPortata, List<Integer> pacchi) {
        this.scaffali = n;
        this.slot = m;
        this.maxPortata = maxPortata;
        this.scaffalatura = new int[n][m];
        this.pacchiDaSistemare = new LinkedList<>(pacchi);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                scaffalatura[i][j] = 0;
            }
        }
    }


    @Override
    protected boolean esisteSoluzione(Coppia coppia) {
        return pacchiDaSistemare.isEmpty();
    }

    @Override
    protected boolean assegnabile(Coppia coppia, Integer integer) {
        int i = coppia.i();
        int j = coppia.j();
        if (scaffalatura[i][j] != 0) return false;
        int somma = 0;
        for (int k = 0; k < slot; k++) {
            somma += scaffalatura[i][k];
        }
        return somma + integer <= maxPortata;
    }

    @Override
    protected void assegna(Coppia ps, Integer integer) {
        scaffalatura[ps.i()][ps.j()] = integer;
        pacchiDaSistemare.remove(integer);
    }

    @Override
    protected void deassegna(Coppia ps, Integer integer) {
        scaffalatura[ps.i()][ps.j()] = 0;
        pacchiDaSistemare.add(integer);
    }

    @Override
    protected void scriviSoluzione(Coppia coppia) {
        numSol++;
        System.out.println("Soluzione numero = " + numSol);
        for (int i = 0; i < scaffali; i++) {
            for (int j = 0; j < slot; j++) {
                System.out.print(scaffalatura[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    protected List<Coppia> puntiDiScelta() {
        List<Coppia> puntiDiScelta = new ArrayList<>();
        for (int i = 0; i < scaffali; i++) {
            for (int j = 0; j < slot; j++) {
                puntiDiScelta.add(new Coppia(i, j));
            }
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte(Coppia coppia) {
        return new LinkedList<>(pacchiDaSistemare);
    }

    public static void main(String[] args) throws IOException {
        List<Integer> pacchiDaSistemare = new LinkedList<>();
        pacchiDaSistemare.add(1);
        pacchiDaSistemare.add(2);
        pacchiDaSistemare.add(3);
        pacchiDaSistemare.add(4);
        pacchiDaSistemare.add(2);
        pacchiDaSistemare.add(3);

        GestoreScaffali gs = new GestoreScaffali(2, 3, 8, pacchiDaSistemare);
        gs.risolvi();
    }
}
