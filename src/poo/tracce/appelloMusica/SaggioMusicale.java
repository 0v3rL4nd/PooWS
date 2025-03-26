package poo.tracce.appelloMusica;

import java.util.Set;

public interface SaggioMusicale {

    void aggiungiMusicistaEStrumento( String musicista, String strumento1, String strumento2) throws IllegalArgumentException;
    Set<String> getStrumenti(String[] musicista );
    Set<String> getMusici(String[] strumento );
    Set<String> musicisti();
    Set<String> strumenti();
    boolean suonaComePrimoStrumento( String musicista, String strumento);
    boolean suonaComeSecondoStrumento( String musicista, String strumento);
    boolean esisteStrumentoSuonato( String strumento);
    boolean esisteMusicista( String musicista);

}
