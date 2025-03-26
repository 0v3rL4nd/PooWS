package poo.backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NQueens extends Backtracking<Integer, Integer> {
    private boolean[][] board;
    private int numSol;
    private int numRegine;

    public NQueens(int dim) {
        if (dim <= 3) {
            throw new IllegalArgumentException();
        }
        this.board = new boolean[dim][dim];
        this.numRegine = dim;
    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == board.length-1;
    }

    @Override
    protected boolean assegnabile(Integer pds, Integer s) {
        //io ho un indice di riga (pds) e uno di colonna (s), devo vedere se ci siano altre regine
        //a nord
        for (int r = pds - 1; r >= 0; r--) {
            if (board[r][s]) return false;
        }
        //NO
        for (int r = pds - 1, c = s - 1; r >= 0 && c >= 0; r--, c--) {
            if(board[r][c]) return false;
        }
        //NE
        for (int r = pds-1, c = s+1; r >= 0 && c < board.length ; r--, c++) {
            if(board[r][c]) return false;
        }
        return true;
    }

    @Override
    protected void assegna(Integer ps, Integer s) {
        board[ps][s] = true;
    }

    @Override
    protected void deassegna(Integer ps, Integer s) {
        board[ps][s] = false;
    }

    @Override
    protected void scriviSoluzione(Integer integer) {
        System.out.println("============");
        numSol++;
        System.out.println("Numero soluzione = "+numSol);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if(j != board[i].length-1) System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println("============");
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> puntiDiScelta = new ArrayList<>();
        for (int i = 0; i < numRegine; i++) {
            puntiDiScelta.add(i);
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte(Integer integer) {
        Collection<Integer> scelte = new ArrayList<>(); //per ogni riga, quante scelte ho? tutte le colonne
        for (int i = 0; i < numRegine; i++) {
            scelte.add(i);
        }
        return scelte;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens(8);
        nq.risolvi();
    }
}
