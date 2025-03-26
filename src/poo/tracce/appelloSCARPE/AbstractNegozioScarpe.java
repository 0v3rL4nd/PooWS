package poo.tracce.appelloSCARPE;

import java.util.Objects;

public abstract class AbstractNegozioScarpe implements NegozioScarpe{

    String modelloScarpa;
    int misura;

    public AbstractNegozioScarpe(int misura, String modelloScarpa) {
        this.misura = misura;
        this.modelloScarpa = modelloScarpa;
    }

    public AbstractNegozioScarpe( AbstractNegozioScarpe n) {
        this.misura = n.misura;
        this.modelloScarpa = n.modelloScarpa;
    }

    public String getModelloScarpa() {
        return modelloScarpa;
    }

    public int getMisura() {
        return misura;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AbstractNegozioScarpe that)) return false;
        return misura == that.misura && Objects.equals(modelloScarpa, that.modelloScarpa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelloScarpa, misura);
    }

    @Override
    public String toString() {
        return "AbstractNegozioScarpe{" +
                "modelloScarpa='" + scarpeDisponibili() + '\'' +
                ", misura=" + misureDisponibili( this.modelloScarpa ) +
                '}';
    }
}
