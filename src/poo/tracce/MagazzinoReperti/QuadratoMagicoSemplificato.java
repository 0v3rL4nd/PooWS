package poo.tracce.MagazzinoReperti;


import poo.backtracking.Backtracking;
import java.util.*;

/**
 * Classe per risolvere il problema del Quadrato Magico usando backtracking
 * Un quadrato magico è una matrice quadrata dove la somma di ogni riga,
 * colonna e diagonale è uguale alla "costante magica"
 */
public class QuadratoMagicoSemplificato extends Backtracking<QuadratoMagicoSemplificato.Indice, Integer> {

    private int[][] matrice;
    private int ordine;
    private int costanteMagica;
    private Set<Integer> numerUsati;
    private boolean soluzioneStampata = false;

    /**
     * Costruttore che inizializza la matrice e calcola la costante magica
     * @param ordine l'ordine della matrice (N x N)
     */
    public QuadratoMagicoSemplificato(int ordine) {
        this.ordine = ordine;
        this.matrice = new int[ordine][ordine];
        this.costanteMagica = ordine * (ordine * ordine + 1) / 2;
        this.numerUsati = new HashSet<>();
    }

    /**
     * Classe per rappresentare le coordinate (riga, colonna) di un elemento
     */
    public static class Indice {
        public final int riga;
        public final int colonna;

        public Indice(int riga, int colonna) {
            this.riga = riga;
            this.colonna = colonna;
        }

        @Override
        public String toString() {
            return "(" + riga + "," + colonna + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Indice indice = (Indice) obj;
            return riga == indice.riga && colonna == indice.colonna;
        }

        @Override
        public int hashCode() {
            return Objects.hash(riga, colonna);
        }
    }

    /**
     * Restituisce la lista di tutti i punti di scelta (tutte le celle della matrice)
     */
    @Override
    protected List<Indice> puntiDiScelta() {
        List<Indice> punti = new ArrayList<>();
        for (int i = 0; i < ordine; i++) {
            for (int j = 0; j < ordine; j++) {
                punti.add(new Indice(i, j));
            }
        }
        return punti;
    }

    /**
     * Restituisce le scelte possibili per un dato punto (numeri da 1 a N²)
     */
    @Override
    protected Collection<Integer> scelte(Indice p) {
        List<Integer> sceltePossibili = new ArrayList<>();
        for (int i = 1; i <= ordine * ordine; i++) {
            sceltePossibili.add(i);
        }
        return sceltePossibili;
    }

    /**
     * Verifica se un numero può essere assegnato a una posizione
     */
    @Override
    protected boolean assegnabile(Indice p, Integer numero) {
        if (numerUsati.contains(numero)) {
            return false;
        }

        // Salva il valore corrente
        int valorePrecedente = matrice[p.riga][p.colonna];

        // Prova l'assegnazione temporanea
        matrice[p.riga][p.colonna] = numero;
        boolean risultato = isAssegnamentoValido(p);

        // Ripristina il valore precedente
        matrice[p.riga][p.colonna] = valorePrecedente;

        return risultato;
    }

    /**
     * Verifica se l'assegnamento corrente è valido
     */
    private boolean isAssegnamentoValido(Indice p) {
        // Controlla la riga se è completa
        if (isRigaCompleta(p.riga)) {
            int sommaRiga = calcolaSommaRiga(p.riga);
            if (sommaRiga != costanteMagica) {
                return false;
            }
        }

        // Controlla la colonna se è completa
        if (isColonnaCompleta(p.colonna)) {
            int sommaColonna = calcolaSommaColonna(p.colonna);
            if (sommaColonna != costanteMagica) {
                return false;
            }
        }

        // Controlla la diagonale principale se è completa
        if (p.riga == p.colonna && isDiagonalePrincipaleCompleta()) {
            int sommaDiagonale = calcolaSommaDiagonalePrincipale();
            if (sommaDiagonale != costanteMagica) {
                return false;
            }
        }

        // Controlla la diagonale secondaria se è completa
        if (p.riga + p.colonna == ordine - 1 && isDiagonaleSecondariaCcompleta()) {
            int sommaDiagonale = calcolaSommaDiagonaleSecondaria();
            if (sommaDiagonale != costanteMagica) {
                return false;
            }
        }

        return true;
    }

    /**
     * Assegna un numero a una posizione
     */
    @Override
    protected void assegna(Indice p, Integer numero) {
        matrice[p.riga][p.colonna] = numero;
        numerUsati.add(numero);
    }

