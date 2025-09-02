package poo.tracce.NegozioScarpe;

import java.util.*;

/* Il presente ADT è finalizzato alla gestione di un negozio di
 * Scarpe. L’ADT deve essere in grado di gestire modelli di scarpe
 * e misure associate. */
public interface NegozioScarpe {
    /* Aggiunge una nuova scarpa, di una data misura, nel negozio.
     * Una volta aggiunta, la scarpa può essere venduta */
    void aggiungi(String modelloScarpa, int misura);

    /* Vende una specifica scarpa di una specifica misura
     * Solleva l’eccezione se la scarpa non è presente */
    boolean vendi(String modelloScarpa, int misura)
            throws IllegalArgumentException;

    /* Restituisce l’insieme delle misure disponibili di un modello
     * specifico */
    Set<String> misureDisponibili(String modelloScarpa);

    /* Restituisce l’insieme ordinato delle scarpe disponibili */
    Set<String> scarpeDisponibili();

    /* Restituisce TRUE con scarpa (modello e misura) disponibile,
     * FALSE altrimenti */
    boolean eScarpaDisponibile(String modelloScarpa, int misura);

    /* Restituisce un iteratore ordinato delle scarpe disponibili data
     * una misura */
    Iterator<String> scarpeDisponibili(int misura);

}
