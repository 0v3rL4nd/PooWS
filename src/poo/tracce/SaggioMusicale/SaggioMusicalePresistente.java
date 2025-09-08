package poo.tracce.SaggioMusicale;

import java.io.*;
import java.util.Scanner;

public class SaggioMusicalePresistente extends SaggioMusicaleImpl {

    /**
     * Popola l'oggetto this utilizzando il contenuto del file f.
     * Il file f è formato da linee in cui è presente: musicista primoStrumento secondoStrumento
     * public SaggioMusicalePresistente(File f) throws FileNotFoundException
     */
    public SaggioMusicalePresistente(File f) throws FileNotFoundException {
        super(); // Chiama il costruttore della classe padre

        Scanner scanner = new Scanner(f);
        try {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (!linea.isEmpty()) {
                    String[] parti = linea.split("\\s+"); // Split su spazi multipli
                    if (parti.length >= 3) {
                        String musicista = parti[0];
                        String primoStrumento = parti[1];
                        String secondoStrumento = parti[2];

                        // Aggiungi il musicista e i suoi strumenti
                        aggiungiMusicistaEStrumento(musicista, primoStrumento, secondoStrumento);
                    }
                }
            }
        } finally {
            scanner.close();
        }
    }


    public void salvaSuFile(File f) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(f);
        try {
            // Itera attraverso tutti i musicisti
            for (String musicista : musicisti()) {
                StringBuilder linea = new StringBuilder();
                linea.append(musicista);

                // Aggiungi primo strumento se esiste
                if (musicistaPrimoStrumento.containsKey(musicista)) {
                    linea.append(" ").append(musicistaPrimoStrumento.get(musicista));
                }

                // Aggiungi secondo strumento se esiste
                if (musicistaSecondoStrumento.containsKey(musicista)) {
                    linea.append(" ").append(musicistaSecondoStrumento.get(musicista));
                }

                writer.println(linea.toString());
            }
        } finally {
            writer.close();
        }
    }
}

// Classe Application per testare la funzionalità
class Application {
    public static void main(String[] args) {
        try {
            // Test di lettura da file
            File inputFile = new File("musicisti.txt");

            // Crea un file di esempio per il test (opzionale)
            if (!inputFile.exists()) {
                PrintWriter testWriter = new PrintWriter(inputFile);
                testWriter.println("Mario pianoforte violino");
                testWriter.println("Luigi chitarra batteria");
                testWriter.println("Anna flauto clarinetto");
                testWriter.close();
                System.out.println("File di esempio creato: " + inputFile.getName());
            }

            // Carica i dati dal file
            SaggioMusicalePresistente saggio = new SaggioMusicalePresistente(inputFile);

            // Visualizza i dati caricati
            System.out.println("Musicisti caricati: " + saggio.musicisti());
            System.out.println("Strumenti disponibili: " + saggio.strumenti());

            // Aggiungi un nuovo musicista
            saggio.aggiungiMusicistaEStrumento("Paolo", "tromba", "sassofono");

            // Salva i dati aggiornati su un nuovo file
            File outputFile = new File("musicisti_aggiornati.txt");
            saggio.salvaSuFile(outputFile);
            System.out.println("Dati salvati su: " + outputFile.getName());

            // Verifica alcune funzionalità
            System.out.println("Mario suona pianoforte come primo strumento: " +
                    saggio.suonaComePrimoStrumento("Mario", "pianoforte"));
            System.out.println("Musicisti che suonano violino: " +
                    saggio.dammiMusicisti(new String[]{"violino"}));

        } catch (FileNotFoundException e) {
            System.err.println("Errore: File non trovato - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Errore generale: " + e.getMessage());
            e.printStackTrace();
        }
    }
}