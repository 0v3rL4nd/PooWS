package poo.tracce.DizionarioBacktracking;

import java.util.*;

public class Verifica {
    private Set<String> dizionario;

    public Verifica(Set<String> dizionario) {
        this.dizionario = new HashSet<>(dizionario);
    }

    public boolean risolvi(String s) {
        if (s == null || s.isEmpty()) {
            return true; // stringa vuota è sempre scomponibile
        }

        // Array ausiliario per tenere traccia degli indici di scelta
        // aux[i] rappresenta l'indice della parola scelta che inizia alla posizione i
        int[] aux = new int[s.length()];
        Arrays.fill(aux, -1);

        return backtrack(s, 0, aux);
    }

    private boolean backtrack(String s, int pos, int[] aux) {
        // Caso base: se abbiamo raggiunto la fine della stringa, abbiamo successo
        if (pos == s.length()) {
            return true;
        }

        // Prova tutte le possibili sottostringhe che iniziano dalla posizione corrente
        for (int i = pos + 1; i <= s.length(); i++) {
            String sottostringa = s.substring(pos, i);

            // Se la sottostringa è nel dizionario
            if (dizionario.contains(sottostringa)) {
                // Registra la scelta nell'array ausiliario
                aux[pos] = i - pos; // lunghezza della parola scelta

                // Ricorsione: prova a scomporre il resto della stringa
                if (backtrack(s, i, aux)) {
                    return true; // Soluzione trovata
                }

                // Backtrack: annulla la scelta
                aux[pos] = -1;
            }
        }

        // Nessuna decomposizione possibile da questa posizione
        return false;
    }

    public List<String> getDecomposizione(String s) {
        List<String> risultato = new ArrayList<>();

        if (s == null || s.isEmpty()) {
            return risultato;
        }

        int[] aux = new int[s.length()];
        Arrays.fill(aux, -1);

        if (backtrack(s, 0, aux)) {
            // Ricostruisce la decomposizione dall'array aux
            int pos = 0;
            while (pos < s.length()) {
                int lunghezza = aux[pos];
                if (lunghezza > 0) {
                    risultato.add(s.substring(pos, pos + lunghezza));
                    pos += lunghezza;
                } else {
                    break; // Errore nella ricostruzione
                }
            }
        }

        return risultato;
    }

    public static void main(String[] args) {
        // Esempio dal testo della traccia
        Set<String> dizionario = new HashSet<>(Arrays.asList(
                "il", "dado", "la", "cane", "corre", "zebra", "veloce",
                "gatto", "grigio", "treno", "salta"
        ));

        Verifica verifica = new Verifica(dizionario);

        // Test 1: stringa scomponibile
        String s1 = "ilgattovelocesaltailcane";
        System.out.println("Test 1: \"" + s1 + "\"");
        boolean risultato1 = verifica.risolvi(s1);
        System.out.println("È scomponibile: " + risultato1);

        if (risultato1) {
            List<String> decomposizione1 = verifica.getDecomposizione(s1);
            System.out.println("Decomposizione: " + decomposizione1);
        }
        System.out.println();

        // Test 2: stringa non scomponibile
        String s2 = "ilungotreno";
        System.out.println("Test 2: \"" + s2 + "\"");
        boolean risultato2 = verifica.risolvi(s2);
        System.out.println("È scomponibile: " + risultato2);

        if (risultato2) {
            List<String> decomposizione2 = verifica.getDecomposizione(s2);
            System.out.println("Decomposizione: " + decomposizione2);
        }
        System.out.println();

        // Test 3: altri casi
        String s3 = "ilgatto";
        System.out.println("Test 3: \"" + s3 + "\"");
        boolean risultato3 = verifica.risolvi(s3);
        System.out.println("È scomponibile: " + risultato3);

        if (risultato3) {
            List<String> decomposizione3 = verifica.getDecomposizione(s3);
            System.out.println("Decomposizione: " + decomposizione3);
        }
        System.out.println();

        // Test 4: stringa vuota
        String s4 = "";
        System.out.println("Test 4: stringa vuota");
        boolean risultato4 = verifica.risolvi(s4);
        System.out.println("È scomponibile: " + risultato4);
        System.out.println();

        // Test con dizionario più complesso per mostrare il backtracking
        System.out.println("=== Test avanzato per dimostrare il backtracking ===");
        Set<String> dizionarioComplesso = new HashSet<>(Arrays.asList(
                "gatto", "gatt", "o", "veloce", "vel", "oce"
        ));

        Verifica verificaComplessa = new Verifica(dizionarioComplesso);
        String s5 = "gattoveloce";
        System.out.println("Dizionario: " + dizionarioComplesso);
        System.out.println("Test: \"" + s5 + "\"");
        boolean risultato5 = verificaComplessa.risolvi(s5);
        System.out.println("È scomponibile: " + risultato5);

        if (risultato5) {
            List<String> decomposizione5 = verificaComplessa.getDecomposizione(s5);
            System.out.println("Decomposizione trovata: " + decomposizione5);
        }
    }
}