package Cruciverba;

import java.util.*;

public class Schema implements Cruciverba {
    private char[][] schema;

    public Schema(char[][] schema) {
        for (int i = 0; i < schema.length; i++) {
            if (schema[0].length != schema[i].length) {
                throw new IllegalArgumentException();
            }
        }
        this.schema = new char[schema.length][schema[0].length];
        for (int i = 0; i < schema.length; i++) {
            System.arraycopy(schema[i], 0, this.schema[i], 0, schema[i].length);
        }
    }

    @Override
    public int getNumeroRighe() {
        return schema.length;
    }

    @Override
    public int getNumeroColonne() {
        return schema[0].length;
    }

    @Override
    public List<String> paroleOrizzontali() {
        Set<String> tmp = new TreeSet<>((s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);
            }
            return s1.length() - s2.length();
        });
        for (int i = 0; i < schema.length; i++) {
            String s = "";
            for (int j = 0; j < schema[i].length; j++) {
                char carattere = schema[i][j];
                if (carattere != ' ') {
                    s += carattere;
                } else {
                    if (!s.isEmpty()) {
                        tmp.add(s);
                    }
                    s = "";
                }
                if (!s.isEmpty()) {
                    tmp.add(s);
                }
            }
        }
        List<String> paroleOrizzontali = new LinkedList<>();
        paroleOrizzontali.addAll(tmp);
        return paroleOrizzontali;
    }

    @Override
    public List<String> paroleVerticali() {
        Set<String> tmp = new TreeSet<>((s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);
            }
            return s1.length() - s2.length();
        });
        for (int i = 0; i < schema[0].length; i++) {
            String s = "";
            for (int j = 0; j < schema.length; j++) {
                char carattere = schema[j][i];
                if (carattere != ' ') {
                    s += carattere;
                } else {
                    if (!s.isEmpty()) {
                        tmp.add(s);
                    }
                    s = "";
                }
            }
            if (!s.isEmpty()) {
                tmp.add(s);
            }
        }
        List<String> paroleVerticali = new LinkedList<>();
        paroleVerticali.addAll(tmp);
        return paroleVerticali;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        for (int i = 0; i < schema.length; i++) {
            sb.append("[");
            for (int j = 0; j < schema[i].length; j++) {
                sb.append(schema[i][j]);
                if (j != schema[i].length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cruciverba)) {
            return false;
        }
        if (obj == this) return true;
        Cruciverba c = (Cruciverba) obj;
        if (c.paroleOrizzontali().size() != this.paroleOrizzontali().size() ||
                c.paroleVerticali().size() != this.paroleVerticali().size()) return false;
        Iterator<String> it = paroleOrizzontali().iterator();
        Iterator<String> it2 = c.paroleOrizzontali().iterator();
        while (it.hasNext()) {
            String curr = it.next();
            if (!it2.hasNext()) {
                return false;
            }
            String cmp = it2.next();
            if (!curr.equals(cmp)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 83;
        int h = 0;
        for (String p : paroleOrizzontali()) {
            h = h * prime + p.hashCode();
        }
        for (String p : paroleVerticali()) {
            h = h * prime + p.hashCode();
        }
        return h;
    }

    public static void main(String[] args) {
        char[][] cruci = new char[9][9]; //non necessariamente quadrato
        " CASACCA".getChars(0, 8, cruci[0], 0);
        "STUFATO ".getChars(0, 8, cruci[1], 0);
        "VICOLO AR".getChars(0, 8, cruci[2], 0);
        " TIRAMISU".getChars(0, 8, cruci[3], 0);
        "CONICO IP".getChars(0, 8, cruci[4], 0);
        "I OSE C I".getChars(0, 8, cruci[5], 0);
        "F TM SARA".getChars(0, 8, cruci[6], 0);
        "ROTATIVA ".getChars(0, 8, cruci[7], 0);
        "AGO URALI".getChars(0, 8, cruci[8], 0);
        Schema s = new Schema(cruci);
        System.out.println(s);
        System.out.println("Parole orizzontali: ");
        System.out.println(s.paroleOrizzontali());
        System.out.println("Parole verticali: ");
        System.out.println(s.paroleVerticali());
    }
}

