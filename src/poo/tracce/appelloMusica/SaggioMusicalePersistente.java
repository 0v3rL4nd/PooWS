package poo.tracce.appelloMusica;
//import java.io.*;
//import java.util.*;
//
//public class SaggioMusicalePersistente extends SaggioMusicaleImpl {
//
//    public SaggioMusicalePersistente(File f) throws IOException {
//        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length >= 2) {
//                    String musicista = parts[0].trim();
//                    String strumento1 = parts[1].trim();
//                    String strumento2 = parts.length > 2 ? parts[2].trim() : "";
//                    aggiungiMusicistaEStrumento(musicista, strumento1, strumento2);
//                }
//            }
//        }
//    }
//
//    public void salvaSuFile(File f) throws IOException {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
//            for (String[] musicista : musicisti()) {
//                List<String> strumenti = new ArrayList<>(getStrumenti(musicista));
//                String strumento1 = strumenti.get(0);
//                String strumento2 = strumenti.size() > 1 ? strumenti.get(1) : "";
//                bw.write(musicista + "," + strumento1 + (strumento2.isEmpty() ? "" : "," + strumento2));
//                bw.newLine();
//            }
//        }
//    }
//}
