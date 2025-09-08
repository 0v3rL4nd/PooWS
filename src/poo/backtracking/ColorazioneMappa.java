package poo.backtracking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

enum Colori {
    ROSSO, VERDE, GIALLO, MARRONE
}

public class ColorazioneMappa extends Backtracking<Integer, Colori> {

    private int n;
    private Set<Integer>[] confinanti;
    private Map<Integer, Colori> assegnazioni; // mappa regione -> colore assegnato
    private int numSol;

    public ColorazioneMappa(Set<Integer>[] confinanti) {
        this.n = confinanti.length;
        this.confinanti = new HashSet[n];
        this.assegnazioni = new HashMap<>();
        this.numSol = 0;

        for (int i = 0; i < n; ++i) {
            this.confinanti[i] = new HashSet<>(confinanti[i]);
        }
    }

    @Override
    protected boolean assegnabile(Integer regione, Colori colore) {
        // Verifica se il colore può essere assegnato alla regione
        Set<Integer> regioniConfinanti = this.confinanti[regione];

        for (Integer regioneConfinante : regioniConfinanti) {
            // Se una regione confinante ha già lo stesso colore, non è assegnabile
            if (assegnazioni.containsKey(regioneConfinante) &&
                    assegnazioni.get(regioneConfinante).equals(colore)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void assegna(Integer regione, Colori colore) {
        assegnazioni.put(regione, colore);
    }

    @Override
    protected void deassegna(Integer regione, Colori colore) {
        assegnazioni.remove(regione);
    }

    @Override
    protected void scriviSoluzione(Integer ultimaRegione) {
        numSol++;
        System.out.println("Soluzione " + numSol + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("  Regione " + i + ": " + assegnazioni.get(i));
        }
        System.out.println();
    }

    @Override
    protected boolean esisteSoluzione(Integer ultimaRegione) {
        // La soluzione esiste quando tutte le regioni sono state colorate
        return assegnazioni.size() == n;
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> punti = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            punti.add(i);
        }
        return punti;
    }

    @Override
    protected Collection<Colori> scelte(Integer regione) {
        Collection<Colori> scelte = new ArrayList<>();
        for (Colori colore : Colori.values()) {
            scelte.add(colore);
        }
        return scelte;
    }

    public static void main(String[] args) throws IOException {
        int n = 6;
        Set<Integer> conf[] = new HashSet[n];

        // Esempio di grafo di confinanze
        conf[0] = new HashSet<>(java.util.Arrays.asList(1, 4));
        conf[1] = new HashSet<>(java.util.Arrays.asList(0, 4, 5, 2));
        conf[2] = new HashSet<>(java.util.Arrays.asList(1, 5, 3));
        conf[3] = new HashSet<>(java.util.Arrays.asList(2));
        conf[4] = new HashSet<>(java.util.Arrays.asList(0, 1, 5));
        conf[5] = new HashSet<>(java.util.Arrays.asList(1, 2, 4));

        System.out.println("Ricerca soluzioni per la colorazione della mappa...");
        ColorazioneMappa cm = new ColorazioneMappa(conf);
        cm.risolvi();

        if (cm.numSol == 0) {
            System.out.println("Nessuna soluzione trovata!");
        } else {
            System.out.println("Totale soluzioni trovate: " + cm.numSol);
        }
    }
}