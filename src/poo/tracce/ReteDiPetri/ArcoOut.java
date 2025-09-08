package poo.tracce.ReteDiPetri;

class ArcoOut extends Arco {
    public ArcoOut(Posto posto, int peso) {
        super(posto, peso);
    }

    public ArcoOut(Posto posto) {
        super(posto, 1);
    }

    @Override
    public String toString() {
        return "ArcoOut[" + posto.getNome() + ", peso=" + peso + "]";
    }
}