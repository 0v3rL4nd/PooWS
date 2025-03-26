package poo.tracce.appelloMusica;

import java.util.Objects;

public abstract class SaggioMusicaleAbstract implements SaggioMusicale {


    private String musicista, strumento1, strumento2;

    @Override
    public String toString() {
        return "SaggioMusicale{" +
                "musicista='" + musicista + '\'' +
                ", strumento1='" + strumento1 + '\'' +
                ", strumento2='" + strumento2 + '\'' +
                '}';
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaggioMusicaleAbstract that = (SaggioMusicaleAbstract) o;
        return this.musicista.equals(that.musicista) &&
                this.strumento1.equals(that.strumento1) &&
                this.strumento2.equals(that.strumento2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.musicista, this.strumento1, this.strumento2);
    }
}
