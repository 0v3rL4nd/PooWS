package poo.BACKTRACKING_CON_ECCEZIONE_PER_FILE;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Sequenze extends Backtracking<Integer,Integer> {
    private int numSol;
    private File f;
    private int n;
    private List<Integer> sequenza = new ArrayList<>(n);
    public Sequenze(int n/*File f*/) throws IOException {
        if(n < 3){
            throw new IllegalArgumentException("N > 2");
        }
        this.n = n;
        /*if(!f.exists()) f.createNewFile();*/
        //this.f = new File(f.getAbsolutePath());
        for (int i = 1; i <=n ; i++) {
            sequenza.add(i);
        }
    }
    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == n-1;
    }

    @Override
    protected boolean assegnabile(Integer pds, Integer s) {
        Collection<Integer> scelte = scelte(pds);
        int vincolo = (n*((n*n)+1))/2;
        int sum = 0;
        for (int i = 0; i < sequenza.size(); i++) {
            sum += sequenza.get(i);
        }
        return sum+s == vincolo;

    }

    @Override
    protected void assegna(Integer ps, Integer s) {
        sequenza.set(ps,s);
    }

    @Override
    protected void deassegna(Integer ps, Integer s) {

    }

    @Override
    protected void scriviSoluzione(Integer integer) {
        numSol++;
        int sum = 0;
        for (int i = 0; i < sequenza.size(); i++) {
            sum += sequenza.get(i);
        }
        System.out.println("Numero Soluzione: "+numSol);
        System.out.println(sequenza+"   somma = "+sum);
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> pds = new ArrayList<>();
        for (int i = 1; i <=n; i++) {
            pds.add(i);
        }
        return pds;
    }

    @Override
    protected Collection<Integer> scelte(Integer integer) {
        Collection<Integer> scelte = new HashSet<>();
        for (int i = 1; i < n*n; i++) {
            scelte.add(i);
        }
        return scelte;
    }

    public static void main(String[] args) throws IOException {
        Sequenze s = new Sequenze(3);
        s.risolvi();
    }
}
