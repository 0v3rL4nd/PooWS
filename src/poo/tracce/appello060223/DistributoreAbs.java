package poo.tracce.appello060223;

import java.util.List;
import java.util.Objects;

public abstract class DistributoreAbs {
    List<Prodotto> prodotti;

    @Override
    public String toString() {
        return "DistributoreAbs{" +
                "prodotti=" + prodotti +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DistributoreAbs that)) return false;
        return Objects.equals(prodotti, that.prodotti);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prodotti);
    }
}
