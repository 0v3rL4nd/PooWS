package poo.tracce.MatriceSparsa_AlberoBinarioDiRicerca;

import java.util.Objects;

public abstract class MatriceSparsaAstratta implements MatriceSparsa {
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MatriceSparsa)) return false;
        MatriceSparsa other = (MatriceSparsa) obj;
        if (this.getRighe() != other.getRighe()) return false;

        for (Elemento e : this.elementi()) {
            if (other.get(e.getI(), e.getJ()) != e.getV()) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRighe(), elementi());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Elemento e : elementi()) {
            sb.append(e).append(" ");
        }
        return sb.toString();
    }

    protected abstract Iterable<Elemento> elementi();
}
