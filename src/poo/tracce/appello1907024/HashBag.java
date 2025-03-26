package poo.tracce.appello1907024;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class HashBag<T> extends AbstractBag<T> {
    private final Map<T, Integer> map = new HashMap<>();

    @Override
    public int cardinality () {
        return map.size();
    }

    @Override
    public boolean isEmpty () {
        return cardinality() == 0;
    }

    @Override
    public void clear () {
        map.clear();
    }

    @Override
    public int multiplicity (T x) {
        return map.get(x);
    }

    @Override
    public void add (T x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    @Override
    public void add (T x, int multiplicity) {
        map.put(x, map.getOrDefault(x, 0) + multiplicity);
    }

    @Override
    public void addAll (Bag<T> bag) {
        for (T x : bag) {
            add(x);
        }
    }

    @Override
    public boolean remove (T x) {
        return map.remove(x, map.getOrDefault(x, 0) - 1);
    }

    @Override
    public boolean removeAll (T x){
        return map.remove(x, map.getOrDefault(x, 0) - 1);
    }

    @Override
    public Bag<T> factory(){
        return new HashBag<>();
    }

    @Override
    public boolean included( Bag<T> b){
        for(T x : b){
            if( this.multiplicity(x) > b.multiplicity(x)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Bag<T> union( Bag<T> b){
        Bag<T> result = factory();
        for( T x: this ){
            result.add( x, Math.max( this.multiplicity(x), b.multiplicity( x )) );
        }
        for( T x : b ){
            if( !this.map.containsKey(x) ){
                result.add( x, b.multiplicity( x ) );
            }
        }
        return result;
    }

    @Override
    public Bag<T> intersection( Bag<T> b){
        Bag<T> result = factory();
        for( T x : b ){
            int min = Math.min( this.multiplicity( x ), b.multiplicity( x ) );
            if( min > 0){
                result.add( x, min );
            }
        }
        return result;
    }

    @Override
    public Bag<T> difference( Bag<T> b){
        Bag<T> result = factory();
        for( T x : b ){
            int diff = Math.max( this.multiplicity( x ), b.multiplicity( x ) );
            if( diff > 0){
                result.add( x, diff );
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator(){
        return map.keySet().iterator();
    }
}
