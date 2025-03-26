package poo.tracce.appelloArtefatto;

import java.io.*;
import java.util.*;

public class CrivelloFile {
    private int N;
    private String numeriFile = "Numeri.txt";
    private String risultatoFile = "Risultato.txt";
    private String tempFile = "Temp.txt";

    public CrivelloFile(int N) {
        this.N = N;
        inizializzaFile();
    }

    private void inizializzaFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numeriFile))) {
            for (int i = 2; i <= N; i++) {
                writer.write(i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Pulisce gli altri file
        try {
            new FileWriter(risultatoFile, false).close();
            new FileWriter(tempFile, false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void filtra() {
        while (true) {
            List<Integer> numeri = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(numeriFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    numeri.add(Integer.parseInt(line.trim()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (numeri.isEmpty()) break;

            int minVal = numeri.get(0);

            // Aggiunge il numero primo trovato al file Risultato
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(risultatoFile, true))) {
                writer.write(minVal + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Filtra multipli di minVal e scrive i restanti in Temp
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                for (int i = 1; i < numeri.size(); i++) {
                    if (numeri.get(i) % minVal != 0) {
                        writer.write(numeri.get(i) + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Sostituisce Numeri con Temp
            new File(numeriFile).delete();
            new File(tempFile).renameTo(new File(numeriFile));
        }
    }

    public List<Integer> leggiRisultato() {
        List<Integer> risultati = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(risultatoFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                risultati.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return risultati;
    }

    public static void main(String[] args) {
        int n = 30;
        CrivelloFile crivello = new CrivelloFile(n);
        crivello.filtra();
        System.out.println("Numeri primi trovati: " + crivello.leggiRisultato());
    }
}
