package poo.tracce.appello120221;

import poo.backtracking.Backtracking;
import java.util.*;
import static java.util.Arrays.asList;

/*
 * è assegnato un dizionario D, ossia un insieme finito i parole amissibili,
 * ed una stringa particolare S. Si chiede di verifiare se S sia scomponibile in una successione orginata di sottostringhe che siano parole ammissibili in D.
 * Se S è scomponibile, fornire su output la sequenza di parole di D, separate da uno spazio, tale che la loro concatenazione è uguale a S.
 * Diversamente, scrivere che S non è decomponibile.
 * Sviluppare, dunque, una classe Verifica erede di Backtracking, che a tempo di costruzione riceve il dizionario D (un Set<Strin>) e la stringa S.
 * L'operazione di verifica di S rispetto a D, avviata lanciando il metodo risolvi(), deve basarsi su un array di stringhe aux, della stessa capacità del dizionario.
 * I punti di scelta sono gli indici di aux. Le scelte, per ogni punto di scelta, sono le parole di D.
 * Si scriva poi, un main in grado di testare la classe Verifica.
 */

public class Verifica extends Backtracking<Integer, String> {

    private Set<String> D;
    private String S;
    private String[] aux;
    private boolean flag = false;

    public Verifica(Set<String> D, String S) {
        this.D = new TreeSet<>(D);
        this.S = S;
        aux = new String[S.length()];
        Arrays.fill(aux, "");
    }

    @Override
    protected boolean assegnabile(Integer index, String word) {
        return D.contains(word);
    }

    @Override
    protected void assegna(Integer index, String word) {
        aux[index] = word;
    }

    @Override
    protected void deassegna(Integer index, String word) {
        aux[index] = "";
    }

    @Override
    protected void scriviSoluzione(Integer steps) {
            System.out.println(String.join(" ", Arrays.copyOf(aux, steps)));
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> ps = new ArrayList<>();
        for (int i = 0; i < aux.length; i++) {
            ps.add(i);
        }
        return ps;
    }

    @Override
    protected Collection<String> scelte(Integer index) {
        List<String> choices = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < index; i++) {
            j += aux[i].length();
        }
        for (int k = j + 1; k <= S.length(); k++) { // Correzione dell'indice k
            String word = S.substring(j, k);
            if (D.contains(word)) {
                choices.add(word);
            }
        }
        return choices;
    }

    public static void main(String[] args) {
        Set<String> D = new TreeSet<>(asList("il", "gatto", "veloce", "salta", "il", "cane"));
        String S = "ilgattoveloce";
        Verifica v = new Verifica(D, S);
        v.risolvi();
    }
}
