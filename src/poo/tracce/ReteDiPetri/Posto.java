package poo.tracce.ReteDiPetri;

import java.util.Objects;

class Posto extends Entita {
    private int marcatura; // numero di token presenti

    // Costruttore con marcatura iniziale specificata
    public Posto(String nome, int marcaturaIniziale) {
        super(nome);
        this.marcatura = marcaturaIniziale;
    }

    // Costruttore senza marcatura (inizializza a 0 token)
    public Posto(String nome) {
        this(nome, 0);
    }

    public int getMarcatura() {
        return marcatura;
    }

    public void setMarcatura(int nuovaMarcatura) {
        this.marcatura = nuovaMarcatura;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Posto posto = (Posto) obj;
        return Objects.equals(nome, posto.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode()*31;
    }

    @Override
    public String toString() {
        return nome + "(" + marcatura + ")";
    }
}