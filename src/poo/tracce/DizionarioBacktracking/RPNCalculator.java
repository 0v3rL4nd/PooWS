package poo.tracce.DizionarioBacktracking;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class RPNCalculator {
    private Stack<Double> stack;
    private Map<String, Double> variabili;

    public RPNCalculator() {
        this.stack = new Stack<>();
        this.variabili = new HashMap<>();
    }

    public void caricaVariabili(String nomeFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] parti = linea.split("\\s+");
                    if (parti.length >= 2) {
                        String nomeVariabile = parti[0];
                        try {
                            double valore = Double.parseDouble(parti[1]);
                            variabili.put(nomeVariabile, valore);
                        } catch (NumberFormatException e) {
                            System.err.println("Errore nel parsing del valore per " + nomeVariabile + ": " + parti[1]);
                        }
                    }
                }
            }
        }
    }

    public double valuta(String espressione) {
        stack.clear(); // Pulisce lo stack per una nuova valutazione

        // Valida il formato usando regex
        if (!validaFormato(espressione)) {
            throw new IllegalArgumentException("Formato espressione non valido: " + espressione);
        }

        // Divide l'espressione in token (separati da spazi)
        String[] token = espressione.trim().split("\\s+");

        for (String t : token) {
            if (isOperatore(t)) {
                eseguiOperazione(t);
            } else {
                // È un operando (numero o variabile)
                double valore = ottieniValore(t);
                stack.push(valore);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Espressione RPN malformata: lo stack dovrebbe contenere esattamente un elemento");
        }

        return stack.pop();
    }

    private boolean validaFormato(String espressione) {
        // Pattern per validare espressione RPN:
        // - operandi: numeri (interi o decimali) o identificatori di variabili
        // - operatori: +, -, *, /
        // - separati da almeno uno spazio
        String pattern = "^\\s*(([a-zA-Z_][a-zA-Z0-9_]*|\\d+(\\.\\d+)?)\\s+)*([a-zA-Z_][a-zA-Z0-9_]*|\\d+(\\.\\d+)?)\\s*$";

        // Verifica anche che ci siano operatori
        return Pattern.matches(pattern, espressione) &&
                (espressione.contains("+") || espressione.contains("-") ||
                        espressione.contains("*") || espressione.contains("/"));
    }

    private boolean isOperatore(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private double ottieniValore(String token) {
        try {
            // Prova a interpretarlo come numero
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            // È una variabile
            if (variabili.containsKey(token)) {
                return variabili.get(token);
            } else {
                throw new IllegalArgumentException("Variabile non definita: " + token);
            }
        }
    }

    private void eseguiOperazione(String operatore) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Stack insufficiente per operazione: " + operatore);
        }

        // ATTENZIONE: in RPN il secondo operando è quello in cima allo stack
        double o1 = stack.pop(); // secondo operando (top)
        double o2 = stack.pop(); // primo operando (top-1)

        double risultato;
        switch (operatore) {
            case "+":
                risultato = o2 + o1;
                break;
            case "-":
                risultato = o2 - o1;
                break;
            case "*":
                risultato = o2 * o1;
                break;
            case "/":
                if (o1 == 0) {
                    throw new ArithmeticException("Divisione per zero");
                }
                risultato = o2 / o1;
                break;
            default:
                throw new IllegalArgumentException("Operatore non supportato: " + operatore);
        }

        stack.push(risultato);
    }

    public void precaricaVariabili(Map<String, Double> variabiliPrecaricate) {
        this.variabili.putAll(variabiliPrecaricate);
    }

    public static void main(String[] args) {
        RPNCalculator calculator = new RPNCalculator();

        try {
            // Simula il caricamento delle variabili (invece di leggere da file)
            Map<String, Double> variabiliTest = new HashMap<>();
            variabiliTest.put("alfa", 500.0);
            variabiliTest.put("b", 2.0);
            calculator.precaricaVariabili(variabiliTest);

            // Test dell'esempio dalla traccia: alfa b 12 + 15 * /
            // In notazione infix: alfa / ((b + 12) * 15)
            // Con alfa=500, b=2: 500 / ((2 + 12) * 15) = 500 / (14 * 15) = 500 / 210 ≈ 2.38

            System.out.println("=== Test Calcolatore RPN ===");
            System.out.println("Variabili caricate: " + variabiliTest);
            System.out.println();

            String espressione1 = "alfa b 12 + 15 * /";
            System.out.println("Espressione RPN: " + espressione1);
            System.out.println("Equivalente infix: alfa / ((b + 12) * 15)");
            double risultato1 = calculator.valuta(espressione1);
            System.out.println("Risultato: " + risultato1);
            System.out.println("Risultato (intero): " + (int)Math.round(risultato1));
            System.out.println();

            // Altri test
            String espressione2 = "5 3 + 2 *";
            System.out.println("Espressione RPN: " + espressione2);
            System.out.println("Equivalente infix: (5 + 3) * 2");
            double risultato2 = calculator.valuta(espressione2);
            System.out.println("Risultato: " + risultato2);
            System.out.println();

            String espressione3 = "10 2 / 3 -";
            System.out.println("Espressione RPN: " + espressione3);
            System.out.println("Equivalente infix: (10 / 2) - 3");
            double risultato3 = calculator.valuta(espressione3);
            System.out.println("Risultato: " + risultato3);
            System.out.println();

            // Test con variabili
            String espressione4 = "alfa 100 - b *";
            System.out.println("Espressione RPN: " + espressione4);
            System.out.println("Equivalente infix: (alfa - 100) * b");
            double risultato4 = calculator.valuta(espressione4);
            System.out.println("Risultato: " + risultato4);
            System.out.println();

            // Test validazione formato
            System.out.println("=== Test Validazione ===");
            try {
                String espressioneInvalida = "5 3 + + 2";
                calculator.valuta(espressioneInvalida);
            } catch (Exception e) {
                System.out.println("Errore atteso per espressione invalida: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Errore durante l'esecuzione: " + e.getMessage());
            e.printStackTrace();
        }
    }
}