package poo.appello_11_09_2025;

public class Mate {

    public static double[][] minore(double[][] a, int r, int c) {
        int n = a.length;
        double[][] minore = new double[n - 1][n - 1];
        int nuovaRiga = 0;
        for (int i = 0; i < n; i++) {
            if (i == r) continue;
            int nuovaColonna = 0;
            for (int j = 0; j < n; j++) {
                if (j == c) continue;
                minore[nuovaRiga][nuovaColonna] = a[i][j];
                nuovaColonna++;
            }
            nuovaRiga++;
        }
        return minore;
    }

    public static double determinante(double[][] a) {
        int n = a.length;
        if (n == 1) {
            return a[0][0];
        }
        if (n == 2) {
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];
        }
        double det = 0;
        for (int j = 0; j < n; j++) {
            double cofattore = Math.pow(-1, j) * a[0][j] * determinante(minore(a, 0, j));
            det += cofattore;
        }
        return det;
    }

    public static double[][] matriceInversa(double[][] a) {
        int n = a.length;
        double det = determinante(a);
        //errore Math.abs(det) è sempre <0.., una matrice è invertibile se det != 0
        if (det == 0) {
            throw new IllegalArgumentException("La matrice non è invertibile (determinante = 0)");
        }
        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double minoreDet = determinante(minore(a, i, j));
                double cofattore = Math.pow(-1, i + j) * minoreDet;
                inversa[j][i] = cofattore / det;
            }
        }
        return inversa;
    }

    public static double[][] mul(double[][] a, double[][] b) {
        int righeA = a.length;
        int colonneA = a[0].length;
        int righeB = b.length;
        int colonneB = b[0].length;

        if (colonneA != righeB) {
            throw new IllegalArgumentException("Matrici non compatibili per la moltiplicazione");
        }
        double[][] risultato = new double[righeA][colonneB];
        for (int i = 0; i < righeA; i++) {
            for (int j = 0; j < colonneB; j++) {
                for (int k = 0; k < colonneA; k++) {
                    risultato[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return risultato;
    }


    public static void scrivi(double[][] a) {
        for (double[] riga : a) {
            for (double elemento : riga) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("Esempio di matrice 3x3:");
        double[][] matrice = {
                {2, 1, 0},
                {1, 3, 1},
                {0, 1, 2}
        };

        double[][] matriceB = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        System.out.println("Matrice originale:");
        scrivi(matrice);

        System.out.println("Determinante: " + determinante(matrice) + "\n");

        System.out.println("Matrice inversa:");
        double[][] inversa = matriceInversa(matrice);
        scrivi(inversa);

        System.out.println("Verifica (A * A^-1):");
        double[][] identita = mul(matrice, inversa);
        scrivi(identita);

        System.out.println("Minore eliminando riga 0, colonna 0:");
        double[][] min = minore(matrice, 0, 0);
        scrivi(min);

        System.out.println("Matrice B:");
        scrivi(matriceB);

        System.out.println("Prodotto tra matrici:");
        double[][] prodotto = mul(matrice, matriceB);
        scrivi(prodotto);

//        System.out.println("=======");
//
//        System.out.println("Matrice interattiva: ");{
//            Scanner sc = new Scanner(System.in);
//            System.out.print("Inserisci numero di righe: ");
//            int righe = sc.nextInt();
//            System.out.print("Inserisci numero di colonne: ");
//            int colonne = sc.nextInt();
//            while(righe != colonne || righe<=1 || colonne<=1) {
//                System.out.println("Matrice non quadrata o troppo piccola, fornisci numero di righe: ");
//                righe = sc.nextInt();
//                System.out.println("fornisci numero di colonne: ");
//                colonne = sc.nextInt();
//            }
//            double[][] matriceInterattiva = new double[righe][colonne];
//            for (int i = 0; i < righe; i++) {
//                for (int j = 0; j < colonne; j++) {
//                    System.out.print("Inserisci elemento [" + i + "][" + j + "]: ");
//                    matriceInterattiva[i][j] = sc.nextDouble();
//                }
//            }
//            System.out.println("Ecco la tua matrice: ");
//            scrivi(matriceInterattiva);
//
//            System.out.println("Determinante: " + determinante(matriceInterattiva) + "\n");
//
//            System.out.println("Matrice inversa:");
//            double[][] inversa_ = matriceInversa(matriceInterattiva);
//            scrivi(inversa_);
//
//            System.out.println("Verifica (A * A^-1):");
//            double[][] identita_ = mul(matriceInterattiva, inversa_);
//            scrivi(identita_);
//
//            System.out.println("fornisci riga per il minore: ");
//            int riga = sc.nextInt();
//            System.out.println("fornisci colonna per il minore: ");
//            int colonna = sc.nextInt();
//            while(riga >= matriceInterattiva.length || colonna >= matriceInterattiva[0].length || riga < 0 || colonna < 0) {
//                System.out.println("Indice fuori dal range, fornisci riga per il minore: ");
//                riga = sc.nextInt();
//                System.out.println("fornisci colonna per il minore: ");
//                colonna = sc.nextInt();
//            }
//            System.out.println("Minore eliminando riga 0, colonna 0:");
//            double[][] min_ = minore(matriceInterattiva, 0, 0);
//            scrivi(min_);
//        }
    }
}
