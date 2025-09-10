package poo.tracce.SudokuNoGUI_RiferimentiIncrociati;

import java.io.*;
import java.util.*;
import java.util.regex.*;

class RiferimentiIncrociati {
    // TreeMap con comparatore custom: prima per lunghezza, poi alfabetico
    private static TreeMap<String, TreeSet<Integer>> indice = new TreeMap<>(
            (String s1, String s2) -> {
                if (s1.length() < s2.length()) return -1;
                if (s1.length() > s2.length()) return 1;
                return s1.compareTo(s2);
            }
    );

    /**
     * Verifica la correttezza del formato del file
     *
     * @param f file da verificare
     * @return true se il formato è corretto
     */
    static boolean verificaFile(File f) throws IOException {
        Pattern pattern = Pattern.compile("\\w+:\\d+");

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            int numeroLinea = 0;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                if (!pattern.matcher(linea.trim()).matches()) {
                    System.err.println("Errore alla linea " + numeroLinea + ": " + linea);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Carica il contenuto del file nell'indice
     */
    static void caricaFile(File f) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] parti = linea.trim().split(":");
                if (parti.length == 2) {
                    String parola = parti[0];
                    int numeroLinea = Integer.parseInt(parti[1]);

                    indice.computeIfAbsent(parola, k -> new TreeSet<>()).add(numeroLinea);
                }
            }
        }
    }

    /**
     * Visualizza l'indice su console
     */
    static void visualizzaIndice() {
        System.out.println("\n=== INDICE ANALITICO ===");
        for (String parola : indice.keySet()) {
            System.out.printf("%-15s: ", parola);
            StringJoiner sj = new StringJoiner(", ");
            for (int numeroLinea : indice.get(parola)) {
                sj.add(String.valueOf(numeroLinea));
            }
            System.out.println(sj.toString());
        }
    }

    /**
     * Scrive l'indice su file
     */
    static void scriviIndice(File fo) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fo))) {
            for (String parola : indice.keySet()) {
                pw.println(parola);
                pw.print("\t");
                StringJoiner sj = new StringJoiner(" ");
                for (int numeroLinea : indice.get(parola)) {
                    sj.add(String.valueOf(numeroLinea));
                }
                pw.println(sj.toString());
            }
        }
        System.out.println("Indice scritto su: " + fo.getAbsolutePath());
    }

    /**
     * Crea un file di esempio per il test
     */
    static void creaFileEsempio() throws IOException {
        File esempio = new File("esempio_input.txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(esempio))) {
            pw.println("casa:1");
            pw.println("cane:1");
            pw.println("gatto:2");
            pw.println("casa:3");
            pw.println("cane:4");
            pw.println("automobile:5");
            pw.println("casa:6");
            pw.println("gatto:7");
            pw.println("bicicletta:8");
        }
        System.out.println("File di esempio creato: " + esempio.getAbsolutePath());
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== GENERATORE RIFERIMENTI INCROCIATI ===");
            System.out.println("1. Usa file esistente");
            System.out.println("2. Crea file di esempio");
            System.out.print("Scelta (1 o 2): ");

            String scelta = sc.nextLine();
            File fileInput;

            if ("2".equals(scelta)) {
                creaFileEsempio();
                fileInput = new File("esempio_input.txt");
            } else {
                System.out.print("Nome file di ingresso: ");
                String nomeFileInput = sc.nextLine();
                fileInput = new File(nomeFileInput);

                if (!fileInput.exists()) {
                    throw new RuntimeException("File di ingresso inesistente: " + nomeFileInput);
                }
            }

            System.out.print("Nome file di uscita: ");
            String nomeFileOutput = sc.nextLine();
            File fileOutput = new File(nomeFileOutput);

            // Verifica e carica il file
            if (!verificaFile(fileInput)) {
                throw new RuntimeException("File di ingresso non corretto");
            }

            caricaFile(fileInput);
            visualizzaIndice();
            scriviIndice(fileOutput);

        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
            e.printStackTrace();
        }
    }
}