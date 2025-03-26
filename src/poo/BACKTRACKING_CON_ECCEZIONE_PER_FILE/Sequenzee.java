package BACKTRACKING_CON_ECCEZIONE_PER_FILE;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.*;

public class Sequenzee extends Backtracking<Integer, Integer> {
    private int n;
    private File f;
    private int[] sequenza;
    private int numSol;
    private long pos;

    public Sequenzee(int n, File f) throws IOException {
        if (n < 2) throw new IllegalArgumentException();
        this.n = n;
        this.sequenza = new int[n];
        this.f = new File(f.getAbsolutePath());
    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == n - 1;
    }

    @Override
    protected boolean assegnabile(Integer pds, Integer s) {
        //devono essere distinti
        for (int i = 0; i < pds; i++) {
            if (sequenza[i] == s) return false;
        }
        return sequenza[pds] == 0;
    }

    @Override
    protected void assegna(Integer ps, Integer s) {
        sequenza[ps] = s;
    }

    @Override
    protected void deassegna(Integer ps, Integer s) {
        sequenza[ps] = 0;
    }

    @Override
    protected void scriviSoluzione(Integer integer) throws IOException {
        int somma = 0;
        for (int i = 0; i < sequenza.length; i++) {
            somma += sequenza[i];
        }
        int vincolo = (n * ((n * n) + 1)) / 2;
        if (somma == vincolo) {
            numSol++;
            RandomAccessFile raf = new RandomAccessFile(f.getAbsolutePath(), "rw");
            raf.seek(pos);
            for (int i = 0; i < sequenza.length; i++) {
                raf.writeChars(sequenza[i] + "");
                pos += 2;
            }
            raf.writeChars(" ");
            pos += 2;
            raf.close();
            System.out.println("Numero soluzione = " + numSol);
            System.out.println(Arrays.toString(sequenza));
        }
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> puntiDiScelta = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            puntiDiScelta.add(i);
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte(Integer integer) {
        Collection<Integer> scelte = new ArrayList<>();
        for (int i = 1; i <= (n * n); i++) {
            scelte.add(i);
        }
        return scelte;
    }

    public static void main(String[] args) throws IOException {
        String path = "C:\\poo-file\\Sequenza\\Sequenza.txt";
        File f = new File(path);
        Sequenzee s = new Sequenzee(5, f);
        s.risolvi();
    }
}
