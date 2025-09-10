package poo.tracce.AlberoEspressione_Permutazioni_Cruciverba;

import java.util.*;

class Schema implements Cruciverba {
    private char[][] schema;

    public Schema(char[][] schema) {
        this.schema = new char[schema.length][];
        for (int i = 0; i < schema.length; i++) {
            this.schema[i] = schema[i].clone();
        }
    }

    @Override
    public int getNumeroRighe() {
        return schema.length;
    }

    @Override
    public int getNumeroColonne() {
        return schema.length > 0 ? schema[0].length : 0;
    }

    @Override
    public boolean contains(String parola) {
        List<String> orizzontali = paroleOrizzontali();
        List<String> verticali = paroleVerticali();

        return orizzontali.contains(parola) || verticali.contains(parola);
    }

    @Override
    public List<String> paroleOrizzontali() {
        List<String> parole = new ArrayList<>();

        for (int i = 0; i < getNumeroRighe(); i++) {
            StringBuilder parolaCorrente = new StringBuilder();

            for (int j = 0; j < getNumeroColonne(); j++) {
                if (schema[i][j] != ' ') {
                    parolaCorrente.append(schema[i][j]);
                } else {
                    if (parolaCorrente.length() > 1) {
                        parole.add(parolaCorrente.toString());
                    }
                    parolaCorrente = new StringBuilder();
                }
            }

            // Aggiungi l'ultima parola se presente
            if (parolaCorrente.length() > 1) {
                parole.add(parolaCorrente.toString());
            }
        }

        return ordinaParole(parole);
    }

    @Override
    public List<String> paroleVerticali() {
        List<String> parole = new ArrayList<>();

        for (int j = 0; j < getNumeroColonne(); j++) {
            StringBuilder parolaCorrente = new StringBuilder();

            for (int i = 0; i < getNumeroRighe(); i++) {
                if (schema[i][j] != ' ') {
                    parolaCorrente.append(schema[i][j]);
                } else {
                    if (parolaCorrente.length() > 1) {
                        parole.add(parolaCorrente.toString());
                    }
                    parolaCorrente = new StringBuilder();
                }
            }

            // Aggiungi l'ultima parola se presente
            if (parolaCorrente.length() > 1) {
                parole.add(parolaCorrente.toString());
            }
        }

        return ordinaParole(parole);
    }

    private List<String> ordinaParole(List<String> parole) {
        parole.sort((p1, p2) -> {
            if (p1.length() != p2.length()) {
                return Integer.compare(p1.length(), p2.length());
            }
            return p1.compareTo(p2);
        });
        return parole;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getNumeroRighe(); i++) {
            for (int j = 0; j < getNumeroColonne(); j++) {
                sb.append(schema[i][j]);
            }
            if (i < getNumeroRighe() - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Schema)) return false;

        Schema altro = (Schema) obj;
        return Arrays.deepEquals(this.schema, altro.schema);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(schema);
    }

    public static void main(String[] args) {
        char[][] griglia = {
                {'C', 'A', 'S', 'A', ' '},
                {'I', ' ', 'O', ' ', ' '},
                {'A', 'M', 'O', 'R', 'E'},
                {'O', ' ', 'L', ' ', ' '},
                {' ', ' ', 'E', ' ', ' '}
        };

        Schema schema = new Schema(griglia);

        System.out.println("Schema del cruciverba:");
        System.out.println(schema);

        System.out.println("\nParole orizzontali: " + schema.paroleOrizzontali());
        System.out.println("Parole verticali: " + schema.paroleVerticali());

        System.out.println("\nContiene 'CASA': " + schema.contains("CASA"));
        System.out.println("Contiene 'AMORE': " + schema.contains("AMORE"));
        System.out.println("Contiene 'CIAO': " + schema.contains("CIAO"));
        System.out.println("Contiene 'TEST': " + schema.contains("TEST"));
    }
}


