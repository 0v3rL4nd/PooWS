package poo.tracce.AlberoEspressione_Permutazioni_Cruciverba;

class NodoOperando extends Nodo {
    private int valore;

    public NodoOperando(int valore) {
        this.valore = valore;
    }

    @Override
    int calcola() {
        return valore;
    }

    @Override
    public String tostring() {
        return String.valueOf(valore);
    }
}