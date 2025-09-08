package poo.tracce.Testo;

import java.util.ArrayList;
import java.util.List;

public interface Testo extends Iterable<String> {
    default int size() {
        int conta = 0;
        for (String t : this) conta++;
        return conta;
    }

    void add(String parola);

    default int frequenza(String parola) {
        int conta = 0;
        for (String t : this)
            if (t.equals(parola)) conta++;
        return conta;
    }

    default String moda() {
        List<String> moda = new ArrayList<>();
        int max = 0;
        for (String t : this) {
            int freq = frequenza(t);
            if (freq > max) {
                moda.clear();
                moda.add(t);
                max = freq;
            } else if (freq == max && !moda.contains(t)) {
                moda.add(t);
            }
        }
        return moda.toString();
    }

    Testo factory();

    default Testo paroleInComune(Testo t) {
        Testo t1 = factory();
        for (String s : this)
            if (t.frequenza(s) > 0 && t1.frequenza(s) == 0)
                t1.add(s);
        return t1;
    }

    // CORREZIONE PRINCIPALE: Formula della similarità del coseno corretta
    default double similaritaCoseno(Testo t) {
        // Calcola il prodotto scalare (numeratore)
        double prodottoScalare = 0;
        for (String s : this) {
            prodottoScalare += frequenza(s) * t.frequenza(s);
        }

        // Calcola la norma del primo vettore
        double norma1 = 0;
        for (String s : this) {
            norma1 += Math.pow(frequenza(s), 2);
        }
        norma1 = Math.sqrt(norma1);

        // Calcola la norma del secondo vettore
        double norma2 = 0;
        for (String s : t) {
            norma2 += Math.pow(t.frequenza(s), 2);
        }
        norma2 = Math.sqrt(norma2);

        // Evita divisione per zero
        if (norma1 == 0 || norma2 == 0) return 0;

        return prodottoScalare / (norma1 * norma2);
    }
}
