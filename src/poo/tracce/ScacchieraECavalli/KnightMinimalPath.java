package poo.tracce.ScacchieraECavalli;

import poo.backtracking.Backtracking;
import java.util.*;

/**
 * Classe per trovare il percorso minimo di un cavallo degli scacchi
 * da una posizione di partenza a una di destinazione usando backtracking
 */
public class KnightMinimalPath extends Backtracking<KnightMinimalPath.Posizione, KnightMinimalPath.Posizione> {

    private int n; // dimensione della scacchiera (n x n)
    private Posizione partenza;
    private Posizione destinazione;
    private List<Posizione> path; // percorso corrente
    private boolean[][] visitato; // celle già visitate
    private List<Posizione> percorsoMinimo; // miglior percorso trovato
    private int lunghezzaMinima; // lunghezza del percorso minimo

    // Le 8 possibili mosse del cavallo
    private static final int[][] MOSSE_CAVALLO = {
            {1, 2}, {-1, 2}, {1, -2}, {-1, -2},
            {2, 1}, {2, -1}, {-2, -1}, {-2, 1}
    };

    /**
     * Costruttore della classe
     * @param n ordine della scacchiera
     * @param iStart riga di partenza
     * @param jStart colonna di partenza
     * @param iDest riga di destinazione
     * @param jDest colonna di destinazione
     */
    public KnightMinimalPath(int n, int iStart, int jStart, int iDest, int jDest) {
        this.n = n;
        this.partenza = new Posizione(iStart, jStart);
        this.destinazione = new Posizione(iDest, jDest);
        this.path = new ArrayList<>();
        this.visitato = new boolean[n][n];
        this.percorsoMinimo = new ArrayList<>();
        this.lunghezzaMinima = Integer.MAX_VALUE;

        // Inizializza il percorso con la posizione di partenza
        this.path.add(partenza);
        this.visitato[iStart][jStart] = true;
    }

    /**
     * Classe per rappresentare una posizione sulla scacchiera
     */
    public static class Posizione {
        public final int riga;
        public final int colonna;

        public Posizione(int riga, int colonna) {
            this.riga = riga;
            this.colonna = colonna;
        }

        @Override
        public String toString() {
            return "<" + riga + "," + colonna + ">";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Posizione posizione = (Posizione) obj;
            return riga == posizione.riga && colonna == posizione.colonna;
        }

        @Override
        public int hashCode() {
            return Objects.hash(riga, colonna);
        }

        /**
         * Verifica se la posizione è valida sulla scacchiera
         */
        public boolean isValida(int n) {
            return riga >= 0 && riga < n && colonna >= 0 && colonna < n;
        }
    }

    /**
     * Restituisce i punti di scelta (posizioni raggiungibili dalla posizione corrente)
     */
    @Override
    protected List<Posizione> puntiDiScelta() {
        if (path.isEmpty()) {
            // Caso iniziale: solo la posizione di partenza
            List<Posizione> iniziale = new ArrayList<>();
            iniziale.add(partenza);
            return iniziale;
        }

        // Restituisce le posizioni raggiungibili dall'ultima posizione del percorso
        List<Posizione> punti = new ArrayList<>();
        Posizione ultimaPosizione = path.get(path.size() - 1);

        for (int[] mossa : MOSSE_CAVALLO) {
            int nuovaRiga = ultimaPosizione.riga + mossa[0];
            int nuovaColonna = ultimaPosizione.colonna + mossa[1];
            Posizione nuovaPosizione = new Posizione(nuovaRiga, nuovaColonna);

            if (nuovaPosizione.isValida(n) && !visitato[nuovaRiga][nuovaColonna]) {
                punti.add(nuovaPosizione);
            }
        }

        return punti;
    }

    /**
     * Restituisce le scelte possibili per un punto (in questo caso, solo la posizione stessa)
     */
    @Override
    protected Collection<Posizione> scelte(Posizione p) {
        return Collections.singletonList(p);
    }

    /**
     * Verifica se una posizione può essere assegnata
     */
    @Override
    protected boolean assegnabile(Posizione p, Posizione s) {
        // Verifica se la posizione è valida e non già visitata
        if (!s.isValida(n) || visitato[s.riga][s.colonna]) {
            return false;
        }

        // Ottimizzazione: se il percorso corrente è già più lungo del minimo trovato, non continuare
        if (path.size() >= lunghezzaMinima) {
            return false;
        }

        return true;
    }

    /**
     * Assegna una posizione al percorso
     */
    @Override
    protected void assegna(Posizione p, Posizione s) {
        if (!path.contains(s)) {
            path.add(s);
        }
        visitato[s.riga][s.colonna] = true;
    }

    /**
     * Rimuove una posizione dal percorso
     */
    @Override
    protected void deassegna(Posizione p, Posizione s) {
        if (path.contains(s)) {
            path.remove(s);
        }
        visitato[s.riga][s.colonna] = false;
    }

