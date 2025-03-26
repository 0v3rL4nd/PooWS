package poo.tracce.appelloArtefatto;

import java.util.Objects;

public abstract class BuilderAbt implements Builder {

    private Artefatto artefatti;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Artefatto) ) return false;
        Artefatto a = (Artefatto) o;
        return artefatti.getNome().equals(a.getNome());
    }

    @Override
    public int hashCode() {

        final int n = 7;
        int h = 0;
        h = h * n + artefatti.getNome().hashCode();
        h = h * n + artefatti.getDescrizione().hashCode();
        return h;

    }

    public String toString(){
        return "Builder{" +
                "artefatti=" + artefatti +
                '}';
    }
}
