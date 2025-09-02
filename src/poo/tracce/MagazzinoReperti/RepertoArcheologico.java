package poo.tracce.MagazzinoReperti;

import java.util.Objects;

//Un RepertoArcheologico è caratterizzato da un nome, una descrizione, da un peso e da un ID numerico.
// La classe RepertoArcheologico dovrà essere equipaggiata di opportuni metodi accessori
// nonché i classici metodi toString(), equals() e hashCode().
public class RepertoArcheologico {
    String nome;
    String descrizione;
    int peso;
    int counter = 0;

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getPeso() {
        return peso;
    }

    public int getId() {
        return counter;
    }

    public RepertoArcheologico(String nome, String descrizione, int peso) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.peso = peso;
        counter++;
    }

    public String toString(){
        return "Nome: " + this.getNome() + "; \n" + "Descrizione: " + this.getDescrizione() + "; \n" + "Peso: " + this.getPeso() + "; \n" + "ID: " + this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RepertoArcheologico that)) return false;
        return peso == that.peso && Objects.equals(nome, that.nome) && Objects.equals(descrizione, that.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descrizione, peso);
    }
}
