package BACKTRACKING_CON_ECCEZIONE_PER_FILE;

import com.sun.security.jgss.GSSUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

record Pair(int i, int j){
    @Override
    public String toString() {
        return "<"+i+";"+j+">";
    }
}

public class Path extends Backtracking<Pair,Pair>{
    private int [][] a;
    private List<Pair> percorso = new LinkedList<>();
    private int numSol;
    private int righe;
    private List<Pair> diagonale = new LinkedList<>();
    public Path(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            if(a[i].length != a[0].length){
                throw new IllegalArgumentException();
            }
        }
        righe = a.length;
        this.a = new int[righe][righe];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                this.a[i][j] = a[i][j];
                if(i == j){
                    Pair p = new Pair(i,j);
                    diagonale.add(p);
                }
            }
        }
        Pair o = new Pair(0,0);
        percorso.add(o);
        diagonale.removeFirst();
        diagonale.removeLast();
    }

    @Override
    protected boolean esisteSoluzione(Pair pair) {
        Pair end = new Pair(righe-1,righe-1);
        return percorso.getLast().equals(end);
    }

    @Override
    protected boolean assegnabile(Pair pds, Pair s) {
        //per le scelte ammissibili per pds, s è assegnabile? devo verificare due cose
        //1) non facciano parte nè pds nè s della diagonale
        System.out.println("Sto vedendo "+pds+" con questa scelta "+s);
        if(pds.equals(new Pair(0,0))) return true;
        if(scelte(pds).isEmpty()||diagonale.contains(pds) || diagonale.contains(s)){
            System.out.println("e non va bene");
            return false;
        }
        //2)la cella non faccia parte del percorso
        return !percorso.contains(s);
    }

    @Override
    protected void assegna(Pair ps, Pair s) {
        percorso.addLast(s);
        System.out.println("Percorso dopo l'aggiunta di "+s+" "+percorso);
    }

    @Override
    protected void deassegna(Pair ps, Pair s) {
        percorso.removeLast();
        System.out.println("Percorso dopo la rimozione di "+s+" "+percorso);
    }

    @Override
    protected void scriviSoluzione(Pair pair) {
        numSol++;
        System.out.println("=======");
        System.out.println("Soluzione numero: "+numSol);
        System.out.println(percorso);
        System.out.println("=======");
    }

    @Override
    protected List<Pair> puntiDiScelta() {
        return percorso;
    }

    @Override
    protected Collection<Pair> scelte(Pair pds) {
        Collection<Pair> scelte = new ArrayList<>();
        int k = a[pds.i()][pds.j()];
        if(k == 0) return scelte;
        //nord
        if(pds.i()-k >= 0){
            Pair scelta = new Pair(pds.i()-k, pds.j());
            scelte.add(scelta);
        }
        //sud
        if(k+pds.i() < righe){
            Pair scelta = new Pair(k+pds.i(), pds.j());
            scelte.add(scelta);
        }
        //est
        if(k+pds.j() < righe){
            Pair scelta = new Pair(pds.i(), pds.j()+k);
            scelte.add(scelta);
        }
        //ovest
        if(pds.j()-k >= 0){
            Pair scelta = new Pair(pds.i(), pds.j()-k);
            scelte.add(scelta);
        }
        System.out.println("scelte per "+pds+" "+scelte);
        return scelte;
    }

    public static void main(String[] args) throws IOException {
        int[][] a={
                {3,0,2,3},
                {2,4,2,1},
                {1,2,1,3},
                {1,1,1,2} };
        Path p = new Path(a);
        p.risolvi();
    }
}
