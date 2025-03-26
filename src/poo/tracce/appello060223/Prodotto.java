package poo.tracce.appello060223;

import java.util.Objects;

public class Prodotto {
    String nome;
    double prezzo;
    double quantita;

    public Prodotto(String nome, double prezzo, double quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public Prodotto( Prodotto p){
        this.nome = p.nome;
        this.prezzo = p.prezzo;
        this.quantita = p.quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getNome() {
        return nome;
    }

    public double getQuantita() {
        return quantita;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Prodotto prodotto)) return false;
        return Double.compare(prezzo, prodotto.prezzo) == 0 && Objects.equals(nome, prodotto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, prezzo);
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }
}
