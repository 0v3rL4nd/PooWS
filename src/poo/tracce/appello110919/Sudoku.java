package poo.tracce.appello110919;

import poo.backtracking.Backtracking;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Sudoku extends Backtracking<int[], Integer> {
//
//    private int[][] grid = new int[9][9];
//
//    public Sudoku(int[][] grid) {
//        this.grid = grid;
//    }
//    @Override
//    protected boolean assegnabile(Object ) {
//        /*un numero nel sudoku è assagnabile se:
//        *  - non c'è un altro numero nella stessa riga
//        *  - non c'è un altro numero nella stessa colonna
//        *  - non c'è un altro numero nella stessa sotto matrice 3x3
//        */
//        if( inColonna(o) && inRiga(o) && inSottoMatrice(o)){
//            return false;
//        }
//        return true;
//    }
//
//    private boolean inColonna(Object o){
//        for(int i = 0; i < grid.length; i++){
//            if(grid[i][o]!=0) return true;
//        }
//    }
//
//    @Override
//    protected void assegna(Object ps, Object o) {
//        if(assegnabile(ps,o)){
//            grid[][] = o;
//        }
//    }
//
//    @Override
//    protected void deassegna(Object ps, Object o) {
//        ps = null;
//    }
//
//    @Override
//    protected void scriviSoluzione(Object o) {
//
//    }
//
//    @Override
//    protected List puntiDiScelta() {
//        //una lisa che contiente le coppie (i,j)
//        List<AbstractMap.SimpleEntry<Integer, Integer>> coppie = new ArrayList<>();
//        for(int i = 0; i < grid.length; i++){
//            for(int j = 0; j < grid[i].length; j++){
//                if(grid[i][j]==0) coppie.add(new AbstractMap.SimpleEntry<>(i,j));
//            }
//        }
//        return coppie;
//    }
//
//    @Override
//    protected Collection scelte(Object o) {
//        for(int i = 0; i)
//    }
        private static final int SIZE = 9;
        private static final int EMPTY = 0;
        private int[][] board;

        public Sudoku(int[][] board) {
            this.board = board;
        }

        @Override
        protected boolean assegnabile(int[] p, Integer s) {
            int row = p[0], col = p[1];
            return isValid(board, row, col, s);
        }

        @Override
        protected void assegna(int[] p, Integer s) {
            board[p[0]][p[1]] = s;
        }

        @Override
        protected void deassegna(int[] p, Integer s) {
            board[p[0]][p[1]] = EMPTY;
        }

        @Override
        protected void scriviSoluzione(int[] p) {
            printBoard(board);
        }

        @Override
        protected List<int[]> puntiDiScelta() {
            List<int[]> emptyCells = new ArrayList<>();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == EMPTY) {
                        emptyCells.add(new int[]{i, j});
                    }
                }
            }
            return emptyCells;
        }

        @Override
        protected Collection<Integer> scelte(int[] p) {
            List<Integer> choices = new ArrayList<>();
            for (int num = 1; num <= SIZE; num++) {
                if (isValid(board, p[0], p[1], num)) {
                    choices.add(num);
                }
            }
            return choices;
        }

        private boolean isValid(int[][] board, int row, int col, int num) {
            for (int i = 0; i < SIZE; i++) {
                if (board[row][i] == num || board[i][col] == num) {
                    return false;
                }
            }
            int boxRowStart = row - row % 3;
            int boxColStart = col - col % 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[boxRowStart + i][boxColStart + j] == num) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void printBoard(int[][] board) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {
            int[][] board = {
                    {5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {4, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 6},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 9}
            };

            Sudoku solver = new Sudoku(board);
            solver.risolvi();
        }
    }

