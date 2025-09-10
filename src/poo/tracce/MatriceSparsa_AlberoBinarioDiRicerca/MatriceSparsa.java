package poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca;

import java.util.Iterator;

public interface MatriceSparsa {
    int getRighe();
    int getColonne();
    void clear();
    double get(int i, int j);
    int sizeRiga(int i);
    int sizeColonna(int j);
    void set(int i, int j, int v);
    void set(int i, int j, double v);
    void set(Elemento e);
    boolean rigaVuota(int i);
    boolean colonnaVuota(int j);
    Iterator<Elemento> rigaIterator(int i);
    Iterator<Elemento> colonnaIterator(int j);
    MatriceSparsa crea();
    MatriceSparsa add(MatriceSparsa m);
    MatriceSparsa mul(MatriceSparsa m);
    boolean simmetrica();
}