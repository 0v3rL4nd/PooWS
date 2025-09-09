package poo.tracce.Bag;

//non giusto
public class Main {
    public static void main(String[] args) {
        System.out.println("Test dell'implementazione BagImpl corretta:");
        System.out.println("===========================================");

        // Test basic operations
        BagImpl<String> bag1 = new BagImpl<>();
        bag1.add("apple");
        bag1.add("banana");
        bag1.add("apple");
        bag1.add("cherry");

        System.out.println("Bag1: " + bag1);
        System.out.println("Cardinalità: " + bag1.cardinality());
        System.out.println("Molteplicità di 'apple': " + bag1.multiplicity("apple"));

        // Test add with multiplicity
        BagImpl<String> bag2 = new BagImpl<>();
        bag2.add("apple", 2);
        bag2.add("banana", 1);
        bag2.add("orange", 3);

        System.out.println("\nBag2: " + bag2);
        System.out.println("Cardinalità: " + bag2.cardinality());

        // Test remove operations
        System.out.println("\nRimozione di un 'apple' da bag1: " + bag1.remove("apple"));
        System.out.println("Bag1 dopo rimozione: " + bag1);

        BagImpl<String> bag3 = new BagImpl<>();
        bag3.add("apple", 3);
        bag3.add("banana", 1);
        System.out.println("\nBag3 prima di removeAll: " + bag3);
        bag3.removeAll("apple");
        System.out.println("Bag3 dopo removeAll('apple'): " + bag3);

        // Test set operations
        BagImpl<String> bagA = new BagImpl<>();
        bagA.add("a", 2);
        bagA.add("b", 1);
        bagA.add("c", 3);

        BagImpl<String> bagB = new BagImpl<>();
        bagB.add("a", 1);
        bagB.add("b", 2);
        bagB.add("d", 1);

        System.out.println("\nOperazioni su insiemi:");
        System.out.println("BagA: " + bagA);
        System.out.println("BagB: " + bagB);
        System.out.println("Union: " + bagA.union(bagB));
        System.out.println("Intersection: " + bagA.intersection(bagB));
        System.out.println("Difference (A-B): " + bagA.difference(bagB));
        System.out.println("A incluso in B: " + bagA.included(bagB));
        System.out.println("B incluso in A: " + bagB.included(bagA));

        // Test iterator
        System.out.println("\nIterazione su bagA:");
        for(String item : bagA) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

}
