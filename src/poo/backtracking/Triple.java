package poo.backtracking;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Triple extends Backtracking<Integer, Integer> {
    private int[] a;
    private int x;
    private int numSol;
    private int[] b;

    ArrayList<int[]> collezione = new ArrayList<>();

    public Triple(int[] a, int x) {
        if (!unici(a) || a.length < 4) throw new IllegalArgumentException();
        this.a = new int[a.length];
        this.x = x;
        this.b = new int[3];
        for (int i = 0; i < a.length; i++) {
            this.a[i] = a[i];
        }
    }

    private boolean unici(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int el = a[i];
            for (int j = 0; j < a.length; j++) {
                if (i != j) {
                    if (el == a[j]) return false;
                }
            }
        }
        return true;
    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == b.length - 1;
    }

    @Override
    protected boolean assegnabile(Integer pds, Integer s) {
        int somma = 0;
        for (int i = 0; i < pds; i++) {
            if (b[i] == s) return false;
            somma += b[i];
        }
        if (somma + s > x) {
            //   System.out.println("Qui con somma "+somma+" + "+s);
            return false;
        }
        //System.out.println("Qui con SOMMA "+somma+" + "+s);
        return b[pds] == 0;
    }

    @Override
    protected void assegna(Integer ps, Integer s) {
        b[ps] = s;
        //System.out.println("assegna ps= "+ps+" s = "+s+" "+Arrays.toString(b));
    }

    @Override
    protected void deassegna(Integer ps, Integer s) {
        // System.out.println("deassegna ps= "+ps+" s = "+s+" "+Arrays.toString(b));
        b[ps] = 0;
        // System.out.println("DEassegna ps= "+ps+" s = "+s+" "+Arrays.toString(b));
    }

    @Override
    protected void scriviSoluzione(Integer integer) {
        int somma = 0;
        for (int i = 0; i < b.length; i++) {
            somma += b[i];
        }
        //System.out.println("Somma "+ somma+" "+ Arrays.toString(b)+" "+collezione.contains(b));
        if (somma == x) {
            //System.out.println("Quindi entro qua");
            numSol++;
            collezione.add(b);
            System.out.println("Soluzione " + numSol);
            System.out.println(Arrays.toString(b));
        }
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> puntiDiScelta = new ArrayList<>();
        for (int i = 0; i < b.length; i++) {
            puntiDiScelta.add(i);
        }
        return puntiDiScelta;
    }

    @Override
    protected Collection<Integer> scelte(Integer integer) {
        Collection<Integer> scelte = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            scelte.add(a[i]);
        }
        return scelte;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Triple t = new Triple(a, 12);
        t.risolvi();
    }
}
