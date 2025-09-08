package poo.tracce.Testo;

public abstract class TestoABS implements Testo {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Testo[");
        boolean first = true;
        for (String s : this) {
            if (!first) sb.append(", ");
            sb.append(s).append(":").append(frequenza(s));
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (String s : this) {
            h = h * 31 + s.hashCode();
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Testo)) return false;
        if (this == obj) return true;
        Testo t = (Testo) obj;
        // Due testi sono uguali se hanno similarità del coseno = 1
        return Math.abs(this.similaritaCoseno(t) - 1.0) < 1e-10;
    }
}