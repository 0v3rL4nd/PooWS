package poo.cap11;

import javax.swing.JOptionPane;

public class NumeroPerfetto {
    public static void main(String[] args) {
        try {
            // Legge il numero dall'utente tramite finestra di dialogo
            String input = JOptionPane.showInputDialog("Inserisci un numero intero positivo:");

            // Controlla se l'utente ha annullato o lasciato vuoto
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nessun numero inserito. Uscita dal programma.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numero = Integer.parseInt(input);

            // Controllo se è un numero positivo
            if (numero <= 0) {
                JOptionPane.showMessageDialog(null, "Il numero deve essere un intero positivo!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica se è un numero perfetto
            if (isNumeroPerfetto(numero)) {
                JOptionPane.showMessageDialog(null, numero + " è un numero perfetto!", "Risultato", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, numero + " non è un numero perfetto.", "Risultato", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Inserisci un numero intero valido!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo per controllare se un numero è perfetto
    public static boolean isNumeroPerfetto(int n) {
        int somma = 0;

        // Calcola la somma dei divisori propri
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                somma += i;
            }
        }

        // Un numero è perfetto se la somma dei suoi divisori propri è uguale a se stesso
        return somma == n;
    }
}
