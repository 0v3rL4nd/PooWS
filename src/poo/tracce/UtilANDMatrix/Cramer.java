package poo.tracce.UtilANDMatrix;

import poo.util.Matrici;

public class Cramer extends Sistema {

    /**
     * Costruttore che richiama il costruttore della classe padre.
     */
    public Cramer(double[][] coefficienti, double[] terminiNoti) {
        super(coefficienti, terminiNoti);
    }

    /**
     * Risolve il sistema usando la regola di Cramer.
     * La regola di Cramer: x[j] = det(A^j) / det(A)
     * dove A^j è la matrice A con la j-esima colonna sostituita dal vettore Y.
     *
     * @return array con le soluzioni del sistema
     * @throws IllegalStateException se il determinante è zero (sistema non risolvibile con Cramer)
     */
    public double[] risolvi() {
        // Calcoliamo il determinante della matrice dei coefficienti
        double detA = Matrici.determinante(coefficienti);

        // Se il determinante è zero, il sistema non è risolvibile con Cramer
        if (Math.abs(detA) < 1e-10) { // Usiamo una tolleranza per errori numerici
            throw new IllegalStateException("Il determinante è zero: sistema non risolvibile con la regola di Cramer");
        }

        double[] soluzione = new double[n];

        // Per ogni incognita x[j]
        for (int j = 0; j < n; j++) {
            // Sostituiamo la colonna j con il vettore dei termini noti
            sostituisciColonna(j, terminiNoti);

            // Calcoliamo il determinante della matrice modificata
            double detAj = Matrici.determinante(coefficienti);

            // Applichiamo la regola di Cramer
            soluzione[j] = detAj / detA;

            // Ripristiniamo la colonna originale
            ripristinaColonna(j);
        }

        return soluzione;
    }

    /**
     * Sostituisce la colonna j della matrice dei coefficienti con il vettore dato.
     *
     * @param j indice della colonna da sostituire
     * @param vettore vettore con cui sostituire la colonna
     */
    private void sostituisciColonna(int j, double[] vettore) {
        for (int i = 0; i < n; i++) {
            coefficienti[i][j] = vettore[i];
        }
    }

    /**
     * Ripristina la colonna j della matrice dei coefficienti al suo valore originale.
     *
     * @param j indice della colonna da ripristinare
     */
    private void ripristinaColonna(int j) {
        // Rileggiamo i coefficienti originali dal costruttore
        // Nota: in una implementazione più robusta, dovremmo mantenere una copia separata
        // dei coefficienti originali. Per semplicità, ricostruiamo la matrice.

        // Questo metodo dovrebbe ripristinare i valori originali.
        // Per semplificare, assumiamo che il chiamante gestisca correttamente le chiamate.

        // In una versione più robusta:
        double[][] originali = getCoefficientiOriginali();
        for (int i = 0; i < n; i++) {
            coefficienti[i][j] = originali[i][j];
        }
    }

    /**
     * Metodo helper per ottenere i coefficienti originali.
     * In questa implementazione semplificata, ricreiamo i coefficienti originali.
     */
    private double[][] getCoefficientiOriginali() {
        // Questo è un approccio semplificato.
        // In un'implementazione reale, manterremmo una copia separata.
        return super.getCoefficienti();
    }

    /**
     * Versione migliorata del metodo risolvi che mantiene una copia dei coefficienti originali.
     */
    public double[] risolviSicuro() {
        // Manteniamo una copia dei coefficienti originali
        double[][] coefficientiOriginali = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(coefficienti[i], 0, coefficientiOriginali[i], 0, n);
        }

        double detA = Matrici.determinante(coefficienti);

        if (Math.abs(detA) < 1e-10) {
            throw new IllegalStateException("Il determinante è zero: sistema non risolvibile con la regola di Cramer");
        }

        double[] soluzione = new double[n];

        for (int j = 0; j < n; j++) {
            // Sostituiamo la colonna j con i termini noti
            for (int i = 0; i < n; i++) {
                coefficienti[i][j] = terminiNoti[i];
            }

            // Calcoliamo il determinante
            double detAj = Matrici.determinante(coefficienti);
            soluzione[j] = detAj / detA;

            // Ripristiniamo la colonna j
            for (int i = 0; i < n; i++) {
                coefficienti[i][j] = coefficientiOriginali[i][j];
            }
        }

        return soluzione;
    }

    /**
     * Verifica la soluzione sostituendola nel sistema originale.
     */
    public boolean verificaSoluzione(double[] soluzione) {
        if (soluzione.length != n) {
            return false;
        }

        double tolleranza = 1e-10;

        for (int i = 0; i < n; i++) {
            double somma = 0;
            for (int j = 0; j < n; j++) {
                somma += coefficienti[i][j] * soluzione[j];
            }

            if (Math.abs(somma - terminiNoti[i]) > tolleranza) {
                return false;
            }
        }

        return true;
    }

    // Metodo di test
    public static void main(String[] args) {
        // Esempio di sistema 3x3:
        // 2x + y - z = 8
        // -3x - y + 2z = -11
        // -2x + y + 2z = -3

        double[][] A = {
                {2, 1, -1},
                {-3, -1, 2},
                {-2, 1, 2}
        };

        double[] Y = {8, -11, -3};

        System.out.println("Test della classe Cramer:");
        System.out.println("========================");

        Cramer sistema = new Cramer(A, Y);
        sistema.stampaSistema();

        System.out.println("\nDeterminante della matrice dei coefficienti: " +
                Matrici.determinante(A));

        try {
            double[] soluzione = sistema.risolviSicuro();

            System.out.println("\nSoluzione del sistema:");
            for (int i = 0; i < soluzione.length; i++) {
                System.out.printf("x%d = %.6f\n", i + 1, soluzione[i]);
            }

            System.out.println("\nVerifica della soluzione: " +
                    sistema.verificaSoluzione(soluzione));

        } catch (IllegalStateException e) {
            System.err.println("Errore: " + e.getMessage());
        }

        // Test con sistema non risolvibile (determinante = 0)
        System.out.println("\n\nTest con sistema non risolvibile:");
        System.out.println("=================================");

        double[][] B = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        double[] Z = {1, 2, 3};

        Cramer sistemaNonRisolvibile = new Cramer(B, Z);
        sistemaNonRisolvibile.stampaSistema();

        try {
            double[] soluzione2 = sistemaNonRisolvibile.risolviSicuro();
        } catch (IllegalStateException e) {
            System.err.println("Errore atteso: " + e.getMessage());
        }
    }
}