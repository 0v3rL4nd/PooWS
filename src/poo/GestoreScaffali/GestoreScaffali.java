package GestoreScaffali;

import BACKTRACKING_CON_ECCEZIONE_PER_FILE.Backtracking;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GestoreScaffali extends Backtracking<Coppia, Integer> {
    private List<Integer> pacchiDaSistemare = new LinkedList<>();
    private Integer[][] scaffalatura;
    private final int maxPortata;

    private int numSol;

    public GestoreScaffali(int righe, int colonne, int maxPortata, List<Integer> pacchiDaSistemare) {
        if (maxPortata < 1) throw new IllegalArgumentException("Portata non positiva");
        this.maxPortata = maxPortata;
        if (pacchiDaSistemare.isEmpty()) throw new IllegalArgumentException("Non ho pacchi da sistemare");
        this.scaffalatura = new Integer[righe][colonne];
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                scaffalatura[i][j] = 0;
            }
        }
        this.pacchiDaSistemare.addAll(pacchiDaSistemare);
        System.out.println("Pacchi da sistemare = " + pacchiDaSistemare);
    }

    @Override
    protected boolean esisteSoluzione(Coppia coppia) {
        return pacchiDaSistemare.isEmpty();
    }

    @Override
    protected boolean assegnabile(Coppia coppia, Integer integer) {
        //devo vedere se posso assegnare il pacco in quella riga (ovvero se non supero la maxPortata)
        int portataCurr = 0;
        for (int i = 0; i < scaffalatura[coppia.i()].length; i++) {
            if (scaffalatura[coppia.i()][i] != 0) {
                portataCurr += scaffalatura[coppia.i()][i];
            }
            if (portataCurr >= maxPortata) return false;
        }
        // System.out.println(portataCurr+" per "+coppia);
        //altrimenti significa che la somma sia < della maxPortata
        //devo verificare se l'aggiunta dell'elemento sfori la portata dello scaffale
        return portataCurr + integer <= maxPortata;
        //altrimenti va bene, la somma è <=
    }

    @Override
    protected void assegna(Coppia ps, Integer integer) {
        scaffalatura[ps.i()][ps.j()] = integer;
        pacchiDaSistemare.remove(integer);
        //System.out.println("Pacchi da sistemare dopo la remove = " + pacchiDaSistemare);
    }

    @Override
    protected void deassegna(Coppia ps, Integer integer) {
        scaffalatura[ps.i()][ps.j()] = 0;
        pacchiDaSistemare.add(integer);
        //System.out.println("Pacchi da sistemare dopo la add = "+pacchiDaSistemare);
    }

    @Override
    protected void scriviSoluzione(Coppia coppia) {

            System.out.println("=====");
            numSol++;
            System.out.println("Soluzione numero: " + numSol);
            for (int i = 0; i < scaffalatura.length; i++) {
                for (int j = 0; j < scaffalatura[i].length; j++) {
                    System.out.print(scaffalatura[i][j]);
                    if (j != scaffalatura[i].length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            System.out.println("=====");
        
    }

    @Override
    protected List<Coppia> puntiDiScelta() {
        //tutti i punti dello scaffale dove posso andare ad inserire un pacco
        List<Coppia> puntiDiScelta = new LinkedList<>();
        for (int i = 0; i < scaffalatura.length; i++) {
            for (int j = 0; j < scaffalatura[i].length; j++) {
                puntiDiScelta.add(new Coppia(i, j));
            }
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte(Coppia coppia) {
        //System.out.println("Scelte = "+pacchiDaSistemare);
        return new LinkedList<>(pacchiDaSistemare);
    }

    public static void main(String[] args) throws IOException {
        List<Integer> pacchi = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            Integer pacco = new Random().nextInt(1, 20);
            pacchi.add(pacco);
        }
        GestoreScaffali gs = new GestoreScaffali(2, 2, 25, pacchi);
        gs.risolvi();
    }
}
