package poo.tracce.DequeueRPN;

import java.util.*;

/**
 * Classe per la valutazione di espressioni RPN (Reverse Polish Notation)
 */
class RPN {
    private String expression;

    /**
     * Costruttore che accetta una stringa contenente un'espressione RPN
     */
    public RPN(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("L'espressione non può essere null");
        }
        this.expression = expression.trim();
    }

    /**
     * Calcola il risultato dell'espressione RPN
     *
     * @return il risultato dell'espressione
     * @throws RuntimeException se l'espressione è malformata
     */
    public int valuta() {
        if (expression.isEmpty()) {
            throw new RuntimeException("Espressione vuota");
        }

        // Utilizza DeQueue come stack di operandi
        DeQueue<Integer> stack = new LinkedDeQueue<>();

        // Divide l'espressione in token separati da spazi
        String[] tokens = expression.split("\\s+");

        try {
            for (String token : tokens) {
                if (token.isEmpty()) {
                    continue; // Ignora token vuoti
                }

                if (isOperator(token)) {
                    // È un operatore: estrae due operandi e calcola il risultato
                    if (stack.size() < 2) {
                        throw new RuntimeException("Operandi insufficienti per l'operatore: " + token);
                    }

                    // Estrae nell'ordine corretto: prima o2, poi o1
                    int o2 = stack.pop(); // secondo operando
                    int o1 = stack.pop(); // primo operando

                    int risultato = applicaOperatore(o1, o2, token);
                    stack.push(risultato);

                } else {
                    // È un operando: lo aggiunge allo stack
                    try {
                        int operando = Integer.parseInt(token);
                        stack.push(operando);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Token non valido: " + token);
                    }
                }
            }

            // Alla fine dovrebbe esserci esattamente un elemento nello stack
            if (stack.size() != 1) {
                if (stack.isEmpty()) {
                    throw new RuntimeException("Espressione vuota o solo operatori");
                } else {
                    throw new RuntimeException("Troppi operandi: " + stack.size() + " elementi rimasti nello stack");
                }
            }

            return stack.peek();

        } catch (NoSuchElementException e) {
            throw new RuntimeException("Espressione malformata: stack vuoto durante l'operazione");
        }
    }

    /**
     * Verifica se un token è un operatore
     */
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /**
     * Applica un operatore a due operandi
     */
    private int applicaOperatore(int o1, int o2, String operator) {
        switch (operator) {
            case "+":
                return o1 + o2;
            case "-":
                return o1 - o2;
            case "*":
                return o1 * o2;
            case "/":
                if (o2 == 0) {
                    throw new RuntimeException("Divisione per zero");
                }
                return o1 / o2;
            default:
                throw new RuntimeException("Operatore non supportato: " + operator);
        }
    }

    /**
     * Restituisce l'espressione memorizzata
     */
    public String getExpression() {
        return expression;
    }
}