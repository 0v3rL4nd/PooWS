package poo.tracce.BigInt;

import java.util.Objects;

public abstract class AbstractBigInt implements BigInt {

    @Override
    public String toString() {
        return value();
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (!(o instanceof BigInt)) return false;
        BigInt other = (BigInt) o;
        if(this.length()==other.length()){
            for(int i=0; i<this.length(); i++){
                if(this.value().charAt(i)!=other.value().charAt(i)) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31*value().hashCode();
    }
}