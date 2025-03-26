package poo.tracce.appello060223;

import java.io.IOException;

/*
Si vuole implementare una classe PersistenzaDitributore che offra i seguenti due metodi statici finalizzati alla gestione della persistenza fi un oggetto Distributore.
Il primo metodo salva il contenuto del distributore nei due file. In file1 pone i prodotti, in file2 pone i nomi dei prodotti, quntità prodotti disponibili, quantità prodotti venduti
Il secondo metodo crea un oggetto DistributoreImpl e lo popola partendo dalla lettura dei file file1 e file2
 */

import java.io.*;
import java.util.*;

public class PersistenzaDistributore {

    public static void salva(DistributoreImpl d, String file1, String file2) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file1));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {

            // Salva la lista dei prodotti in formato binario
            oos.writeObject(d.prodottiDisponibili());

            // Salva informazioni testuali sui prodotti
            for (Prodotto p : d.prodottiDisponibili()) {
                writer.write(p.getNome() + "," + d.disponibilitaProdotto(p) + "," + d.numeroProdottiVenduti(p));
                writer.newLine();
            }
            oos.close();
            writer.close();

        } catch (IOException e) {
            System.out.println("Errore di I/O");
        }

    }

    public static DistributoreImpl ripristina(String file1, String file2) throws Exception {
        DistributoreImpl distributore = new DistributoreImpl();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file1));
             BufferedReader reader = new BufferedReader(new FileReader(file2))) {

            // Legge la lista dei prodotti
            List<Prodotto> prodotti = (List<Prodotto>) ois.readObject();
            for (Prodotto p : prodotti) {
                distributore.aggiungiProdottoDaVendere(p);
            }

            // Legge informazioni testuali e aggiorna il distributore
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] dati = linea.split(",");
                String nome = dati[0];
                int quantitaDisponibile = Integer.parseInt(dati[1]);
                Prodotto p1 = new Prodotto(nome, 10, quantitaDisponibile);
                distributore.aggiungiProdottoDaVendere(p1);
            }
        }

        return distributore;
    }
}
