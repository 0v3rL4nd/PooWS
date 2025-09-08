package poo.tracce.Testo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class Applicazione {
    public Testo leggiTesto(BufferedReader reader) throws IOException {
        Testo t = new TestoImpl(new ArrayList<>(), new ArrayList<>());
        String line;
        while ((line = reader.readLine()) != null) {
            // MIGLIORAMENTO: Regex più robusta per dividere le parole
            String[] parole = line.split("[\\s\\p{Punct}]+");
            for (String parola : parole) {
                if (!parola.trim().isEmpty()) {
                    t.add(parola.toUpperCase().trim());
                }
            }
        }
        return t;
    }

    public static void main(String[] args) {
        Applicazione app = new Applicazione();

        // Test con stringhe di esempio invece di file
        try {
            // Simula lettura da file con stringhe di test
            String testo1 = "Il gatto è sul tetto. Il gatto dorme.";
            String testo2 = "Il cane è nel giardino. Il cane corre.";

            BufferedReader reader1 = new BufferedReader(new StringReader(testo1));
            Testo t1 = app.leggiTesto(reader1);
            System.out.println("Testo 1: " + t1);
            reader1.close();

            BufferedReader reader2 = new BufferedReader(new StringReader(testo2));
            Testo t2 = app.leggiTesto(reader2);
            System.out.println("Testo 2: " + t2);
            reader2.close();

            System.out.println("Parole in comune: " + t1.paroleInComune(t2));
            System.out.println("Moda testo 1: " + t1.moda());
            System.out.println("Moda testo 2: " + t2.moda());
            System.out.println("Similarità coseno: " + String.format("%.4f", t1.similaritaCoseno(t2)));

            // Test con testi identici
            System.out.println("\n--- Test con testi identici ---");
            BufferedReader reader3 = new BufferedReader(new StringReader(testo1));
            Testo t3 = app.leggiTesto(reader3);
            reader3.close();
            System.out.println("Similarità tra t1 e t3 (identici): " + String.format("%.4f", t1.similaritaCoseno(t3)));
            System.out.println("t1.equals(t3): " + t1.equals(t3));

        } catch (IOException e) {
            System.out.println("Errore di I/O: " + e.getMessage());
        }
    }
}