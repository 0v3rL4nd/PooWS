package poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca;

import java.util.*;

class MatriceSparsaLinked implements MatriceSparsa {
    private int righe, colonne;
    private List<LinkedList<Elemento>> rigaMap; // mappa per righe
    private List<LinkedList<Elemento>> colonnaMap; // mappa per colonne

    public MatriceSparsaLinked(int righe, int colonne) {
        this.righe = righe;
        this.colonne = colonne;

        rigaMap = new ArrayList<>(righe);
        colonnaMap = new ArrayList<>(colonne);

        for (int i = 0; i < righe; i++) {
            rigaMap.add(new LinkedList<>());
        }
        for (int j = 0; j < colonne; j++) {
            colonnaMap.add(new LinkedList<>());
        }
    }

    @Override
    public int getRighe() { return righe; }

    @Override
    public int getColonne() { return colonne; }

    @Override
    public void clear() {
        for (LinkedList<Elemento> riga : rigaMap) {
            riga.clear();
        }
        for (LinkedList<Elemento> colonna : colonnaMap) {
            colonna.clear();
        }
    }

    @Override
    public double get(int i, int j) {
        if (i < 0 || i >= righe || j < 0 || j >= colonne) {
            throw new IndexOutOfBoundsException();
        }

        for (Elemento e : rigaMap.get(i)) {
            if (e.getJ() == j) {
                return e.getV();
            }
        }
        return 0.0; // elemento non trovato = zero
    }

    @Override
    public int sizeRiga(int i) {
        return rigaMap.get(i).size();
    }

    @Override
    public int sizeColonna(int j) {
        return colonnaMap.get(j).size();
    }

    @Override
    public void set(int i, int j, int v) {
        set(i, j, (double) v);
    }

    @Override
    public void set(int i, int j, double v) {
        if (i < 0 || i >= righe || j < 0 || j >= colonne) {
            throw new IndexOutOfBoundsException();
        }

        // Rimuovi elemento esistente se presente
        rigaMap.get(i).removeIf(e -> e.getJ() == j);
        colonnaMap.get(j).removeIf(e -> e.getI() == i);

        // Aggiungi nuovo elemento se non zero
        if (v != 0.0) {
            Elemento elemento = new Elemento(i, j, v);
            rigaMap.get(i).add(elemento);
            colonnaMap.get(j).add(elemento);
        }
    }

    @Override
    public void set(Elemento e) {
        set(e.getI(), e.getJ(), e.getV());
    }

    @Override
    public boolean rigaVuota(int i) {
        return rigaMap.get(i).isEmpty();
    }

    @Override
    public boolean colonnaVuota(int j) {
        return colonnaMap.get(j).isEmpty();
    }

    @Override
    public Iterator<Elemento> rigaIterator(int i) {
        return rigaMap.get(i).iterator();
    }

    @Override
    public Iterator<Elemento> colonnaIterator(int j) {
        return colonnaMap.get(j).iterator();
    }

    @Override
    public MatriceSparsa crea() {
        return new MatriceSparsaLinked(righe, colonne);
    }

    @Override
    public MatriceSparsa add(MatriceSparsa m) {
        if (this.righe != m.getRighe() || this.colonne != m.getColonne()) {
            throw new IllegalArgumentException("Dimensioni incompatibili");
        }

        MatriceSparsa risultato = new MatriceSparsaLinked(righe, colonne);

        // Aggiungi elementi di questa matrice
        for (int i = 0; i < righe; i++) {
            for (Elemento e : rigaMap.get(i)) {
                risultato.set(e.getI(), e.getJ(), e.getV());
            }
        }

        // Aggiungi elementi dell'altra matrice
        for (int i = 0; i < righe; i++) {
            Iterator<Elemento> iter = m.rigaIterator(i);
            while (iter.hasNext()) {
                Elemento e = iter.next();
                double somma = risultato.get(e.getI(), e.getJ()) + e.getV();
                risultato.set(e.getI(), e.getJ(), somma);
            }
        }

        return risultato;
    }

    @Override
    public MatriceSparsa mul(MatriceSparsa m) {
        if (this.colonne != m.getRighe()) {
            throw new IllegalArgumentException("Dimensioni incompatibili per moltiplicazione");
        }

        MatriceSparsa risultato = new MatriceSparsaLinked(this.righe, m.getColonne());

        for (int i = 0; i < this.righe; i++) {
            for (int j = 0; j < m.getColonne(); j++) {
                double somma = 0.0;

                // Prodotto scalare riga i con colonna j
                Iterator<Elemento> rigaIter = this.rigaIterator(i);
                while (rigaIter.hasNext()) {
                    Elemento e1 = rigaIter.next();
                    double val2 = m.get(e1.getJ(), j);
                    somma += e1.getV() * val2;
                }

                if (somma != 0.0) {
                    risultato.set(i, j, somma);
                }
            }
        }

        return risultato;
    }

    @Override
    public boolean simmetrica() {
        if (righe != colonne) {
            return false; // deve essere quadrata
        }

        for (int i = 0; i < righe; i++) {
            for (Elemento e : rigaMap.get(i)) {
                if (get(e.getJ(), e.getI()) != e.getV()) {
                    return false;
                }
            }
        }
        return true;
    }
}