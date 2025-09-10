package poo.tracce.AlberoEspressione_Permutazioni_Cruciverba;

import java.util.*;
import java.util.regex.*;

class AlberoEspressione {
    private Nodo radice;

    public AlberoEspressione() {
        this.radice = null;
    }

    /**
     * Costruisce l'albero da un'espressione postfissa (RPN)
     */
    void build(String rpn) {
        // Validazione con espressione regolare
        Pattern pattern = Pattern.compile("^(\\d+\\s+)*(\\d+\\s+[+\\-*/]\\s+)+\\d+$");
        if (!pattern.matcher(rpn.trim() + " ").matches()) {
            throw new RuntimeException("Espressione postfissa malformata: " + rpn);
        }

        Stack<Nodo> stack = new Stack<>();
        String[] tokens = rpn.trim().split("\\s+");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                // È un operando
                stack.push(new NodoOperando(Integer.parseInt(token)));
            } else if (token.matches("[+\\-*/]")) {
                // È un operatore
                if (stack.size() < 2) {
                    throw new RuntimeException("Operatori insufficienti per l'operatore: " + token);
                }
                Nodo destro = stack.pop();
                Nodo sinistro = stack.pop();
                stack.push(new NodoOperatore(token.charAt(0), sinistro, destro));
            } else {
                throw new RuntimeException("Token non valido: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new RuntimeException("Espressione malformata: stack finale non contiene esattamente un elemento");
        }

        this.radice = stack.pop();
    }

    /**
     * Visita in ordine simmetrico iterativa
     */
    void inOrderIte(List<String> lv) {
        if (radice == null) return;

        Stack<Nodo> stack = new Stack<>();
        Nodo corrente = radice;

        while (corrente != null || !stack.isEmpty()) {
            // Vai tutto a sinistra
            while (corrente != null) {
                stack.push(corrente);
                if (corrente instanceof NodoOperatore) {
                    corrente = ((NodoOperatore) corrente).getSinistro();
                } else {
                    corrente = null;
                }
            }

            // Processa il nodo corrente
            corrente = stack.pop();
            if (corrente instanceof NodoOperatore) {
                lv.add(String.valueOf(((NodoOperatore) corrente).getOperatore()));
                corrente = ((NodoOperatore) corrente).getDestro();
            } else {
                lv.add(corrente.toString());
                corrente = null;
            }
        }
    }

    public int calcola() {
        if (radice == null) throw new RuntimeException("Albero vuoto");
        return radice.calcola();
    }

    @Override
    public String toString() {
        return radice != null ? radice.toString() : "vuoto";
    }

    // Main di test
    public static void main(String[] args) {
        AlberoEspressione albero = new AlberoEspressione();

        // Test con espressione postfissa: "3 4 + 2 * 1 -" che rappresenta ((3+4)*2)-1 = 13
        String rpn = "3 4 + 2 * 1 -";
        System.out.println("Espressione RPN: " + rpn);

        albero.build(rpn);
        System.out.println("Albero costruito: " + albero);
        System.out.println("Risultato calcolo: " + albero.calcola());

        List<String> visitaInOrder = new ArrayList<>();
        albero.inOrderIte(visitaInOrder);
        System.out.println("Visita in ordine simmetrico: " + visitaInOrder);
    }
}
