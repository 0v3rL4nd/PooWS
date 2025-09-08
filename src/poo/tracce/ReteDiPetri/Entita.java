package poo.tracce.ReteDiPetri;

abstract class Entita {
    protected String nome;

    public Entita(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Metodi astratti per equals, hashCode e toString
    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}