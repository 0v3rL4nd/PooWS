package poo.appello_11_09_2025;

public abstract class InsiemeAstratto<T extends Comparable<? super T>> implements Insieme<T> {

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (T x : this) {
            sb.append(x).append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Insieme)) return false;
        Insieme<T> other = (Insieme<T>) obj;
        if (this.cardinalità() != other.cardinalità()) return false;
        for (T element : this) {
            if (!other.contiene(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (T element : this) {
            if (element != null) {
                hash += element.hashCode();
            }
        }
        return hash;
    }
}
