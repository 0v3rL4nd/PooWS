package poo.tracce.Bag;

abstract class BagABS<T> implements Bag<T>{

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(T elem: this){
            sb.append(elem);
            sb.append("(");
            sb.append(multiplicity(elem));
            sb.append("), ");
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Bag<?>)) return false;
        Bag<T> other = (Bag<T>) o;
        for(T elemOther: other){
            for(T elemThis: this){
                if(elemThis.equals(elemOther) && !(multiplicity(elemThis)==multiplicity(elemOther))) return false;
            }
            return false;
        }
        return true;
    }

    public int hashCode(){
        int h=31;
        for(T elem: this){
            h*= elem.hashCode() * multiplicity(elem);
        }
        return h;
    }
}
