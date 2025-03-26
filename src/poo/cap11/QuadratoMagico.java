package poo.cap11;

import javax.swing.*;

public class QuadratoMagico {
    public static void main(String[] args) {
        try{
            String n = JOptionPane.showInputDialog("Inserire un numero intero: ");

            if( n == null || n.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Inserire un numero intero valido");
                return;
            }
            int numero = Integer.parseInt(n);
            if(numero < 0){
                JOptionPane.showMessageDialog(null, "Inserire un numero invalido");
                return;
            }
            int m[][] = new int[numero][numero];
            for(int i = 0; i < m.length; i++){
                for(int j = 0; j < m[i].length; j++){
                    m[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Inserire un numero intero: "));
                }
            }

            if( isQuadratoMagico(m) ) {
                JOptionPane.showMessageDialog(null, "La matrice fornita è un quadrato magico!");
            }
            else{
                JOptionPane.showMessageDialog(null,"La matrice fornita NON è un quadrato magico.");
            }

        } catch ( NumberFormatException e ) {
            JOptionPane.showMessageDialog(null, "Inserisci un numero intero valido!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean isQuadratoMagico(int[][] matrice){
        int n = matrice.length;
        int sommaDiagonale1 = 0, sommaDiagonale2 = 0;
        int sommaRighe[] = new int[n];
        int sommaColonne[] = new int[n];

        // Calcolo delle somme
        for (int i = 0; i < n; i++) {
            sommaDiagonale1 += matrice[i][i];
            sommaDiagonale2 += matrice[i][n - 1 - i];
            for (int j = 0; j < n; j++) {
                sommaRighe[i] += matrice[i][j];
                sommaColonne[j] += matrice[i][j];
            }
        }

        // Controllo che tutte le somme siano uguali
        int sommaObiettivo = sommaDiagonale1;
        if (sommaDiagonale1 != sommaDiagonale2) return false;

        for (int i = 0; i < n; i++) {
            if (sommaRighe[i] != sommaObiettivo || sommaColonne[i] != sommaObiettivo) return false;
        }

        return true;
    }
}
