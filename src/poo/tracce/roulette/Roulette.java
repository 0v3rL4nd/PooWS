package poo.tracce.roulette;

import poo.util.Array;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Roulette {

    private double[] numeri;

    public Roulette(File f) throws IOException {

        //controllo sul file e popolamento di numeri
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String linea;
            linea = br.readLine();
            while(linea != null){
                linea = linea.trim();
                if(linea.matches("([0-9]|[1-2][0-9]|3[0-6])(\\s+)([0-9]|[1-2][0-9]|3[0-6])*")){
                    String[] numeri = linea.split(" ");
                    for(int i = 0; i < numeri.length; i++){
                        this.numeri[i] = Double.parseDouble(numeri[i]);
                    }
                }else{
                    throw new IllegalArgumentException("Linea non valida");
                }
            }
        }catch(Exception e){
            throw new IOException("file inesistente");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Integer> processati = new HashSet<>();

        for (int i = 0; i < numeri.length; i++) {
            int numeroCorrente = (int) numeri[i];
            // Controlla se già processato
            if (processati.contains(numeroCorrente)) {
                continue;
            }
            processati.add(numeroCorrente);
            Map<Integer, Integer> successori = new TreeMap<>();
            // Trova tutti i successori di numeroCorrente
            for (int j = 0; j < numeri.length - 1; j++) {
                if ((int) numeri[j] == numeroCorrente) {
                    int successore = (int) numeri[j + 1];
                    successori.put(successore, successori.getOrDefault(successore, 0) + 1);
                }
            }
            // Stampa risultato solo se ci sono successori
            if (!successori.isEmpty()) {
                sb.append("Il numero " + numeroCorrente + " è seguito da ");
                for (Integer successore : successori.keySet()) {
                    sb.append(successore + ":" + successori.get(successore) + " volte \n");
                }
            }
        }
        return sb.toString();
    }

    public boolean uscito(int x){
        for(int i = 0; i < numeri.length; i++){
            if(numeri[i] == x){
                return true;
            }
        }
        return false;
    }

    public int numeroUscitoPiuDiFrequenteDopo(int x) {
        Map<Double, Integer> frequenze = new TreeMap<>();

        // Conta le frequenze di tutti i numeri
        for (int i = 0; i < numeri.length; i++) {
            double numero = numeri[i];
            frequenze.put(numero, frequenze.getOrDefault(numero, 0) + 1);
        }
        int frequenzaX = frequenze.getOrDefault((double) x, 0);
        // Trova la frequenza massima minore di frequenzaX
        int frequenzaTarget = 0;
        for (Integer freq : frequenze.values()) {
            if (freq > frequenzaTarget && freq < frequenzaX) {
                frequenzaTarget = freq;
            }
        }
        // Se non esiste una frequenza minore, restituisci -1
        if (frequenzaTarget == 0) {
            return -1;
        }
        // Trova il primo numero (nell'ordine dell'array) con frequenzaTarget
        for (int i = 0; i < numeri.length; i++) {
            double numeroCorrente = numeri[i];
            if ((int) numeroCorrente != x && frequenze.get(numeroCorrente) == frequenzaTarget) {
                return (int) numeroCorrente;
            }
        }
        return -1;
    }
}
