package poo.backtracking;

import BACKTRACKING_CON_ECCEZIONE_PER_FILE.Backtracking;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class NTorri extends Backtracking<Integer,Integer> {

    private boolean [][]board;
    private int numSol;
    private int Ntorri;
    public NTorri(int n) {
        this.board = new boolean[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                this.board[i][j] = false;
            }
        }
    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == board.length-1;
    }

    @Override
    protected boolean assegnabile(Integer riga, Integer colonna) {
        //verifichiamo che nella riga "riga" non ci siano altre torri prima dell'indice
        for (int i = 0; i < colonna; i++) {
            if(board[riga][i]){
                System.out.println("Qui con "+riga+" "+i);
                return false;
            }
        }
        //ora nella colonna
        for (int i = 0; i < riga; i++) {
            if(board[i][colonna]){
                System.out.println("QUI CON "+i+" "+colonna);
                return false;
            }
        }
        return true;
    }

    @Override
    protected void assegna(Integer ps, Integer integer) {
        board[ps][integer] = true;
        System.out.println("Ho assegnato correttamente in "+ps+" "+integer);
    }

    @Override
    protected void deassegna(Integer ps, Integer integer) {
        board[ps][integer] = false;
    }

    @Override
    protected void scriviSoluzione(Integer integer) {
        numSol++;
        System.out.println("Numero soluzione = "+numSol);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> puntiDiScelta = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            puntiDiScelta.add(i);
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte(Integer integer) {
        Collection<Integer> scelte = new LinkedList<>();
        for (int i = 0; i < board[0].length; i++) {
            scelte.add(i);
        }

        //System.out.println("Scelte "+scelte+" Per "+integer);
        return scelte;
    }

    public static void main(String[] args) throws IOException {
        NTorri n = new NTorri(4);
        n.risolvi();
    }
}
