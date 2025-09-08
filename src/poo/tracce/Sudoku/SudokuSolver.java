package poo.tracce.Sudoku;

import java.util.*;

class SudokuSolver {
    private Cella[][] sudoku;
    private List<int[][]> soluzioni;
    private int maxSoluzioni;
    private int soluzioniTrovate;

    // Ordine di attraversamento secondo la "logica dei settori"
    private int[][] ordineSettori;

    public SudokuSolver() {
        sudoku = new Cella[9][9];
        soluzioni = new ArrayList<>();
        maxSoluzioni = 1;
        inizializzaMatrice();
        calcolaOrdineSettori();
    }

    /**
     * Inizializza la matrice con celle vuote
     */
    private void inizializzaMatrice() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = new Cella();
            }
        }
    }

    /**
     * Calcola l'ordine di attraversamento secondo la logica dei settori
     */
    private void calcolaOrdineSettori() {
        ordineSettori = new int[81][2];
        int index = 0;

        // Attraversa i 9 settori 3x3
        for (int settoreRiga = 0; settoreRiga < 3; settoreRiga++) {
            for (int settoreCol = 0; settoreCol < 3; settoreCol++) {
                // All'interno di ogni settore
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int riga = settoreRiga * 3 + i;
                        int col = settoreCol * 3 + j;
                        ordineSettori[index][0] = riga;
                        ordineSettori[index][1] = col;
                        index++;
                    }
                }
            }
        }
    }

    /**
     * Risolve il Sudoku usando backtracking
     */
    public List<int[][]> risolvi(int numeroSoluzioni) {
        this.maxSoluzioni = numeroSoluzioni;
        this.soluzioniTrovate = 0;
        this.soluzioni.clear();

        backtrack(0);

        return new ArrayList<>(soluzioni);
    }

    /**
     * Algoritmo di backtracking ricorsivo
     */
    private boolean backtrack(int indice) {
        // Caso base: tutte le celle sono state processate
        if (indice == 81) {
            // Salva la soluzione corrente
            salvaSoluzione();
            soluzioniTrovate++;
            return soluzioniTrovate >= maxSoluzioni;
        }

        int riga = ordineSettori[indice][0];
        int col = ordineSettori[indice][1];
        Cella cella = sudoku[riga][col];

        // Se la cella è pre-impostata, salta alla prossima
        if (cella.getStato() == Stato.IMPOSTATO) {
            return backtrack(indice + 1);
        }

        // Prova tutti i valori da 1 a 9
        for (int valore = 1; valore <= 9; valore++) {
            if (isValoreValido(riga, col, valore)) {
                // Assegna il valore
                cella.setValore(valore);
                cella.setStato(Stato.ASSEGNATO);

                // Ricorsione
                if (backtrack(indice + 1)) {
                    return true; // Fermati se hai trovato abbastanza soluzioni
                }

                // Backtrack
                cella.svuota();
            }
        }

        return false;
    }

    /**
     * Verifica se un valore può essere assegnato a una cella
     */
    private boolean isValoreValido(int riga, int col, int valore) {
        // Verifica riga
        for (int j = 0; j < 9; j++) {
            if (j != col && sudoku[riga][j].getValore() == valore) {
                return false;
            }
        }

        // Verifica colonna
        for (int i = 0; i < 9; i++) {
            if (i != riga && sudoku[i][col].getValore() == valore) {
                return false;
            }
        }

        // Verifica settore 3x3
        int settoreRiga = (riga / 3) * 3;
        int settoreCol = (col / 3) * 3;

        for (int i = settoreRiga; i < settoreRiga + 3; i++) {
            for (int j = settoreCol; j < settoreCol + 3; j++) {
                if ((i != riga || j != col) && sudoku[i][j].getValore() == valore) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Salva la soluzione corrente
     */
    private void salvaSoluzione() {
        int[][] soluzione = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                soluzione[i][j] = sudoku[i][j].getValore();
            }
        }
        soluzioni.add(soluzione);
    }

    /**
     * Imposta un valore in una cella (pre-impostazione)
     */
    public void impostaCella(int riga, int col, int valore) {
        if (riga >= 0 && riga < 9 && col >= 0 && col < 9) {
            if (valore == 0) {
                sudoku[riga][col].svuota();
            } else if (valore >= 1 && valore <= 9) {
                sudoku[riga][col].setValore(valore);
                sudoku[riga][col].setStato(Stato.IMPOSTATO);
            }
        }
    }

    /**
     * Pulisce il sudoku (mantiene solo le celle impostate)
     */
    public void pulisci() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j].getStato() != Stato.IMPOSTATO) {
                    sudoku[i][j].svuota();
                }
            }
        }
    }

    /**
     * Resetta completamente il sudoku
     */
    public void reset() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j].setValore(0);
                sudoku[i][j].setStato(Stato.NON_ASSEGNATO);
            }
        }
    }

    public Cella getCella(int riga, int col) {
        return sudoku[riga][col];
    }
}