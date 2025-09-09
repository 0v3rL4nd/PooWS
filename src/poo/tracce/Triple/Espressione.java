package poo.tracce.Triple;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Classe per la valutazione di espressioni aritmetiche intere.
 * Supporta operatori +, -, *, / con precedenza matematica standard
 * e parentesi per raggruppare sotto-espressioni.
 */
public class Espressione {
    private String expr;

    // Regex per validare l'espressione
    // Deve contenere solo cifre, operatori (+,-,*,/) e parentesi
    private static final Pattern VALID_EXPRESSION =
            Pattern.compile("^[0-9+\\-*/()]+$");

    /**
     * Costruttore che verifica la validità dell'espressione con una regex.
     * @param expr stringa contenente l'espressione da valutare
     * @throws IllegalArgumentException se l'espressione non è valida
     */
    public Espressione(String expr) {
        if (expr == null || expr.trim().isEmpty()) {
            throw new IllegalArgumentException("L'espressione non può essere null o vuota");
        }

        // Rimuoviamo eventuali spazi (anche se non dovrebbero esserci)
        this.expr = expr.replaceAll("\\s+", "");

        // Verifichiamo con regex che contenga solo caratteri validi
        if (!VALID_EXPRESSION.matcher(this.expr).matches()) {
            throw new IllegalArgumentException("L'espressione contiene caratteri non validi. " +
                    "Sono ammessi solo cifre, +, -, *, /, (, )");
        }

        // Verifichiamo il bilanciamento delle parentesi
        if (!parentesiBilanciate(this.expr)) {
            throw new IllegalArgumentException("Le parentesi non sono bilanciate");
        }

        // Verifichiamo che non ci siano operatori consecutivi o all'inizio/fine
        if (!sintassiCorretta(this.expr)) {
            throw new IllegalArgumentException("Sintassi dell'espressione non corretta");
        }
    }

    /**
     * Verifica che le parentesi siano bilanciate.
     */
    private boolean parentesiBilanciate(String expr) {
        int count = 0;
        for (char c : expr.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false; // chiusura senza apertura
        }
        return count == 0;
    }

