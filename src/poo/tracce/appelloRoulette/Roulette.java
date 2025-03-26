package poo.tracce.appelloRoulette;

/*
Si vogliono valutare statisticamene i numeri che escono al gioco della roulette e i possibii numeri son gli interi da 0 a 36.
Su un fie di tipo testo è memorizzata la sequenza di numeri usciti in un certo arco di tempo.
ogni linea del fil contiene i numeri usciti in sequenza, ad es. in un'ora di gioco.
La statistica desiderata consiste nell'estrarre dal file le informazioni di uscita dei numeri e mostrarle seconfo il formato che segue:
"1 seguito da 8: 1 volta,
2 seguito da 4: 3 volte,
2 seguito da 3: 1 volta,
...
"
Sviluppare la classe roulette che a tempo di costruzione riceve un oggetto File. Dopo averne verificato la validità di ciascuna linea con una regex,
i numeri vanno estratti e memorizzati in un oggetto TreeMap<Integer, TreeMap<Integer, Integer>>.
La classe deve ammettere i metodi: toString(), uscito(int x), numeroUscitoPiuDiFrequenteDopo(int x).
aggiungere inoltre un main di prova.
 */

import java.io.*;
import java.util.*;

/*
Sta cosa qua sotto, mi vergogno terribilmente
 */

//public class Roulette {
//
//    private Map<Integer, Integer> adiacenze[] = new TreeMap[37];
//    private Set<Integer> usciti  = new TreeSet<>();
//
//    public Roulette(File file) throws IOException {
//
//        if( !(file.exists()) ) throw new FileNotFoundException();
//
//        String numeri = "[0-9]|[1-2][0-9]|3[0-6]";
//        String line =  numeri + "((\\s+)" + numeri + ")*";
//        BufferedReader br = new BufferedReader(new FileReader(file));
//
//        for(;;){
//            try{
//            String linea = br.readLine();
//            if( line == null ) break;
//            if( !(linea.matches(line)) ) throw new IllegalArgumentException("Linea non valida");
//            StringTokenizer  st = new StringTokenizer(linea, " ");
//                        while( st.hasMoreTokens() ){
//                usciti.add(Integer.parseInt(st.nextToken()));
//                if( !(usciti.contains(Integer.parseInt(String.valueOf(st))))){
//                    adiacenze[Integer.parseInt(String.valueOf(st))] = new TreeMap<>();
//                }
//                else{
//                    adiacenze[Integer.parseInt(String.valueOf(st))].put(Integer.parseInt(String.valueOf(st)), 1);
//                }
//
//            }
//
//            }
//            catch(IOException e){
//                break;
//            }
//            br.close();
//        }
//    }
//
//    public boolean eUscito(int x){
//        return usciti.contains(x);
//    }
//
//    public int numeroUscitoPiuDiFrequenteDopo(int x){
//        if( !(eUscito(x)) ) return -1;
//        int valore = 0;
//        for(int i = 0; i < adiacenze.length; i++){
//            if( i == x ){
//                int max = Integer.MIN_VALUE;
//                for(int val : adiacenze[i].keySet()){
//                    if( adiacenze[i].get(val) > max ){
//                        max = adiacenze[i].get(val);
//                        valore = val;
//                    }
//                }
//            }
//        }
//        return valore;
//    }
//
//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder(100);
//        sb.append("Roulette\n");
//        for(int i = 0; i < adiacenze.length; i++){
//            sb.append("Il numero " + i + " è seguito da   ");
//            for(int val : adiacenze[i].keySet()){
//                sb.append(val + ":" + adiacenze[i].get(val) + " volte   ");
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        String roulette = "C:\\Users\\vogli\\OneDrive\\Documenti\\Roulette.txt";
//        File f = new File(roulette);
//        Roulette r = new Roulette(f);
//        System.out.println(r);
//        System.out.println(r.numeroUscitoPiuDiFrequenteDopo(12));
//    }
//
//
//
//
//}


public class Roulette {

    private Map<Integer, TreeMap<Integer, Integer>> adiacenze = new TreeMap<>();
    private Set<Integer> usciti  = new TreeSet<>();

    public Roulette(File file) throws IOException {
        if (!file.exists()) throw new FileNotFoundException();

        String numeri = "(0|[1-9]|[1-2][0-9]|3[0-6])";
        String line = numeri + "(\\s+" + numeri + ")*";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.matches(line)) throw new IllegalArgumentException("Linea non valida");

                StringTokenizer st = new StringTokenizer(linea, " ");
                int precedente = -1;

                while (st.hasMoreTokens()) {
                    int numero = Integer.parseInt(st.nextToken());
                    usciti.add(numero);

                    adiacenze.putIfAbsent(numero, new TreeMap<>());

                    if (precedente != -1) {
                        adiacenze.get(precedente).put(numero,
                                adiacenze.get(precedente).getOrDefault(numero, 0) + 1);
                    }

                    precedente = numero;
                }
            }
        }
    }

    public boolean eUscito(int x) {
        return usciti.contains(x);
    }

    public int numeroUscitoPiuDiFrequenteDopo(int x) {
        if (!eUscito(x) || !adiacenze.containsKey(x)) return -1;

        return adiacenze.get(x).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Roulette\n");

        for (Map.Entry<Integer, TreeMap<Integer, Integer>> entry : adiacenze.entrySet()) {
            sb.append("Il numero " + entry.getKey() + " è seguito da ");
            for (Map.Entry<Integer, Integer> subEntry : entry.getValue().entrySet()) {
                sb.append(subEntry.getKey() + ": " + subEntry.getValue() + " volte   ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        String roulette = "C:\\Users\\vogli\\OneDrive\\Documenti\\Roulette.txt";
        File f = new File(roulette);
        Roulette r = new Roulette(f);
        System.out.println(r);
        System.out.println("Numero più frequente dopo 12: " + r.numeroUscitoPiuDiFrequenteDopo(12));
    }
}

