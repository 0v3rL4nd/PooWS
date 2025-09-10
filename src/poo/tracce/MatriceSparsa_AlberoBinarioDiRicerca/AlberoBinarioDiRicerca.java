package poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca;

import java.util.*;

class AlberoBinarioDiRicerca<T extends Comparable<T>> {
    private class Nodo {
        T valore;
        Nodo sinistro, destro;

        Nodo(T valore) {
            this.valore = valore;
        }
    }

    private Nodo radice;

    // Metodo per inserire elementi (per testing)
    public void inserisci(T valore) {
        radice = inserisciRicorsivo(radice, valore);
    }

    private Nodo inserisciRicorsivo(Nodo nodo, T valore) {
        if (nodo == null) {
            return new Nodo(valore);
        }

        if (valore.compareTo(nodo.valore) < 0) {
            nodo.sinistro = inserisciRicorsivo(nodo.sinistro, valore);
        } else if (valore.compareTo(nodo.valore) > 0) {
            nodo.destro = inserisciRicorsivo(nodo.destro, valore);
        }

        return nodo;
    }

    // Metodo altezza - misurata in numero di archi
    public int altezza() {
        return altezzaRicorsiva(radice);
    }

    private int altezzaRicorsiva(Nodo nodo) {
        if (nodo == null) {
            return -1; // altezza di albero vuoto = -1
        }

        int altezzaSinistra = altezzaRicorsiva(nodo.sinistro);
        int altezzaDestra = altezzaRicorsiva(nodo.destro);

        return 1 + Math.max(altezzaSinistra, altezzaDestra);
    }

    // Metodo frontiera - visita foglie da sinistra a destra
    public void frontiera(java.util.List<T> lista) {
        FrontieraRicorsiva(radice, lista);
    }

    private void FrontieraRicorsiva(Nodo nodo, java.util.List<T> lista) {
        if (nodo == null) {
            return;
        }

        // Se è una foglia, aggiungila alla lista
        if (nodo.sinistro == null && nodo.destro == null) {
            lista.add(nodo.valore);
            return;
        }

        // Visita prima sottoalbero sinistro, poi destro
        FrontieraRicorsiva(nodo.sinistro, lista);
        FrontieraRicorsiva(nodo.destro, lista);
    }

    // Metodo di test per verificare il funzionamento
    public static void testAlbero() {
        AlberoBinarioDiRicerca<Integer> albero = new AlberoBinarioDiRicerca<>();

        // Inserisci alcuni valori
        int[] valori = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        for (int val : valori) {
            albero.inserisci(val);
        }

        System.out.println("Altezza dell'albero: " + albero.altezza());

        List<Integer> foglie = new ArrayList<>();
        albero.frontiera(foglie);
        System.out.println("Foglie (da sinistra a destra): " + foglie);
    }
}