    /**
     * Rimuove l'assegnazione di un numero da una posizione
     */
    @Override
    protected void deassegna(Indice p, Integer numero) {
        matrice[p.riga][p.colonna] = 0;
        numerUsati.remove(numero);
    }

    /**
     * Verifica se esiste una soluzione completa
     */
    @Override
    protected boolean esisteSoluzione(Indice p) {
        // Controlla se tutte le celle sono riempite
        for (int i = 0; i < ordine; i++) {
            for (int j = 0; j < ordine; j++) {
                if (matrice[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Indica se fermarsi alla prima soluzione
     */
    @Override
    protected boolean ultimaSoluzione(Indice p) {
        return soluzioneStampata; // Ferma dopo aver trovato la prima soluzione
    }

    /**
     * Scrive la soluzione trovata
     */
    @Override
    protected void scriviSoluzione(Indice p) {
        if (!soluzioneStampata) {
            System.out.println("Soluzione trovata:");
            stampaMatrice();
            soluzioneStampata = true;
        }
    }

    // Metodi di utilità

    private boolean isRigaCompleta(int riga) {
        for (int j = 0; j < ordine; j++) {
            if (matrice[riga][j] == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isColonnaCompleta(int colonna) {
        for (int i = 0; i < ordine; i++) {
            if (matrice[i][colonna] == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalePrincipaleCompleta() {
        for (int i = 0; i < ordine; i++) {
            if (matrice[i][i] == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonaleSecondariaCcompleta() {
        for (int i = 0; i < ordine; i++) {
            if (matrice[i][ordine - 1 - i] == 0) {
                return false;
            }
        }
        return true;
    }

    private int calcolaSommaRiga(int riga) {
        int somma = 0;
        for (int j = 0; j < ordine; j++) {
            somma += matrice[riga][j];
        }
        return somma;
    }

    private int calcolaSommaColonna(int colonna) {
        int somma = 0;
        for (int i = 0; i < ordine; i++) {
            somma += matrice[i][colonna];
        }
        return somma;
    }

    private int calcolaSommaDiagonalePrincipale() {
        int somma = 0;
        for (int i = 0; i < ordine; i++) {
            somma += matrice[i][i];
        }
        return somma;
    }

    private int calcolaSommaDiagonaleSecondaria() {
        int somma = 0;
        for (int i = 0; i < ordine; i++) {
            somma += matrice[i][ordine - 1 - i];
        }
        return somma;
    }

    /**
     * Popola la matrice per colonne con i numeri da 1 a N²
     */
    public void popolaMatricePerColonne() {
        int numero = 1;
        for (int j = 0; j < ordine; j++) {
            for (int i = 0; i < ordine; i++) {
                matrice[i][j] = numero++;
            }
        }
    }

    /**
     * Stampa la matrice corrente
     */
    public void stampaMatrice() {
        for (int i = 0; i < ordine; i++) {
            System.out.print("[");
            for (int j = 0; j < ordine; j++) {
                System.out.print(String.format("%2d", matrice[i][j]));
                if (j < ordine - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println("]");
        }
        System.out.println("Costante magica: " + costanteMagica);
        System.out.println();
    }

    /**
     * Resetta la matrice per una nuova risoluzione
     */
    public void reset() {
        for (int i = 0; i < ordine; i++) {
            for (int j = 0; j < ordine; j++) {
                matrice[i][j] = 0;
            }
        }
        numerUsati.clear();
        soluzioneStampata = false;
    }

    /**
     * Applicazione principale per testare la classe
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci l'ordine della matrice (3 o 4): ");
        int ordine = scanner.nextInt();

        if (ordine < 3 || ordine > 4) {
            System.out.println("Ordine non supportato. Usando ordine 3.");
            ordine = 3;
        }

        QuadratoMagicoSemplificato quadrato = new QuadratoMagicoSemplificato(ordine);

        System.out.println("\nMatrice iniziale popolata per colonne:");
        quadrato.popolaMatricePerColonne();
        quadrato.stampaMatrice();

        // Reset della matrice per la risoluzione
        quadrato.reset();

        System.out.println("Risoluzione del quadrato magico...");
        long startTime = System.currentTimeMillis();

        try {
            quadrato.risolvi();
            long endTime = System.currentTimeMillis();

            if (!quadrato.soluzioneStampata) {
                System.out.println("Nessuna soluzione trovata.");
            } else {
                System.out.println("Tempo di esecuzione: " + (endTime - startTime) + " ms");
            }
        } catch (Exception e) {
            System.out.println("Errore durante la risoluzione: " + e.getMessage());
        }

        scanner.close();
    }
}