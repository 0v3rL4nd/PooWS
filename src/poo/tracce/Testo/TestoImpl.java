package poo.tracce.Testo;

import java.util.*;

public class TestoImpl extends TestoABS {
    private List<String> parole = new ArrayList<>();
    private List<Integer> occorrenze = new ArrayList<>();

    public TestoImpl(List<String> parole, List<Integer> occorrenze) {
        this.parole = new ArrayList<>(parole);
        this.occorrenze = new ArrayList<>(occorrenze);
    }

    public TestoImpl(TestoImpl t) {
        this.parole = new ArrayList<>(t.getParole());
        this.occorrenze = new ArrayList<>(t.getOccorrenze());
    }

    public List<String> getParole() {
        return new ArrayList<>(parole);
    }

    public List<Integer> getOccorrenze() {
        return new ArrayList<>(occorrenze);
    }

    @Override
    public void add(String parola) {
        // CORREZIONE: Ignora parole vuote
        if (parola == null || parola.trim().isEmpty()) return;

        parola = parola.trim();

        if (!parole.contains(parola)) {
            parole.add(parola);
            occorrenze.add(1);
        } else {
            int pos = parole.indexOf(parola);
            // CORREZIONE: Era scritto +1 invece di incrementare
            occorrenze.set(pos, occorrenze.get(pos) + 1);
        }
    }

    @Override
    public int frequenza(String parola) {
        int pos = parole.indexOf(parola);
        return pos >= 0 ? occorrenze.get(pos) : 0;
    }

    @Override
    public Testo factory() {
        return new TestoImpl(new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public Iterator<String> iterator() {
        // CORREZIONE: Era scritto "Iterator()" con la I maiuscola
        return parole.iterator();
    }
}