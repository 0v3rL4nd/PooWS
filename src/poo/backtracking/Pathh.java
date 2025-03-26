package poo.backtracking;

import java.util.*;

public class Pathh extends Backtracking<Coppia, Coppia> {
    private int[][] a;
    private List<Coppia> percorso = new LinkedList<>();
    private int numSol;

    public Pathh(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a[0].length) {
                throw new IllegalArgumentException();
            }
        }
        this.a = new int[a.length][a.length];
        Coppia o = new Coppia(0, 0);
        percorso.add(o);
        for (int i = 0; i < a.length; i++) {
            System.arraycopy(a[i], 0, this.a[i], 0, a.length);
        }
    }

    @Override
    protected boolean esisteSoluzione(Coppia coppia) {
       // System.out.println("Coppia c = "+coppia);
        return percorso.getLast().i() == a.length - 1 && percorso.getLast().j() == a.length - 1;
    }

    @Override
    protected boolean assegnabile(Coppia pds, Coppia s) {
        if (pds.i() == 0 && pds.j() == 0) return true;
        //System.out.println("Scelta considerata = "+s);
        //se non fa parte della diagonale
        if (s.i() == s.j() && s.i() != a.length-1) return false;
        //se fa già parte del percorso
        //System.out.println("Percorso = "+percorso);
        return !percorso.contains(s);
    }

    @Override
    protected void assegna(Coppia ps, Coppia s) {
        percorso.addLast(s);
      //  System.out.println("Assegna = " + percorso);
    }

    @Override
    protected void deassegna(Coppia ps, Coppia s) {
        percorso.removeLast();
    //   System.out.println("Deassegna " + percorso);
    }

    @Override
    protected void scriviSoluzione(Coppia coppia) {
        numSol++;
        System.out.println("Numero soluzione = " + numSol);
        System.out.println(percorso);
    }

    @Override
    protected List<Coppia> puntiDiScelta() {
        return  percorso;
    }

    @Override
    protected Collection<Coppia> scelte(Coppia pds) {
        Collection<Coppia> scelte = new ArrayList<>();
        int k = a[pds.i()][pds.j()];
        if (k == 0) {
            return scelte;
        }
        //nord
        if (pds.i() - k >= 0) {
            scelte.add(new Coppia(pds.i() - k, pds.j()));
        }
        //sud
        if (pds.i() + k < a.length) {
            scelte.add(new Coppia(pds.i() + k, pds.j()));
        }
        //est
        if (pds.j() + k < a.length) {
            scelte.add(new Coppia(pds.i(), pds.j() + k));
        }
        //ovest
        if (pds.j() - k >= 0) {
            scelte.add(new Coppia(pds.i(), pds.j() - k));
        }
        System.out.println("Scelte per " + pds + " " + scelte);
        return scelte;
    }

    public static void main(String[] args) {
        int[][] a = {{3, 0, 2, 3}, {2, 4, 2, 1}, {1, 2, 1, 3}, {1, 1, 1, 2}};
        Pathh p = new Pathh(a);
        p.risolvi();
    }
}
