package poo.tracce.DequeueRPN;

import java.util.*;

/**
 * Classe astratta che implementa DeQueue e fornisce i metodi canonici
 */
abstract class AbstractDequeue<T> implements DeQueue<T> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (T element : this) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(element);
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DeQueue)) return false;

        DeQueue<?> other = (DeQueue<?>) obj;
        if (this.size() != other.size()) return false;

        Iterator<T> thisIter = this.iterator();
        Iterator<?> otherIter = other.iterator();

        while (thisIter.hasNext() && otherIter.hasNext()) {
            if (!Objects.equals(thisIter.next(), otherIter.next())) {
                return false;
            }
        }

        return !thisIter.hasNext() && !otherIter.hasNext();
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (T element : this) {
            hash = 31 * hash + Objects.hashCode(element);
        }
        return hash;
    }
}