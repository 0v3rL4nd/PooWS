package poo.tracce.Triple;


import java.util.*;

public class Triple extends Backtracking {
    private int[] a; // array di input
    private int x; // somma target
    private int[] b; // array per costruire le triple (3 elementi)
    private boolean[] usato; // tiene traccia degli elementi già utilizzati
    private ArrayList<int[]> soluzioni; // collezione delle soluzioni trovate
    private int livelloCorrente;

    /**
     * Costruttore della classe Triple.
     * @param a array di interi unici (deve avere N > 3 elementi)
     * @param x valore target per la somma delle triple
     * @throws IllegalArgumentException se l'array non soddisfa le precondizioni
     */
    public Triple(int[] a, int x) {
        // Verifica delle precondizioni
        if (a == null) {
            throw new IllegalArgumentException("L'array non può essere null");
        }

        if (a.length <= 3) {
            throw new IllegalArgumentException("L'array deve contenere più di 3 elementi (N > 3)");
        }

        // Verifica che tutti gli elementi siano unici
        Set<Integer> elementi = new HashSet<>();
        for (int elemento : a) {
            if (!elementi.add(elemento)) {
                throw new IllegalArgumentException("L'array deve contenere elementi unici");
            }
        }

        this.a = a.clone(); // copia difensiva
        this.x = x;
        this.b = new int[3]; // array per costruire le triple
        this.usato = new boolean[a.length]; // inizialmente tutti false
        this.soluzioni = new ArrayList<>();
        this.livelloCorrente = 0;
    }

    /**
     * Avvia la ricerca di tutte le triple.
     */
    @Override
    public void risolvi() {
        soluzioni.clear();
        Arrays.fill(usato, false);
        livelloCorrente = 0;
        bt(0);
        stampaSoluzioni();
    }

    /**
     * Verifica se è possibile assegnare un valore al livello corrente.
     */
    @Override
    protected boolean assegnabile(int livello) {
        if (livello >= 3) return false;

        // Se stiamo al terzo elemento (livello 2), verifichiamo che la somma sia corretta
        if (livello == 2) {
            int sommaAttuale = b[0] + b[1];
            int elementoMancante = x - sommaAttuale;

            // Cerchiamo se l'elemento mancante esiste nell'array e non è già stato usato
            for (int i = 0; i < a.length; i++) {
                if (a[i] == elementoMancante && !usato[i]) {
                    return true;
                }
            }
            return false;
        }

        return true;
    }

    /**
     * Assegna un valore al livello corrente.
     */
    @Override
    protected void assegna(int livello, int valore) {
        if (livello < 3) {
            b[livello] = valore;

            // Trova l'indice del valore nell'array originale e marcalo come usato
            for (int i = 0; i < a.length; i++) {
                if (a[i] == valore && !usato[i]) {
                    usato[i] = true;
                    break;
                }
            }
        }
    }

    /**
     * Rimuove l'assegnazione al livello corrente (backtrack).
     */
    @Override
    protected void deassegna(int livello) {
        if (livello < 3) {
            int valore = b[livello];

            // Trova l'indice del valore nell'array originale e marcalo come non usato
            for (int i = 0; i < a.length; i++) {
                if (a[i] == valore && usato[i]) {
                    usato[i] = false;
                    break;
                }
            }

            b[livello] = 0; // reset del valore
        }
    }

    /**
     * Verifica se abbiamo raggiunto una soluzione completa.
     */
    @Override
    protected boolean soluzione(int livello) {
        return livello == 3 && (b[0] + b[1] + b[2] == x);
    }

    /**
     * Gestisce una soluzione trovata.
     */
    @Override
    protected void usaSoluzione(int livello) {
        // Crea una copia della tripla e la aggiunge alle soluzioni
        int[] tripla = {b[0], b[1], b[2]};

        // Ordina la tripla per evitare duplicati (es: [1,2,3] e [3,1,2] sono la stessa tripla)
        Arrays.sort(tripla);

        // Verifica se questa tripla non è già stata trovata
        boolean giaPresente = false;
        for (int[] soluzione : soluzioni) {
            if (Arrays.equals(soluzione, tripla)) {
                giaPresente = true;
                break;
            }
        }

        if (!giaPresente) {
            soluzioni.add(tripla);
        }
    }

