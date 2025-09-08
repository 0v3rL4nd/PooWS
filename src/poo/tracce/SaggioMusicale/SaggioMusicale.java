package poo.tracce.SaggioMusicale;

import java.util.Set;

public interface SaggioMusicale {

    void aggiungiMusicistaEStrumento(String musicista, String strumento1, String strumento2) throws IllegalArgumentException;
    Set<String> dammiMusicisti(String[] strumenti) throws IllegalArgumentException;
    Set<String> dammiStrumenti(String[] musicisti) throws IllegalArgumentException;
    Set<String> musicisti();
    Set<String> strumenti();
    boolean suonaComePrimoStrumento(String musicista, String strumento);
    boolean suonaComeSecondoStrumento(String musicista, String strumento);
    boolean esisteStrumentoSuonato(String strumento);
    boolean esisteMusicista(String musicista);

}
