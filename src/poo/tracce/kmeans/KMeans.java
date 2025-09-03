package poo.tracce.kmeans;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KMeans {

    Punto[] centroids;
    Map<Punto, Integer> dataSet = new HashMap<>();
    private int k;


    public KMeans(File dataset, int k, File kCentroidi) throws IOException {
        if(k <= 0 ) throw new IllegalArgumentException("k <= 0");
        this.k = k;

        //popolo il dataset
        try(BufferedReader br = new BufferedReader(new FileReader(dataset))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coords = line.split(" ");
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);
                Punto p = new Punto(x, y);
                dataSet.put(p, -1);
            }
        } catch (Exception e) {
            throw new FileNotFoundException("file inesistente");
        }

        //popolo centroids
        try(BufferedReader br = new BufferedReader(new FileReader(kCentroidi))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] coords = line.split(" ");
                double x = Double.parseDouble(coords[0]);
                double y = Double.parseDouble(coords[1]);
                Punto p = new Punto(x, y);
                centroids[i] = p;
                i++;
            }
        } catch (Exception e) {
            throw new FileNotFoundException("file inesistente");
        }

    }

    public void run(int epoach){
        boolean terminazione = true;
        double distanza = 0;

        for (int i = 0; i<= epoach; i++){

            //aggiorno cluster
            for(Punto p  : dataSet.keySet()){
                for(int j = 0; j<= centroids.length; j++){
                    if(distanza >= p.distanza(centroids[j])){
                        distanza = p.distanza(centroids[j]);
                        dataSet.put(p, j);
                    } else {
                        terminazione = false;
                    }
                }
            }

            //aggiorno centroids
            for(int j = 0; j<= centroids.length; j++){
                List<Punto> puntiCluster = new ArrayList<>();
                for( Punto p : dataSet.keySet()){
                    if(dataSet.get(p) == j ){
                        puntiCluster.add(p);
                    }
                }
                if( !puntiCluster.isEmpty() ){
                    double sommaX = 0, sommaY = 0;
                    for(Punto p:  puntiCluster){
                        sommaX += p.getX();
                        sommaY += p.getY();
                    }
                    double mediaX = sommaX / puntiCluster.size();
                    double mediaY = sommaY / puntiCluster.size();
                    centroids[j] = new Punto(mediaX, mediaY);
                }

            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Centroids: \n");

        for(int i = 0; i< centroids.length; i++){
            sb.append("C" + i + ": " + centroids[i] + "\n");
        }

        sb.append("Dataset: \n");
        for(Punto p : dataSet.keySet()){
            sb.append(p + " -> " + dataSet.get(p) + "\n");
        }

        return sb.toString();
    }
}
