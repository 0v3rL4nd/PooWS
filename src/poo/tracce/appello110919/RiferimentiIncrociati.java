package poo.tracce.appello110919;

import java.io.*;
import java.util.*;
import java.util.regex.*;

//continuo a non capire questo esercizio

public class RiferimentiIncrociati {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nome del file di input: ");
        String nomeFile = scanner.nextLine();

        Map<String, Set<Integer>> indice = new TreeMap<>(Comparator.comparingInt(String::length).thenComparing(String::compareTo));

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            Pattern pattern = Pattern.compile("^(\\w+):(\\d+)$");

            while ((linea = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(linea);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("Formato del file non valido: " + linea);
                }

                String parola = matcher.group(1);
                int pagina = Integer.parseInt(matcher.group(2));

                indice.computeIfAbsent(parola, k -> new TreeSet<>()).add(pagina);
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file: " + e.getMessage());
            return;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.print("Inserisci il nome del file di output: ");
        String nomeFileOutput = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFileOutput))) {
            for (Map.Entry<String, Set<Integer>> entry : indice.entrySet()) {
                String output = entry.getKey() + " -> " + entry.getValue();
                System.out.println(output);
                writer.write(output);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }
}
