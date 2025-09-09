package poo.tracce.Triple;

abstract class Backtracking {

    /**
     * Metodo principale che avvia il processo di backtracking.
     */
    public abstract void risolvi();

    /**
     * Verifica se la soluzione parziale corrente può essere estesa.
     * @param livello livello corrente nell'albero di ricerca
     * @return true se si può procedere, false altrimenti
     */
    protected abstract boolean assegnabile(int livello);

    /**
     * Assegna un valore al livello corrente.
     * @param livello livello corrente
     * @param valore valore da assegnare
     */
    protected abstract void assegna(int livello, int valore);

    /**
     * Rimuove l'assegnazione al livello corrente (backtrack).
     * @param livello livello da cui fare backtrack
     */
    protected abstract void deassegna(int livello);

    /**
     * Verifica se abbiamo raggiunto una soluzione completa.
     * @param livello livello corrente
     * @return true se è una soluzione completa
     */
    protected abstract boolean soluzione(int livello);

    /**
     * Gestisce una soluzione trovata.
     * @param livello livello della soluzione
     */
    protected abstract void usaSoluzione(int livello);

    /**
     * Restituisce il numero di possibili valori per un dato livello.
     * @param livello livello corrente
     * @return numero di possibili valori
     */
    protected abstract int numeroValori(int livello);

    /**
     * Restituisce il valore i-esimo possibile per il livello dato.
     * @param livello livello corrente
     * @param i indice del valore
     * @return valore i-esimo
     */
    protected abstract int valore(int livello, int i);

    /**
     * Implementazione ricorsiva del backtracking.
     * @param livello livello corrente nell'albero di ricerca
     */
    protected void bt(int livello) {
        if (soluzione(livello)) {
            usaSoluzione(livello);
        } else {
            for (int i = 0; i < numeroValori(livello); i++) {
                int val = valore(livello, i);
                if (assegnabile(livello)) {
                    assegna(livello, val);
                    bt(livello + 1);
                    deassegna(livello);
                }
            }
        }
    }
}