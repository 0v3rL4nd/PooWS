package AlberoEspressione;

import java.util.Stack;

import javax.xml.stream.events.Characters;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class AlberoEspressione {
    private static class Nodo {
        Nodo fs, fd;
    }

    private static class NodoOperando extends Nodo {
        int valore;

        public String toString() {
            return "" + valore;
        }
    }

    private static class NodoOperatore extends Nodo {
        char segno;

        public String toString() {
            return "" + segno;
        }
    }

    private Stack<Nodo> albero = new Stack<>();
    static String operatore = "[\\+\\-\\*/]";
    static String operando = "\\d+";
    static String regex = "(\\d+|\\d+(\\s+)[\\d+(\\s+)]*("+operatore+"(\\s+))+)";

    public void build(String rpn) {
        if (!rpn.matches(regex)) {
            throw new RuntimeException("Espressione malformata");
        }
        //procediamo con la costruzione dell'albero
        StringTokenizer st = new StringTokenizer(rpn, " \n");
        int cntNumeri = 0;
        int cntOperatori = 0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.matches(operando)) {
                Integer val = Integer.parseInt(token);
                NodoOperando n = new NodoOperando();
                n.valore = val;
                albero.push(n);
                cntNumeri++;
            }
            if (token.matches(operatore)) {
                char carattere = token.charAt(0);
                NodoOperatore n = new NodoOperatore();
                n.segno = carattere;
                if (albero.size() <= 1) throw new RuntimeException("Espressione malformata!");
                Nodo operando2 = albero.pop();
                Nodo operando1 = albero.pop();
                n.fs = operando1;
                n.fd = operando2;
                albero.push(n);cntOperatori++;
            }
        }
        if(cntNumeri - cntOperatori != 1) throw new RuntimeException("Espressione malformatissima");
    }

    public void inOrderIte( List<String> lv ){
        inOrderIte(lv,albero.pop());
    }

    private void inOrderIte(List<String> lv, Nodo pop) {
        if(pop == null){
            return;
        }
        if(pop instanceof NodoOperatore){
            lv.add("(");
        }
        inOrderIte(lv,pop.fs);
        lv.add(pop.toString());
        inOrderIte(lv,pop.fd);
        if(pop instanceof NodoOperatore){
            lv.add(")");
        }
    }

    public static void main(String[] args) {
        AlberoEspressione ae = new AlberoEspressione();
        ae.build("1 2 3 4 5 + - * / ");
        List<String> lista = new LinkedList<>();
        ae.inOrderIte(lista);
        System.out.println(lista);
    }


}
