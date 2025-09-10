package poo.tracce.Totocalcio;

import java.util.Arrays;

class TotocalcioDemo extends Totocalcio {
    private char[][] sistema;
    private int numeroRighe;
    private int numeroColonne;
    private int numeroSoluzioni;

    public TotocalcioDemo(int righe, int colonne) {
        super(righe);
        this.numeroRighe = righe;
        this.numeroColonne = colonne;
        this.sistema = new char[numeroRighe][numeroColonne];
        this.numeroSoluzioni = 0;

        for (int i = 0; i < numeroRighe; i++) {
            Arrays.fill(sistema[i], ' ');
        }
    }

    @Override
    public void sviluppa() {
        numeroSoluzioni = 0;
        System.out.println("Sviluppo sistema DEMO " + numeroRighe + "x" + numeroColonne + ":");
        System.out.println();
        colonna(0);
        System.out.println("Totale soluzioni generate: " + numeroSoluzioni);
    }

    private void colonna(int col) {
        if (col == numeroColonne) {
            scriviSoluzione();
            return;
        }

        generaCombinazioni(col, 0);
    }

    private void generaCombinazioni(int col, int riga) {
        if (riga == numeroRighe) {
            colonna(col + 1);
            return;
        }

        char[] segni = {'1', 'X', '2'};
        for (char segno : segni) {
            sistema[riga][col] = segno;
            generaCombinazioni(col, riga + 1);
        }
    }

    private void scriviSoluzione() {
        numeroSoluzioni++;
        System.out.printf("Soluzione %d:\n", numeroSoluzioni);

        for (int i = 0; i < numeroRighe; i++) {
            for (int j = 0; j < numeroColonne; j++) {
                System.out.print(sistema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}