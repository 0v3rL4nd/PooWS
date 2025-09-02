package poo.tracce.NegozioScarpe;

import java.io.*;
import java.util.*;

public class CampionarioScarpe {
    public static void salvaCampionario(NegozioScarpe negozio, File f) throws IOException {
        Map<String, Set<Integer>> campionario = new TreeMap<>();

        if (negozio instanceof NegozioScarpeImpl) {
            NegozioScarpeImpl n = (NegozioScarpeImpl) negozio;
            for (NegozioScarpeImpl.coppia c : n.getNegozioScarpe()) {
                if(!campionario.containsKey(c.getModello())) campionario.put(c.getModello(), new HashSet<>());
                campionario.get(c.getModello()).add(c.getMisura());
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (String modello : campionario.keySet()) {
                bw.write(modello);
                for (int misura : campionario.get(modello)) {
                    bw.write(" " + misura);
                }
                bw.newLine();
            }
        }
    }


    public static void elaboraCampionario(File source, File dest, int misuraMin, int misuraMax) throws IOException {
        Map<String,  Set<Integer>> campionario = new TreeMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(source))) {
            String line = br.readLine();
            while (line != null) {
                String[] campioni = line.split(" ");
                String modello = campioni[0];
                Set<Integer> misure = new HashSet<>();
                for (int i = 1; i < campioni.length; i++) {
                    int misura = Integer.parseInt(campioni[i]);
                    if (misura >= misuraMin && misura <= misuraMax) {
                        misure.add(misura);
                    }
                }
                campionario.put(modello, misure);
                line = br.readLine();
            }
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(dest))){
            for(String modello: campionario.keySet()){
                bw.write(modello);
                for(int misura: campionario.get(modello)){
                    bw.write(" " + misura);
                }
                bw.newLine();
            }

        }
    }
}