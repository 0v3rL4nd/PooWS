package poo.backtracking;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Path extends Backtracking<Pair, Pair> {
    private int[][] a;
    private int numSol;
    private List<Pair> percorso = new LinkedList<>();

    private List<Pair> diagonale = new LinkedList<>();

    public Path(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a[0].length) {
                throw new IllegalArgumentException("Matrice non quadrata");
            }
        }
        this.a = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                this.a[i][j] = a[i][j];
                if (i == j) {
                    Pair diag = new Pair(i, j);
                    diagonale.add(diag);
                }
            }
        }
        Pair origine = new Pair(0, 0);
        Pair end = new Pair(a.length - 1, a.length - 1);
        diagonale.remove(origine);
        diagonale.remove(end);
        percorso.add(origine);
        System.out.println("Diagonale = "+diagonale.toString());

    }

    @Override
    protected boolean esisteSoluzione(Pair pair) {
        //fin quanto esiste una soluzione? fintanto che non sono alla fine della matrice
        Pair end = new Pair(a.length - 1, a.length - 1);
        Pair origine = new Pair(0,0);
       // System.out.println("Percorso = " + percorso.toString());
        return percorso.getLast().equals(end);
    }

    @Override
    protected boolean assegnabile(Pair pds, Pair scelta) {
        Collection<Pair> scelte = this.scelte(pds);
        //io ho la certezza che il mio pds non sia sulla diagonale, nè tantomeno scelte da quel pds
       // System.out.println("Scelta = "+scelta);
       // System.out.println(pds+" "+scelte.toString());
        return scelte.contains(scelta);
    }

    @Override
    protected void assegna(Pair ps, Pair scelta) {
        percorso.add(scelta);
    }

    @Override
    protected void deassegna(Pair ps, Pair scelta) {
        percorso.removeLast();
    }

    @Override
    protected void scriviSoluzione(Pair pair) {
        numSol++;
        System.out.println("Soluzione " + numSol + " partendo da " + pair);
        System.out.println(percorso.toString());

    }

    @Override
    protected List<Pair> puntiDiScelta() {
        //i miei punti di scelta sono tutte le Pair della matrice
        List<Pair> puntiDiScelta = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (i != j) {
                    Pair pair = new Pair(i, j);
                    puntiDiScelta.add(pair);
                }
            }
        }
        //se sono qua ho aggiunto tutte le possibili coppie (tranne quelle della diagonale principale)
        Pair end = new Pair(a.length - 1, a.length - 1);
        puntiDiScelta.add(end);
       // System.out.println("Punti di scelta = "+puntiDiScelta.toString());
        return percorso;
    }

    @Override
    protected Collection<Pair> scelte(Pair pair) {
        Collection<Pair> scelte = new LinkedList<>();
        int i = pair.getI();
        int j = pair.getJ();
        int k = a[i][j];
        //System.out.println("Percorso = "+percorso.toString());
        Pair nord = new Pair(i - k, j);
       // System.out.println("Nord = "+nord);
        if (nord.getI() >= 0 && !percorso.contains(nord) && !diagonale.contains(nord)) scelte.add(nord);
        Pair sud = new Pair(i + k, j);
      //  System.out.println("Sud = "+sud);
        if (sud.getI() < a.length && !percorso.contains(sud) && !diagonale.contains(sud)) scelte.add(sud);
        Pair est = new Pair(i, j + k);
     //   System.out.println("Est = "+est);
        if (est.getJ() < a.length && !percorso.contains(est) && !diagonale.contains(est)) scelte.add(est);
        Pair ovest = new Pair(i, j - k);
     //   System.out.println("Ovest = "+ovest);
        if (ovest.getJ() >= 0 && !percorso.contains(ovest) && !diagonale.contains(ovest)) scelte.add(ovest);

        return scelte;
    }

    public static void main(String[] args) throws IOException {
        int[][] a = {{3, 0, 2, 3}, {2, 4, 2, 1}, {1, 2, 1, 3}, {1, 1, 1, 2}};
        Path p = new Path(a);
        p.risolvi();
    }
}