    /**
     * Verifica la correttezza sintattica base dell'espressione.
     */
    private boolean sintassiCorretta(String expr) {
        if (expr.isEmpty()) return false;

        // Non può iniziare o finire con un operatore binario
        char first = expr.charAt(0);
        char last = expr.charAt(expr.length() - 1);

        if (isOperator(first) && first != '(' && first != '-') return false;
        if (isOperator(last) && last != ')') return false;

        // Non possono esserci operatori consecutivi (tranne - come segno negativo)
        for (int i = 0; i < expr.length() - 1; i++) {
            char curr = expr.charAt(i);
            char next = expr.charAt(i + 1);

            if (isOperator(curr) && isOperator(next) &&
                    !(curr == '(' || next == ')' || next == '(' ||
                            (next == '-' && (curr == '(' || isOperator(curr))))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica se un carattere è un operatore.
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    /**
     * Valuta l'espressione e restituisce il risultato.
     * @return risultato intero della valutazione
     * @throws ArithmeticException per divisione per zero
     * @throws IllegalStateException per espressioni malformate
     */
    public int valuta() {
        try {
            StringTokenizer st = new StringTokenizer(expr, "+-*/()", true);
            int risultato = valutaEspressione(st);

            // Verifichiamo che non ci siano token residui
            if (st.hasMoreTokens()) {
                throw new IllegalStateException("Espressione malformata: token residui");
            }

            return risultato;
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new IllegalStateException("Espressione malformata", e);
        }
    }

    /**
     * Metodo privato che effettivamente valuta l'espressione utilizzando due stack:
     * uno per gli operandi e uno per gli operatori.
     * @param st StringTokenizer per scandire l'espressione token per token
     * @return risultato della valutazione
     */
    private int valutaEspressione(StringTokenizer st) {
        Stack<Integer> operandi = new Stack<>();
        Stack<Character> operatori = new Stack<>();

        boolean aspettaOperando = true; // per gestire il segno negativo

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (token.length() == 1 && isOperator(token.charAt(0))) {
                char op = token.charAt(0);

                if (op == '(') {
                    // Chiamata ricorsiva per la sotto-espressione
                    int risultatoSottoEspressione = valutaEspressione(st);
                    operandi.push(risultatoSottoEspressione);
                    aspettaOperando = false;
                } else if (op == ')') {
                    // Fine della sotto-espressione, applichiamo tutti gli operatori rimasti
                    while (!operatori.isEmpty()) {
                        applicaOperatore(operandi, operatori);
                    }

                    if (operandi.isEmpty()) {
                        throw new IllegalStateException("Espressione malformata: parentesi vuote");
                    }

                    return operandi.pop();
                } else if (op == '-' && aspettaOperando) {
                    // Gestione del segno negativo
                    if (!st.hasMoreTokens()) {
                        throw new IllegalStateException("Espressione malformata: - senza operando");
                    }
                    String nextToken = st.nextToken();
                    if (nextToken.equals("(")) {
                        // -(...) - chiamata ricorsiva e negazione del risultato
                        int risultato = valutaEspressione(st);
                        operandi.push(-risultato);
                    } else {
                        // -numero
                        try {
                            int numero = Integer.parseInt(nextToken);
                            operandi.push(-numero);
                        } catch (NumberFormatException e) {
                            throw new IllegalStateException("Espressione malformata dopo -");
                        }
                    }
                    aspettaOperando = false;
                } else {
                    // Operatore normale (+, -, *, /)

                    // Applichiamo gli operatori con priorità maggiore o uguale
                    while (!operatori.isEmpty() &&
                            priorita(operatori.peek()) >= priorita(op)) {
                        applicaOperatore(operandi, operatori);
                    }

                    operatori.push(op);
                    aspettaOperando = true;
                }
            } else {
                // Token numerico
                try {
                    int numero = Integer.parseInt(token);
                    operandi.push(numero);
                    aspettaOperando = false;
                } catch (NumberFormatException e) {
                    throw new IllegalStateException("Token non riconosciuto: " + token);
                }
            }
        }

        // Applichiamo tutti gli operatori rimasti
        while (!operatori.isEmpty()) {
            applicaOperatore(operandi, operatori);
        }

        if (operandi.size() != 1) {
            throw new IllegalStateException("Espressione malformata: numero errato di operandi");
        }

        return operandi.pop();
    }

    /**
     * Restituisce la priorità di un operatore.
     * @param op operatore
     * @return priorità (maggiore = più prioritario)
     */
    private int priorita(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Applica un operatore prelevando operatori e operandi dagli stack appropriati.
     * @param operandi stack degli operandi
     * @param operatori stack degli operatori
     */
    private void applicaOperatore(Stack<Integer> operandi, Stack<Character> operatori) {
        if (operatori.isEmpty() || operandi.size() < 2) {
            throw new IllegalStateException("Espressione malformata: operatori/operandi insufficienti");
        }

        char op = operatori.pop();
        int o2 = operandi.pop(); // secondo operando (top dello stack)
        int o1 = operandi.pop(); // primo operando (top-1 dello stack)

        int risultato;
        switch (op) {
            case '+':
                risultato = o1 + o2;
                break;
            case '-':
                risultato = o1 - o2;
                break;
            case '*':
                risultato = o1 * o2;
                break;
            case '/':
                if (o2 == 0) {
                    throw new ArithmeticException("Divisione per zero");
                }
                risultato = o1 / o2; // divisione intera
                break;
            default:
                throw new IllegalStateException("Operatore non riconosciuto: " + op);
        }

        operandi.push(risultato);
    }

    /**
     * Restituisce la rappresentazione stringa dell'espressione.
     */
    @Override
    public String toString() {
        return "Espressione: " + expr;
    }

    /**
     * Metodo di test per verificare il funzionamento della classe.
     */
    public static void main(String[] args) {
        System.out.println("Test della classe Espressione:");
        System.out.println("==============================");

        String[] espressioni = {
                "3+5*2",           // = 13
                "(3+5)*2",         // = 16
                "10-3*2",          // = 4
                "10/(5-3)",        // = 5
                "2*3+4*5",         // = 26
                "(2+3)*(4+5)",     // = 45
                "100/10/2",        // = 5 (associatività sinistra)
                "2*3*4",           // = 24
                "-5+3",            // = -2
                "-(3+2)",          // = -5
                "10-(-5)",         // = 15
                "((2+3)*4)",       // = 20
        };

        for (String expr : espressioni) {
            try {
                Espressione e = new Espressione(expr);
                int risultato = e.valuta();
                System.out.printf("%-15s = %d\n", expr, risultato);
            } catch (Exception ex) {
                System.out.printf("%-15s ERROR: %s\n", expr, ex.getMessage());
            }
        }

        System.out.println("\nTest di espressioni non valide:");
        System.out.println("===============================");

        String[] espressioniInvalide = {
                "3++5",            // operatori consecutivi
                "3+",              // finisce con operatore
                "+3",              // inizia con operatore (tranne -)
                "3+()",            // parentesi vuote
                "3+5*",            // finisce con operatore
                "((3+5)",          // parentesi non bilanciate
                "3+5)**2",         // caratteri non validi
                "3/0",             // divisione per zero
                "",                // espressione vuota
                "3 + 5",           // con spazi (dovrebbe essere gestito)
        };

        for (String expr : espressioniInvalide) {
            try {
                Espressione e = new Espressione(expr);
                int risultato = e.valuta();
                System.out.printf("%-15s = %d (DOVREBBE ESSERE ERRORE!)\n", expr, risultato);
            } catch (Exception ex) {
                System.out.printf("%-15s ERROR: %s\n", expr, ex.getMessage());
            }
        }
    }
}