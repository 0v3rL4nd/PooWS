package poo.tracce.ReteDiPetri;

abstract class Arco {
    protected Posto posto;
    protected int peso;

    public Arco(Posto posto, int peso) {
        this.posto = posto;
        this.peso = peso;
    }

    public Arco(Posto posto) {
        this(posto, 1); // peso di default = 1
    }

    public Posto getPosto() {
        return posto;
    }

    public int getPeso() {
        return peso;
    }
}
