package poo.tracce.appelloArtefatto;

import java.util.List;

public interface Builder extends Iterable<Artefatto>{

    void iniziaCostruzioneArtefatto( String nome, String descrizione) throws IllegalStateException;
    void artefattoCompletato( String nome) throws IllegalStateException;
    void aggiungiComponente( String nome, String componente) throws IllegalStateException;
    Artefatto dammiArtefatto( String nome);
    List<String> elencoArtefattiCompleti();
    List<String> elencoArtefattiInCostruzione();
    boolean esisteArtefatto( String nome);
    boolean rimuoviArtefatto( String nome);

}
