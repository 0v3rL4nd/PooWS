package poo.tracce.Sudoku;

class Cella {
    private int valore;
    private Stato stato;

    public Cella() {
        this.valore = 0;
        this.stato = Stato.NON_ASSEGNATO;
    }

    public Cella(int valore, Stato stato) {
        this.valore = valore;
        this.stato = stato;
    }

    public int getValore() { return valore; }
    public void setValore(int valore) { this.valore = valore; }
    public Stato getStato() { return stato; }
    public void setStato(Stato stato) { this.stato = stato; }

    public boolean isVuota() { return valore == 0; }
    public void svuota() {
        if (stato != Stato.IMPOSTATO) {
            valore = 0;
            stato = Stato.NON_ASSEGNATO;
        }
    }
}