    /**
     * Verifica se è stata trovata una soluzione
     */
    @Override
    protected boolean esisteSoluzione(Posizione p) {
        if (path.isEmpty()) {
            return false;
        }

        Posizione ultimaPosizione = path.get(path.size() - 1);
        return ultimaPosizione.equals(destinazione);
    }

    /**
     * Non fermare alla prima soluzione, cercare il percorso minimo
     */
    @Override
    protected boolean ultimaSoluzione(Posizione p) {
        return false; // Continua a cercare per trovare il percorso minimo
    }

    /**
     * Salva la soluzione se è migliore di quella corrente
     */
    @Override
    protected void scriviSoluzione(Posizione p) {
        if (path.size() < lunghezzaMinima) {
            lunghezzaMinima = path.size();
            percorsoMinimo = new ArrayList<>(path);
            System.out.println("Nuovo percorso minimo trovato (lunghezza " + lunghezzaMinima + "): " + this);
        }
    }

    /**
     * Trova il percorso minimo
     */
    public void trovaPercorsoMinimo() {
        System.out.println("Ricerca del percorso minimo da " + partenza + " a " + destinazione + "...\n");
        long startTime = System.currentTimeMillis();

        risolvi();

        long endTime = System.currentTimeMillis();

        if (percorsoMinimo.isEmpty()) {
            System.out.println("Nessun percorso trovato da " + partenza + " a " + destinazione);
        } else {
            System.out.println("\n=== RISULTATO FINALE ===");
            System.out.println("Percorso minimo (lunghezza " + lunghezzaMinima + "):");
            System.out.println(percorsoMinimoToString());
            System.out.println("Tempo di esecuzione: " + (endTime - startTime) + " ms");
        }
    }

    /**
     * Restituisce il percorso minimo come stringa
     */
    public String percorsoMinimoToString() {
        if (percorsoMinimo.isEmpty()) {
            return "Nessun percorso trovato";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < percorsoMinimo.size(); i++) {
            sb.append(percorsoMinimo.get(i));
            if (i < percorsoMinimo.size() - 1) {
                sb.append("");
            }
        }
        return sb.toString();
    }

    /**
     * ToString per il percorso corrente
     */
    @Override
    public String toString() {
        if (path.isEmpty()) {
            return "Percorso vuoto";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i < path.size() - 1) {
                sb.append("");
            }
        }
        return sb.toString();
    }

    /**
     * Stampa la scacchiera con il percorso evidenziato
     */
    public void stampaScacchiera() {
        if (percorsoMinimo.isEmpty()) {
            System.out.println("Nessun percorso da visualizzare");
            return;
        }

        System.out.println("\nScacchiera " + n + "x" + n + " con percorso minimo:");
        System.out.print("   ");
        for (int j = 0; j < n; j++) {
            System.out.print(String.format("%3d", j));
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(String.format("%2d ", i));
            for (int j = 0; j < n; j++) {
                int passo = trovaPassoNelPercorso(i, j);
                if (passo >= 0) {
                    System.out.print(String.format("%3d", passo));
                } else {
                    System.out.print("  .");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Trova il numero del passo per una data posizione nel percorso minimo
     */
    private int trovaPassoNelPercorso(int riga, int colonna) {
        for (int i = 0; i < percorsoMinimo.size(); i++) {
            Posizione pos = percorsoMinimo.get(i);
            if (pos.riga == riga && pos.colonna == colonna) {
                return i + 1; // Passo numerato da 1
            }
        }
        return -1; // Non trovato
    }

    /**
     * Resetta lo stato per una nuova ricerca
     */
    public void reset() {
        path.clear();
        percorsoMinimo.clear();
        lunghezzaMinima = Integer.MAX_VALUE;

        // Pulisce la matrice visitato
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visitato[i][j] = false;
            }
        }

        // Reinizializza con la posizione di partenza
        path.add(partenza);
        visitato[partenza.riga][partenza.colonna] = true;
    }

    /**
     * Main per testare la classe
     */
    public static void main(String[] args) {
        // Test con le coordinate specificate nell'esercizio
        System.out.println("=== KNIGHT MINIMAL PATH ===");
        System.out.println("Scacchiera 8x8");
        System.out.println("Partenza: <0,5>");
        System.out.println("Destinazione: <6,0>");
        System.out.println();

        KnightMinimalPath knight = new KnightMinimalPath(8, 0, 5, 6, 0);
        knight.trovaPercorsoMinimo();
        knight.stampaScacchiera();

        System.out.println("\n" + "=".repeat(50));

        // Test aggiuntivo con percorso più breve
        System.out.println("Test aggiuntivo: da <0,0> a <2,2>");
        KnightMinimalPath knight2 = new KnightMinimalPath(8, 0, 0, 2, 2);
        knight2.trovaPercorsoMinimo();
        knight2.stampaScacchiera();
    }
}