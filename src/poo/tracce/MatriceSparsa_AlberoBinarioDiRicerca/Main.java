package poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca;

import static poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca.MatriceInversa.matriceInversa;
import static poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca.MatriceInversa.stampaMatrice;

class Main {
    public static void main(String[] args) {
        // Test della matrice sparsa
        MatriceSparsa m1 = new MatriceSparsaLinked(100, 100);
        m1.set(0, 0, 5.0);
        m1.set(2, 5, 3.0);
        m1.set(99, 99, 1.0);

        System.out.println("Elemento (0,0): " + m1.get(0, 0));
        System.out.println("Elemento (1,1): " + m1.get(1, 1));
        System.out.println("Simmetrica: " + m1.simmetrica());

        // Test matriceInversa (Esercizio 2)
        double[][] test = {{2.0, 1.0}, {1.0, 1.0}};
        try {
            double[][] inversa = matriceInversa(test);
            System.out.println("Matrice inversa calcolata:");
            stampaMatrice(inversa);
        } catch (RuntimeException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}