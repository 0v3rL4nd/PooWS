package poo.backtracking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CombinazioniDiBit extends Backtracking<Integer,Integer>{
    private int n ; // la lunghezza di bit
    private int [] array ; // le combinazioni da creare

    //scelte: gli indici dell'array
    //punti di scelte: 0 e 1


    public CombinazioniDiBit(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        array = new int [n]; // tutti 0 per default
    }

    @Override
    protected boolean assegnabile(Integer punto, Integer scelta) {
        //in questo problema non abbiamo vincoli
        return true;
    }

    @Override
    protected void assegna(Integer p, Integer scelta) {
        array[p] = scelta;
    }

    @Override
    protected void deassegna(Integer p, Integer scelta) {

    }

    @Override
    protected void scriviSoluzione(Integer p) {
      //  System.out.println(java.util.Arrays.toString(array));
        for (int i = 0; i < n; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> ps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ps.add(i);
        }
        return ps;
    }

    @Override
    protected Collection<Integer> scelte(Integer integer) {
        //voglio le scelte possibili per quel punto di scelta passato
        List<Integer> s = new ArrayList<>();
        s.add(0);
        s.add(1);
        return s;
    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == n-1;
    }

    public static void main(String[] args) throws IOException {
        CombinazioniDiBit c = new CombinazioniDiBit(4);
        c.risolvi();
    }
}