    /**
     * Restituisce il numero di possibili valori per un dato livello.
     */
    @Override
    protected int numeroValori(int livello) {
        if (livello >= 3) return 0;

        if (livello == 2) {
            // Per il terzo elemento, calcoliamo quello che manca
            int sommaAttuale = b[0] + b[1];
            int elementoMancante = x - sommaAttuale;

            // Verifichiamo se l'elemento mancante esiste ed è disponibile
            for (int i = 0; i < a.length; i++) {
                if (a[i] == elementoMancante && !usato[i]) {
                    return 1;
                }
            }
            return 0;
        } else {
            // Per i primi due livelli, consideriamo tutti gli elementi non usati
            int count = 0;
            for (boolean u : usato) {
                if (!u) count++;
            }
            return count;
        }
    }

    /**
     * Restituisce il valore i-esimo possibile per il livello dato.
     */
    @Override
    protected int valore(int livello, int i) {
        if (livello >= 3) return 0;

        if (livello == 2) {
            // Per il terzo elemento, restituiamo quello che manca
            int sommaAttuale = b[0] + b[1];
            int elementoMancante = x - sommaAttuale;
            return elementoMancante;
        } else {
            // Per i primi due livelli, restituiamo l'i-esimo elemento non usato
            int count = 0;
            for (int j = 0; j < a.length; j++) {
                if (!usato[j]) {
                    if (count == i) {
                        return a[j];
                    }
                    count++;
                }
            }
        }
        return 0;
    }

    /**
     * Stampa tutte le soluzioni trovate.
     */
    private void stampaSoluzioni() {
        System.out.println("Triple trovate con somma = " + x + ":");
        if (soluzioni.isEmpty()) {
            System.out.println("Nessuna tripla trovata.");
        } else {
            for (int i = 0; i < soluzioni.size(); i++) {
                int[] tripla = soluzioni.get(i);
                System.out.println("Tripla " + (i + 1) + ": [" +
                        tripla[0] + ", " + tripla[1] + ", " + tripla[2] + "]");
            }
            System.out.println("Totale triple trovate: " + soluzioni.size());
        }
    }

    /**
     * Restituisce la lista delle soluzioni trovate.
     */
    public ArrayList<int[]> getSoluzioni() {
        return new ArrayList<>(soluzioni);
    }

    /**
     * Metodo di test.
     */
    public static void main(String[] args) {
        System.out.println("Test della classe Triple:");
        System.out.println("========================");

        // Test 1: Array con triple valide
        int[] array1 = {1, 2, 3, 4, 5, 6};
        int target1 = 9;

        System.out.println("Test 1 - Array: " + Arrays.toString(array1) + ", Target: " + target1);
        try {
            Triple triple1 = new Triple(array1, target1);
            triple1.risolvi();
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }

        System.out.println();

        // Test 2: Array con più elementi
        int[] array2 = {-1, 0, 1, 2, -1, -4, 3, 5};
        int target2 = 0;

        System.out.println("Test 2 - Array: " + Arrays.toString(array2) + ", Target: " + target2);
        try {
            Triple triple2 = new Triple(array2, target2);
            triple2.risolvi();
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }

        System.out.println();

        // Test 3: Array troppo piccolo (deve sollevare eccezione)
        int[] array3 = {1, 2, 3};
        int target3 = 6;

        System.out.println("Test 3 - Array troppo piccolo: " + Arrays.toString(array3));
        try {
            Triple triple3 = new Triple(array3, target3);
            triple3.risolvi();
        } catch (Exception e) {
            System.err.println("Errore atteso: " + e.getMessage());
        }

        System.out.println();

        // Test 4: Array con elementi duplicati (deve sollevare eccezione)
        int[] array4 = {1, 2, 3, 2, 5};
        int target4 = 8;

        System.out.println("Test 4 - Array con duplicati: " + Arrays.toString(array4));
        try {
            Triple triple4 = new Triple(array4, target4);
            triple4.risolvi();
        } catch (Exception e) {
            System.err.println("Errore atteso: " + e.getMessage());
        }
    }
}