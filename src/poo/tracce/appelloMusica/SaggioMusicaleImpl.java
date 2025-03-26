package poo.tracce.appelloMusica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.*;

public class SaggioMusicaleImpl extends SaggioMusicaleAbstract {

    private Map<String, List<String>> saggio = new HashMap<>();

    public SaggioMusicaleImpl() {
        // Costruttore vuoto
    }

    @Override
    public void aggiungiMusicistaEStrumento(String musicista, String strumento1, String strumento2) throws IllegalArgumentException {
        if (saggio.containsKey(musicista)) {
            throw new IllegalArgumentException("Musicista già esistente!");
        }

        List<String> strumenti = new ArrayList<>();
        strumenti.add(strumento1);
        strumenti.add(strumento2);
        saggio.put(musicista, strumenti);
    }


    @Override
    public boolean suonaComePrimoStrumento(String musicista, String strumento) {
        return saggio.containsKey(musicista) && saggio.get(musicista).get(0).equals(strumento);
    }

    @Override
    public boolean suonaComeSecondoStrumento(String musicista, String strumento) {
        return saggio.containsKey(musicista) && saggio.get(musicista).size() > 1 &&
                saggio.get(musicista).get(1).equals(strumento);
    }

    @Override
    public boolean esisteStrumentoSuonato(String strumento) {
        return saggio.values().stream().anyMatch(lista -> lista.contains(strumento));
    }

    @Override
    public boolean esisteMusicista(String musicista) {
        return saggio.containsKey(musicista);
    }

    @Override
    public Set<String> getStrumenti(String[] musicista) {
        Set<String> strumenti = new HashSet<>();
        for (String m : musicista) {
            if (saggio.containsKey(m)) {
                strumenti.addAll(saggio.get(m));
            }
        }
        return strumenti;
    }

    @Override
    public Set<String> getMusici(String[] strumento) {
        Set<String> musicisti = new HashSet<>();
        for (String s : strumento) {
            for (Map.Entry<String, List<String>> entry : saggio.entrySet()) {
                if (entry.getValue().contains(s)) {
                    musicisti.add(entry.getKey());
                }
            }
        }
        return musicisti;
    }

    @Override
    public Set<String> musicisti() {
        return saggio.keySet();
    }

    @Override
    public Set<String> strumenti() {
        Set<String> strumenti = new HashSet<>();
        for (List<String> lista : saggio.values()) {
            strumenti.addAll(lista);
        }
        return strumenti;
    }

    public static void main(String[] args) {
        SaggioMusicale saggio = new SaggioMusicaleImpl();
        saggio.aggiungiMusicistaEStrumento("Mario", "Chitarra", "Basso");
        saggio.aggiungiMusicistaEStrumento("Paolo", "Batteria", "Chitarra");
        saggio.aggiungiMusicistaEStrumento("Giuseppe", "Batteria", "Basso");

        System.out.println(saggio.suonaComePrimoStrumento("Mario", "Chitarra"));
        System.out.println(saggio.suonaComeSecondoStrumento("Mario", "Basso"));

        System.out.println(saggio.esisteStrumentoSuonato("Chitarra"));

        System.out.println(saggio.esisteMusicista("Mario"));

        System.out.println(saggio.getStrumenti(new String[]{"Mario", "Paolo"}));

        System.out.println(saggio.getMusici(new String[]{"Chitarra", "Basso"}));

        System.out.println(saggio.musicisti());
    }

}

