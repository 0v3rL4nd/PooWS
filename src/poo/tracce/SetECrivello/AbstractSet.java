package poo.tracce.SetECrivello;

abstract class AbstractSet<T> implements Set<T> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (T element : this) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(element);
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Set)) return false;

        Set<T> other = (Set<T>) obj;
        if (this.size() != other.size()) return false;

        for (T element : this) {
            if (!other.contains(element)) {
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