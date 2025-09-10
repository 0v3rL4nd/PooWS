package poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca;

public class MatriceInversa {
    public static double[][] matriceInversa(double[][] a) {
        int n = a.length;

        // Verifica che sia quadrata
        for (int i = 0; i < n; i++) {
            if (a[i].length != n) {
                throw new RuntimeException("La matrice non è quadrata");
            }
        }

        // Crea matrice aumentata [A|I]
        double[][] aumentata = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aumentata[i][j] = a[i][j];
            }
            aumentata[i][i + n] = 1.0; // matrice identità
        }

        // Eliminazione di Gauss-Jordan
        for (int i = 0; i < n; i++) {
            // Trova pivot
            int maxRiga = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(aumentata[k][i]) > Math.abs(aumentata[maxRiga][i])) {
                    maxRiga = k;
                }
            }

            // Scambia righe
            if (maxRiga != i) {
                double[] temp = aumentata[i];
                aumentata[i] = aumentata[maxRiga];
                aumentata[maxRiga] = temp;
            }

            // Verifica che il pivot non sia zero
            if (Math.abs(aumentata[i][i]) < 1e-10) {
                throw new RuntimeException("Matrice non invertibile");
            }

            // Normalizza riga pivot
            double pivot = aumentata[i][i];
            for (int j = 0; j < 2 * n; j++) {
                aumentata[i][j] /= pivot;
            }

            // Elimina colonna
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double fattore = aumentata[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        aumentata[k][j] -= fattore * aumentata[i][j];
                    }
                }
            }
        }

        // Estrai matrice inversa
        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inversa[i][j] = aumentata[i][j + n];
            }
        }

        return inversa;
    }

    static void stampaMatrice(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%.3f ", m[i][j]);
            }
            System.out.println();
        }
    }
}
