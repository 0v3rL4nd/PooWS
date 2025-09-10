package poo.tracce.SudokuNoGUI_RiferimentiIncrociati;

class Sudoku {
    private int[][] board = new int[9][9];           // tutti 0 per default
    private boolean[][] blocked = new boolean[9][9]; // celle bloccate - tutti false inizialmente
    private int numSol = 0;

    public Sudoku() {}

    /**
     * Costruttore che inizializza il Sudoku con valori predefiniti
     * @param b array di triple {riga, colonna, valore}
     */
    public Sudoku(int[][] b) {
        for (int i = 0; i < b.length; ++i) {
            int r = b[i][0], c = b[i][1], v = b[i][2];

            // Validazione input
            if (r < 0 || r >= 9 || c < 0 || c >= 9 || v < 1 || v > 9)
                throw new IllegalArgumentException("Coordinate o valore non validi: [" + r + "," + c + "," + v + "]");

            blocked[r][c] = true;
            if (assegnabile(r, c, v)) {
                board[r][c] = v;
            } else {
                throw new IllegalArgumentException("Valore " + v + " non assegnabile in posizione [" + r + "," + c + "]");
            }
        }
    }

    /**
     * Avvia la risoluzione del Sudoku
     */
    public void risolvi() {
        numSol = 0;
        colloca(0, 0);
        if (numSol == 0) {
            System.out.println("Nessuna soluzione trovata!");
        }
    }

    /**
     * Metodo ricorsivo per il backtracking
     * @param i riga corrente
     * @param j colonna corrente
     */
    private void colloca(int i, int j) {
        if (blocked[i][j]) {
            // Cella bloccata - passa alla successiva
            if (i == board.length - 1 && j == board[i].length - 1) {
                scriviSoluzione();
            } else {
                if (j == board[i].length - 1)
                    colloca(i + 1, 0);
                else
                    colloca(i, j + 1);
            }
        } else {
            // Cella libera - prova tutti i valori da 1 a 9
            for (int s = 1; s <= 9; ++s) {
                if (assegnabile(i, j, s)) {
                    assegna(i, j, s);

                    if (i == board.length - 1 && j == board[i].length - 1) {
                        scriviSoluzione();
                    } else {
                        if (j == board[i].length - 1)
                            colloca(i + 1, 0);
                        else
                            colloca(i, j + 1);
                    }

                    deassegna(i, j);
                }
            }
        }
    }

    /**
     * Verifica se un valore può essere assegnato a una posizione
     */
    private boolean assegnabile(int i, int j, int s) {
        // Coordinate in alto a sinistra del quadrante 3x3
        int r = (i / 3) * 3, c = (j / 3) * 3;

        // Verifica unicità nel quadrante 3x3
        for (int row = r; row <= r + 2; ++row) {
            for (int col = c; col <= c + 2; ++col) {
                if (board[row][col] == s) return false;
            }
        }

        // Verifica unicità nella riga
        for (int col = 0; col < 9; ++col) {
            if (board[i][col] == s) return false;
        }

        // Verifica unicità nella colonna
        for (int row = 0; row < 9; ++row) {
            if (board[row][j] == s) return false;
        }

        return true;
    }

    private void assegna(int i, int j, int s) {
        board[i][j] = s;
    }

    private void deassegna(int i, int j) {
        board[i][j] = 0;
    }

    private void scriviSoluzione() {
        numSol++;
        System.out.println("Soluzione #" + numSol + ":");
        System.out.println(toString());
        System.out.println("─".repeat(25));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; ++i) {
            if (i % 3 == 0 && i != 0) {
                sb.append("─".repeat(21)).append("\n");
            }
            for (int j = 0; j < 9; ++j) {
                if (j % 3 == 0 && j != 0) {
                    sb.append("│ ");
                }
                sb.append(board[i][j] == 0 ? "." : String.valueOf(board[i][j])).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Schema di test più semplice
        int[][] testSchema = {
                {0,0,5},{0,1,3},{0,4,7},
                {1,0,6},{1,3,1},{1,4,9},{1,5,5},
                {2,1,9},{2,2,8},{2,7,6},
                {3,0,8},{3,4,6},{3,8,3},
                {4,0,4},{4,3,8},{4,5,3},{4,8,1},
                {5,0,7},{5,4,2},{5,8,6},
                {6,1,6},{6,6,2},{6,7,8},
                {7,3,4},{7,4,1},{7,5,9},{7,8,5},
                {8,7,7},{8,8,9}
        };

        System.out.println("=== SUDOKU SOLVER ===");
        Sudoku sudoku = new Sudoku(testSchema);
        System.out.println("Schema iniziale:");
        System.out.println(sudoku);
        System.out.println("\nRisoluzione in corso...\n");
        sudoku.risolvi();
    }
}