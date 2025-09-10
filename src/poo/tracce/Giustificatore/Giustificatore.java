package poo.tracce.Giustificatore;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Giustificatore {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java Giustificatore <fileInput> <fileOutput>");
            System.exit(1);
        }
        Path in = Paths.get(args[0]);
        Path out = Paths.get(args[1]);

        try {
            List<String> righe = Files.readAllLines(in, StandardCharsets.UTF_8);
            List<String> giustificate = giustificaTesto(righe);
            Files.write(out, giustificate, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("File giustificato scritto in: " + out.toString());
        } catch (IOException e) {
            System.err.println("Errore I/O: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<String> giustificaTesto(List<String> righe) {
        // 1) calcola lunghezza massima
        int maxLen = 0;
        for (String r : righe) {
            if (r.length() > maxLen) maxLen = r.length();
        }

        List<String> result = new ArrayList<>(righe.size());
        for (String r : righe) {
            // se già della lunghezza massima o termina con '.' non si giustifica
            String trimmed = r.trim();
            if (r.length() == maxLen || (!trimmed.isEmpty() && trimmed.endsWith("."))) {
                // manteniamo la riga così com'è
                result.add(r);
            } else {
                result.add(giustificaRiga(r, maxLen));
            }
        }
        return result;
    }

    private static String giustificaRiga(String riga, int targetLen) {
        // split su uno o più spazi bianchi
        String[] parole = riga.trim().split("\\s+");
        int k = parole.length;
        if (k == 0) {
            // riga vuota -> riempi di spazi fino a targetLen
            return repeatChar(' ', targetLen);
        }

        int lunghezzaParole = 0;
        for (String p : parole) lunghezzaParole += p.length();

        int totaleSpaziNecessari = targetLen - lunghezzaParole;
        if (k == 1) {
            // una sola parola: right-pad con spazi
            return parole[0] + repeatChar(' ', Math.max(0, totaleSpaziNecessari));
        } else {
            int gap = k - 1;
            int base = totaleSpaziNecessari / gap;
            int extra = totaleSpaziNecessari % gap; // i primi 'extra' gap avranno base+1
            StringBuilder sb = new StringBuilder(targetLen);
            for (int i = 0; i < k; i++) {
                sb.append(parole[i]);
                if (i < gap) {
                    int spazi = base + (i < extra ? 1 : 0);
                    sb.append(repeatChar(' ', spazi));
                }
            }
            // sicurezza: se per qualche motivo la concatenazione non raggiunge targetLen, pad a destra
            while (sb.length() < targetLen) sb.append(' ');
            // se dovesse superare, tronca (non dovrebbe succedere)
            if (sb.length() > targetLen) return sb.substring(0, targetLen);
            return sb.toString();
        }
    }

    private static String repeatChar(char c, int n) {
        if (n <= 0) return "";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}
