package poo.tracce.SaggioMusicale;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SaggioMusicaleImpl implements SaggioMusicale {

    Map<String,String> musicistaPrimoStrumento = new HashMap<>();
    Map<String,String> musicistaSecondoStrumento = new HashMap<>();

    @Override
    public void aggiungiMusicistaEStrumento(String musicista, String strumento1, String strumento2) throws IllegalArgumentException {
        musicistaPrimoStrumento.put(musicista, strumento1);
        musicistaSecondoStrumento.put(musicista, strumento2);
    }

    @Override
    public Set<String> dammiMusicisti(String[] strumenti) throws IllegalArgumentException {
        Set<String> musicisti = new HashSet<>();
        for(String strumento: strumenti){
            // Check first instruments
            for(String musicista: musicistaPrimoStrumento.keySet()){
                if(strumento.equals(musicistaPrimoStrumento.get(musicista))){
                    musicisti.add(musicista);
                }
            }
            // Check second instruments
            for(String musicista: musicistaSecondoStrumento.keySet()){
                if(strumento.equals(musicistaSecondoStrumento.get(musicista))){
                    musicisti.add(musicista);
                }
            }
        }
        return musicisti;
    }

    @Override
    public Set<String> dammiStrumenti(String[] musicisti) throws IllegalArgumentException {
        Set<String> strumenti = new HashSet<>();
        for(String musicista: musicisti){
            if(musicistaPrimoStrumento.containsKey(musicista)){
                strumenti.add(musicistaPrimoStrumento.get(musicista));
            }
            if(musicistaSecondoStrumento.containsKey(musicista)){
                strumenti.add(musicistaSecondoStrumento.get(musicista));
            }
        }
        return strumenti;
    }

    @Override
    public Set<String> musicisti() {
        Set<String> musicisti = new HashSet<>();
        musicisti.addAll(musicistaPrimoStrumento.keySet());
        musicisti.addAll(musicistaSecondoStrumento.keySet());
        return musicisti;
    }

    @Override
    public Set<String> strumenti() {
        Set<String> strumenti = new HashSet<>();
        strumenti.addAll(musicistaPrimoStrumento.values());
        strumenti.addAll(musicistaSecondoStrumento.values());
        return strumenti;
    }

    @Override
    public boolean suonaComePrimoStrumento(String musicista, String strumento) {
        for(String musicista2: musicistaPrimoStrumento.keySet()){
            if(musicista.equals(musicista2)){
                if(!(musicistaPrimoStrumento.get(musicista).equals(strumento))){
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean suonaComeSecondoStrumento(String musicista, String strumento) {
        for(String musicista2: musicistaSecondoStrumento.keySet()){
            if(musicista.equals(musicista2)){
                if(!(musicistaSecondoStrumento.get(musicista).equals(strumento))){
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean esisteStrumentoSuonato(String strumento) {
        for(String strumento1: musicistaPrimoStrumento.values()){
            if(strumento.equals(strumento1)){
                return true;
            }
        }
        for(String strumento2: musicistaSecondoStrumento.values()){
            if(strumento.equals(strumento2)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean esisteMusicista(String musicista) {
        for(String musicista1: musicistaPrimoStrumento.keySet()){
            if(musicista1.equals(musicista)){
                return true;
            }
        }
        for(String musicista2: musicistaSecondoStrumento.keySet()){
            if(musicista2.equals(musicista)){
                return true;
            }
        }
        return false;
    }
}
