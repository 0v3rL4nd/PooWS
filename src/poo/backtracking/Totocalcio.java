package poo.backtracking;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Totocalcio extends Backtracking<Integer, Character> {
    //Scopo della classe Totocalcio è enumerare, con la tecnica backtracking, tutte le
    //possibili colonne di 13 giocate generabili dal sistema
    private char[][] sistema;
    private char[] colonna = new char[13];
    private File f;
    private int numSol;
    //Ogni posizione del
    //vettore 𝑐𝑜𝑙𝑜𝑛𝑛𝑎 è un punto di scelta


    public Totocalcio(char[][] sistema) throws IOException {
        for (int i = 0; i < sistema.length; i++) {
            if (sistema[i].length > 3) {
                throw new IllegalArgumentException("Sistema non accettato");
            }
        }
        this.sistema = new char[sistema.length][];
        for (int i = 0; i < sistema.length; i++) {
            this.sistema[i] = new char[sistema[i].length]; // Inizializza l'array interno
            System.arraycopy(sistema[i], 0, this.sistema[i], 0, sistema[i].length);
        }
        for (int i = 0; i < colonna.length; i++) {
            colonna[i] = ' ';
        }
       

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < sistema.length; i++) {
            for (int j = 0; j < sistema[i].length; j++) {
                s.append(sistema[i][j]);
            }
        }
        return s.toString();
    }

    @Override
    protected boolean assegnabile(Integer pds, Character s) {
        //quello che faccio è chiedermi: per il mio punto di scelta (ossia un indice su colonna), posso 
        //assegnare il carattere s? devo andare a prendere la posizione sul sistema e vedere le possibili 
        //variabili là dentro 
        Collection<Character> scelte = scelte(pds);
        if(!(scelte.contains(s))) return false;
        char c = colonna[pds];
        if(c == ' ') return true;
        return false;
    }

    @Override
    protected void assegna(Integer ps, Character s) {
       colonna[ps] = s;
    }

    @Override
    protected void deassegna(Integer ps, Character s) {
        colonna[ps] = ' ';
    }

    @Override
    protected void scriviSoluzione(Integer pds){
        numSol++;
        System.out.println("Numero soluzione: " + numSol);
        for (int i = 0; i < colonna.length; i++) {
            System.out.print(colonna[i]);
        }
        System.out.println();
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> pds = new ArrayList<>();
        for (int i = 0; i < colonna.length; i++) {
            pds.add(i);
            //così facendo andiamo a creare i nostri punti di scelta che altro non sono che gli indici
            //dell'array
        }
        return pds;
    }

    @Override
    protected Collection<Character> scelte(Integer pds) {
        List<Character> scelte = new ArrayList<>();
        //sono le possibili giocate nell’array di array sistema sulla riga
        for (int j = 0; j < sistema[pds].length; j++) {
            scelte.add(sistema[pds][j]);
        }
        return scelte;
    }

    @Override
    protected boolean esisteSoluzione(Integer pds) {
        return pds == colonna.length - 1;
    }

    public static void main(String[] args) throws IOException {
        char[][] sistema = {
                {'2'},
                {'1', 'X'},
                {'X'},
                {'1', '2', 'X'},
                {'1', 'X'},
                {'2'},
                {'1'},
                {'1', '2', 'X'},
                {'X'},
                {'1'},
                {'2', '1'},
                {'X'},
                {'2'},
        };
        File f = new File("/home/helgrind/Scrivania/poo-file/Totocalcio/sistemaTotocalcio.txt");
        Totocalcio t = new Totocalcio(sistema);
        System.out.println("Adesso generiamo tutte le possibili combinazioni");
        t.risolvi();
    }
}
