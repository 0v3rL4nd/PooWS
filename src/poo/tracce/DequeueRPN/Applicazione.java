package poo.tracce.DequeueRPN;

public class Applicazione {

    /**
     * Main di test per la classe RPN
     */
    public static void main(String[] args) {
        System.out.println("=== TEST RPN CALCULATOR ===\n");

        // Array di espressioni di test
        String[] espressioni = {
                "5 8 7 + *",           // Esempio dalla traccia: 5 * (8 + 7) = 75
                "3 4 +",               // Semplice addizione: 3 + 4 = 7
                "10 2 /",              // Divisione: 10 / 2 = 5
                "15 7 1 1 + - / 3 * 2 1 1 + + -", // Espressione complessa
                "4 2 + 3 5 1 - * +",  // (4 + 2) + (3 * (5 - 1)) = 6 + 12 = 18
                "1 2 + 4 * 5 + 3 -",  // ((1 + 2) * 4) + 5 - 3 = 12 + 5 - 3 = 14
                "",                    // Espressione vuota
                "5 +",                 // Operandi insufficienti
                "5 0 /",               // Divisione per zero
                "abc 5 +",             // Token non valido
                "5 6 7",               // Troppi operandi
                "+ 5 6",               // Operatore all'inizio senza operandi
        };

        for (String expr : espressioni) {
            System.out.println("Espressione: \"" + expr + "\"");
            try {
                RPN rpn = new RPN(expr);
                int risultato = rpn.valuta();
                System.out.println("Risultato: " + risultato);
            } catch (RuntimeException e) {
                System.out.println("Espressione malformata: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
            System.out.println();
        }

        // Test aggiuntivi per DeQueue
        System.out.println("=== TEST DEQUEUE ===\n");

        DeQueue<Integer> deque = new LinkedDeQueue<>();

        System.out.println("1. Test operazioni base:");
        System.out.println("DeQueue vuota: " + deque);
        System.out.println("Size: " + deque.size() + ", isEmpty: " + deque.isEmpty());

        // Test push e offer
        deque.push(1);
        deque.push(2);
        deque.offer(3);
        deque.offer(4);
        System.out.println("Dopo push(1), push(2), offer(3), offer(4): " + deque);

        // Test peek
        System.out.println("peek(): " + deque.peek());
        System.out.println("Dopo peek: " + deque);

        // Test poll e pop
        System.out.println("poll(): " + deque.poll());
        System.out.println("pop(): " + deque.pop());
        System.out.println("Dopo poll e pop: " + deque);

        // Test contains
        System.out.println("Contains 3: " + deque.contains(3));
        System.out.println("Contains 5: " + deque.contains(5));

        // Test comportamento come stack
        System.out.println("\n2. Test comportamento come stack:");
        DeQueue<String> stack = new LinkedDeQueue<>();
        stack.push("primo");
        stack.push("secondo");
        stack.push("terzo");
        System.out.println("Stack: " + stack);
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack dopo pop: " + stack);

        // Test comportamento come coda
        System.out.println("\n3. Test comportamento come coda:");
        DeQueue<String> coda = new LinkedDeQueue<>();
        coda.offer("primo");
        coda.offer("secondo");
        coda.offer("terzo");
        System.out.println("Coda: " + coda);
        System.out.println("Poll: " + coda.poll());
        System.out.println("Poll: " + coda.poll());
        System.out.println("Coda dopo poll: " + coda);

        System.out.println("\n=== TEST COMPLETATI ===");
    }
}
