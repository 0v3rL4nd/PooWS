package poo.tracce.ReteDiPetri;

class ArcoIn extends Arco {
    public ArcoIn(Posto posto, int peso) {
        super(posto, peso);
    }

    public ArcoIn(Posto posto) {
        super(posto, 1);
    }

    @Override
    public String toString() {
        return "ArcoIn[" + posto.getNome() + ", peso=" + peso + "]";
    }
}