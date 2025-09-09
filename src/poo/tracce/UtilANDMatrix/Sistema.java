package poo.tracce.UtilANDMatrix;

class Sistema {
    protected double[][] coefficienti;
    protected double[] terminiNoti;
    protected int n; // numero di incognite/equazioni

    /**
     * Costruttore per il sistema A*X = Y
     *
     * @param coefficienti matrice dei coefficienti A (nxn)
     * @param terminiNoti vettore dei termini noti Y (n)
     */
    public Sistema(double[][] coefficienti, double[] terminiNoti) {
        if (coefficienti == null || terminiNoti == null) {
            throw new IllegalArgumentException("Coefficienti e termini noti non possono essere null");
        }

        this.n = coefficienti.length;

        // Verifichiamo che la matrice sia quadrata
        for (int i = 0; i < n; i++) {
            if (coefficienti[i] == null || coefficienti[i].length != n) {
                throw new IllegalArgumentException("La matrice dei coefficienti deve essere quadrata");
            }
        }

        // Verifichiamo che il vettore dei termini noti abbia la dimensione corretta
        if (terminiNoti.length != n) {
            throw new IllegalArgumentException("Il vettore dei termini noti deve avere dimensione " + n);
        }

        // Copiamo i dati per evitare modifiche esterne
        this.coefficienti = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(coefficienti[i], 0, this.coefficienti[i], 0, n);
        }

        this.terminiNoti = new double[n];
        System.arraycopy(terminiNoti, 0, this.terminiNoti, 0, n);
    }

    /**
     * Getter per la matrice dei coefficienti.
     */
    public double[][] getCoefficienti() {
        // Ritorniamo una copia per preservare l'incapsulamento
        double[][] copia = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(coefficienti[i], 0, copia[i], 0, n);
        }
        return copia;
    }

    /**
     * Getter per il vettore dei termini noti.
     */
    public double[] getTerminiNoti() {
        // Ritorniamo una copia per preservare l'incapsulamento
        double[] copia = new double[n];
        System.arraycopy(terminiNoti, 0, copia, 0, n);
        return copia;
    }

    /**
     * Getter per la dimensione del sistema.
     */
    public int getDimensione() {
        return n;
    }

    /**
     * Stampa il sistema in forma leggibile.
     */
    public void stampaSistema() {
        System.out.println("Sistema di equazioni lineari:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && coefficienti[i][j] >= 0) {
                    System.out.print(" + ");
                } else if (j > 0) {
                    System.out.print(" ");
                }
                System.out.printf("%.3f*x%d", coefficienti[i][j], j + 1);
            }
            System.out.printf(" = %.3f\n", terminiNoti[i]);
        }
    }
}