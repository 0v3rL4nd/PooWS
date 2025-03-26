package poo.tracce.appello1907024;

import java.util.*;

public class BagImpl<T> extends AbstractBag<T> {
    private final Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T element) {
        map.put(element, map.getOrDefault(element, 0) + 1);
    }



    @Override
    public int multiplicity(T element) {
        return map.getOrDefault(element, 0);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int cardinality() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public Bag<T> factory() {
        return new BagImpl<>();
    }

    @Override
    public boolean included(Bag<T> b) {
        for (T x : b) {
            if (multiplicity(x) < b.multiplicity(x)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Bag<T> union(Bag<T> b) {
        Bag<T> result = factory();
        for (T x : this) {
            result.add(x, Math.max(multiplicity(x), b.multiplicity( x )));
        }
        for (T x : b) {
            if (!this.map.containsKey(x)) {
                result.add(x, b.multiplicity( x ));
            }
        }
        return result;
    }

    @Override
    public void addAll(Bag<T> b) {
        for (T x : b) {
            add(x);
        }
    }

    @Override
    public Bag<T> difference(Bag<T> b) {
        Bag<T> result = factory();
        for (T x : b) {
            int diff = Math.max(multiplicity(x), b.multiplicity( x ));
            if (diff > 0) {
                result.add(x, diff);
            }
        }
        return result;
    }

    @Override
    public Bag<T> intersection(Bag<T> b) {
        Bag<T> result = factory();
        for (T x : b) {
            int min = Math.min(multiplicity(x), b.multiplicity(x));
            if (min > 0) {
                result.add(x, min);
            }
        }
        return result;
    }

    @Override
    public void add(T x, int multiplicity){
        map.put(x, map.getOrDefault(x, 0) + multiplicity);
    }

    @Override
    public boolean remove(T x) {
        return map.remove(x, map.getOrDefault(x, 0) - 1);
    }

    public static void main(String[] args) {
        Bag<String> b1 = new BagImpl<>();
        b1.add("a");
        b1.add("b");
        b1.add("c");
        b1.add("d");
        b1.add("e");
        b1.add("f");
        b1.add("g");
        b1.add("h");
        b1.add("i");
        b1.add("j");
        b1.add("k");

        Bag<String> b2 = new BagImpl<>();
        b2.add("a");
        b2.add("b");
        b2.add("c");
        b2.add("d");
        b2.add("e");
        b2.add("f");
        b2.add("g");
        b2.add("h");
        b2.add("i");
        b2.add("j");
        b2.add("k");

        System.out.println(b1.union(b2));
        System.out.println(b1.difference(b2));
        System.out.println(b1.intersection(b2));
        System.out.println(b1.included(b2));
        System.out.println(b2.included(b1));
        System.out.println(b1.multiplicity("a"));
        System.out.println(b1.multiplicity("b"));
        System.out.println(b1.multiplicity("c"));
        System.out.println(b1.multiplicity("d"));
        System.out.println(b1.multiplicity("e"));
        System.out.println(b1.multiplicity("f"));
        System.out.println(b1.multiplicity("g"));
        System.out.println(b1.multiplicity("h"));
        System.out.println(b1.multiplicity("i"));
        System.out.println(b1.multiplicity("j"));

        System.out.println(b2.multiplicity("a"));
        System.out.println(b2.multiplicity("b"));

        System.out.println(b1.remove("a"));
        System.out.println(b1.remove("b"));
    }
}
