package poo.tracce.appelloArtefatto;

import java.util.List;

public class Artefatto {

    private String nome, descrizione;
    private List<String> componenti;

    public Artefatto(String nome, String descrizione, List<String> componenti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.componenti = componenti;
    }

    public Artefatto( Artefatto a ){
        this.nome = a.getNome();
        this.descrizione = a.getDescrizione();
        this.componenti = a.getComponenti();
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public List<String> getComponenti() {
        return componenti;
    }


}
