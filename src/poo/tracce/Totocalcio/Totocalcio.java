package poo.tracce.Totocalcio;

import java.util.*;

class Totocalcio {
    private char[][] sistema;
    private int numeroRighe;
    private int numeroColonne;
    private int numeroSoluzioni;

    public Totocalcio(int righe) {
        this.numeroRighe = righe;
        this.numeroColonne = 13; // 13 partite sempre
        this.sistema = new char[numeroRighe][numeroColonne];
        this.numeroSoluzioni = 0;

        // Inizializza tutte le posizioni con spazio
        for (int i = 0; i < numeroRighe; i++) {
            Arrays.fill(sistema[i], ' ');
        }
    }

    /**
     * Sviluppa tutte le possibili colonne del sistema usando backtracking
     */
    public void sviluppa() {
        numeroSoluzioni = 0;
        System.out.println("Sviluppo sistema " + numeroRighe + "x" + numeroColonne + ":");
        System.out.println();
        colonna(0);
        System.out.println("Totale soluzioni generate: " + numeroSoluzioni);
    }

    /**
     * Metodo ricorsivo per generare le colonne
     */
    private void colonna(int col) {
        if (col == numeroColonne) {
            scriviSoluzione();
            return;
        }

        // Per ogni colonna, genera tutte le combinazioni possibili
        generaCombinazioni(col, 0);
    }

    /**
     * Genera le combinazioni per una colonna specifica
     */
    private void generaCombinazioni(int col, int riga) {
        if (riga == numeroRighe) {
            colonna(col + 1);
            return;
        }

        // Prova tutti i segni possibili: 1, X, 2
        char[] segni = {'1', 'X', '2'};
        for (char segno : segni) {
            sistema[riga][col] = segno;
            generaCombinazioni(col, riga + 1);
        }
    }

    private void scriviSoluzione() {
        numeroSoluzioni++;
        System.out.println("Colonna " + numeroSoluzioni + ":");

        for (int i = 0; i < numeroRighe; i++) {
            for (int j = 0; j < numeroColonne; j++) {
                System.out.print(sistema[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sistema Totocalcio ").append(numeroRighe).append("x").append(numeroColonne).append(":\n");

        for (int i = 0; i < numeroRighe; i++) {
            for (int j = 0; j < numeroColonne; j++) {
                sb.append(sistema[i][j] == ' ' ? '.' : sistema[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Main per test con sistema piccolo per evitare output eccessivo
    public static void main(String[] args) {
        // Test con sistema molto piccolo: 2 righe, ma solo 3 colonne per demo
        System.out.println("=== DEMO TOTOCALCIO ===");
        System.out.println("(Versione ridotta per demo: 2x3 invece di 13 colonne)");

        Totocalcio demo = new TotocalcioDemo(2, 3); // versione demo ridotta
        demo.sviluppa();
    }
}