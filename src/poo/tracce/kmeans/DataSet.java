package poo.tracce.kmeans;

import java.io.File;
import java.io.FileNotFoundException;

public class DataSet {

    private DataSet(){};

    public static void crea (File f, int n, int min, int max) throws FileNotFoundException {
        if(n <= 0 ) throw new IllegalArgumentException("n <= 0");
        try(var pw = new java.io.PrintWriter(f)){
            for( int i = 0; i < n; i++ ){
                double x = Math.random() * (max-min) + min;
                double y = Math.random() * (max-min) + min;
            }
        } catch (Exception e) {
            throw new FileNotFoundException("file inesistente");
        }
    }
}
