package Roulettee;

import java.io.*;
import java.util.*;

public class Roulette {
    Map<Integer, TreeMap<Integer, Integer>> statistica = new TreeMap<>();
    static String numero = "([0-9]|[1-2][0-9]|3[0-6])";
    static String regex = numero + "((\\s+)" + numero + ")*";

    public Roulette(File f) throws IOException {
        if (!f.exists()) throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(f));
        List<Integer> lista = new LinkedList<>();
        for (; ; ) {
            try {
                String linea = br.readLine();
                if (linea == null) break;
                linea = linea.trim();
                if (!linea.matches(regex)) throw new IllegalArgumentException("Linea non valida");
                System.out.println("Ho letto la linea " + linea);
                StringTokenizer st = new StringTokenizer(linea, " \n");
                while (st.hasMoreTokens()) {
                    Integer corrente = Integer.parseInt(st.nextToken());
                    if (!statistica.containsKey(corrente)) {
                        statistica.put(corrente, new TreeMap<>());
                    }
                    lista.add(corrente);
                }
            } catch (IOException e) {
                break;
            }
        }
        br.close();
        ListIterator<Integer> lit = lista.listIterator(1);
        Integer pre = lit.previous();
        lit.next();
        Integer curr = lit.next();
        System.out.println(pre + " " + curr);
        TreeMap<Integer, Integer> valore = statistica.get(pre);
        if (!valore.containsKey(curr)) {
            valore.put(curr, 1);
        }
        while (lit.hasNext()) {
            pre = lit.previous();
            lit.next();
            curr = lit.next();
            System.out.println(pre + " " + curr);
            valore = statistica.get(pre);
            if (!valore.containsKey(curr)) {
                valore.put(curr, 1);
            } else {
                valore.put(curr, valore.get(curr) + 1);
            }
        }
        System.out.println(statistica);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("Roulette\n");
        for (Integer i : statistica.keySet()) {
            sb.append("Il numero " + i + " è seguito da   ");
            for (Integer val : statistica.get(i).keySet()) {
                sb.append(val + ":" + statistica.get(i).get(val) + " volte   ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean eUscito(int x) {
        return statistica.containsKey(x);
    }

    public int numeroUscitoPiuDiFrequenteDopo(int x) {
        if (!eUscito(x)) return -1;
        Integer valore = 0;
        for (Integer i : statistica.keySet()) {
            if (i == x) {
                Integer max = Integer.MIN_VALUE;
                for (Integer val : statistica.get(i).keySet()) {
                    if (statistica.get(i).get(val) > max) {
                        max = statistica.get(i).get(val);
                        valore = val;
                    }
                }
            }
        }
        return valore;
    }

    public static void main(String[] args) throws IOException {
        String roulette = "C:\\poo-file\\Roulette\\Roulette.txt";
        File f = new File(roulette);
        Roulette r = new Roulette(f);
        System.out.println(r);
        System.out.println(r.numeroUscitoPiuDiFrequenteDopo(12));
    }
}
