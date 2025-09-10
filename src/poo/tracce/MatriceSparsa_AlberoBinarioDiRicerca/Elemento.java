package poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca;

// Classe ausiliaria per memorizzare gli elementi non zero
class Elemento {
    private int i, j; // coordinate
    private double v; // valore

    public Elemento(int i, int j, double v) {
        this.i = i;
        this.j = j;
        this.v = v;
    }

    public int getI() { return i; }
    public int getJ() { return j; }
    public double getV() { return v; }
    public void setV(double v) { this.v = v; }
}