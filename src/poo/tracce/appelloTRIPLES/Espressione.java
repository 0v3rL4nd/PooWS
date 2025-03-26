package poo.tracce.appelloTRIPLES;

import java.util.Stack;
import java.util.StringTokenizer;

class Espressione {
    private String expr;

    public Espressione(String expr) {
        if (!expr.matches("[0-9+\\-*/()]+")) {
            throw new IllegalArgumentException("Espressione non valida.");
        }
        this.expr = expr;
    }

    public int valuta() {
        return valutaEspressione(new StringTokenizer(expr, "+-*/()", true));
    }

    private int valutaEspressione(StringTokenizer st) {
        Stack<Integer> operandi = new Stack<>();
        Stack<Character> operatori = new Stack<>();

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.matches("\\d+")) {
                operandi.push(Integer.parseInt(token));
            } else if (token.equals("(")) {
                operandi.push(valutaEspressione(st));
            } else if (token.equals(")")) {
                break;
            } else {
                while (!operatori.isEmpty() && precedence(operatori.peek()) >= precedence(token.charAt(0))) {
                    eseguiOperazione(operandi, operatori);
                }
                operatori.push(token.charAt(0));
            }
        }
        while (!operatori.isEmpty()) {
            eseguiOperazione(operandi, operatori);
        }
        return operandi.pop();
    }

    private int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : (op == '*' || op == '/') ? 2 : 0;
    }

    private void eseguiOperazione(Stack<Integer> operandi, Stack<Character> operatori) {
        if (operandi.size() < 2) {
            throw new IllegalArgumentException("Espressione malformata");
        }
        int b = operandi.pop();
        int a = operandi.pop();
        char op = operatori.pop();
        switch (op) {
            case '+': operandi.push(a + b); break;
            case '-': operandi.push(a - b); break;
            case '*': operandi.push(a * b); break;
            case '/':
                if (b == 0) throw new ArithmeticException("Divisione per zero");
                operandi.push(a / b);
                break;
        }
    }

    @Override
    public String toString() {
        return expr;
    }
}
