package poo.tracce.AlberoEspressione_Permutazioni_Cruciverba;

class NodoOperatore extends Nodo {
    private char operatore;
    private Nodo sinistro, destro;

    public NodoOperatore(char operatore, Nodo sinistro, Nodo destro) {
        this.operatore = operatore;
        this.sinistro = sinistro;
        this.destro = destro;
    }

    @Override
    int calcola() {
        int valSin = sinistro.calcola();
        int valDes = destro.calcola();

        switch (operatore) {
            case '+': return valSin + valDes;
            case '-': return valSin - valDes;
            case '*': return valSin * valDes;
            case '/':
                if (valDes == 0) throw new ArithmeticException("Divisione per zero");
                return valSin / valDes;
            default: throw new IllegalArgumentException("Operatore non valido: " + operatore);
        }
    }

    @Override
    public String tostring() {
        return "(" + sinistro + " " + operatore + " " + destro + ")";
    }

    public Nodo getSinistro() { return sinistro; }
    public Nodo getDestro() { return destro; }
    public char getOperatore() { return operatore; }
}