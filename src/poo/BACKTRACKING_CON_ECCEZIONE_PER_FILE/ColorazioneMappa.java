package poo.BACKTRACKING_CON_ECCEZIONE_PER_FILE;

import java.io.IOException;
import java.util.*;

enum Colore{ ROSSO, VERDE, GIALLO, NERO}
public class ColorazioneMappa extends Backtracking<Integer,Colore>{
    private Set<Integer> [] stessoColore;
    private Set<Integer> [] confinanti;
    private int numSol;
    private int n;
    public ColorazioneMappa(Set<Integer>[]confinanti) {
        this.n = confinanti.length;
        this.confinanti = new HashSet[n];
        for (int i = 0; i < confinanti.length; i++) {
            this.confinanti[i] = new HashSet<>(confinanti[i]);
        }
        this.stessoColore = new HashSet[Colore.values().length];
        for (int i = 0; i < stessoColore.length; i++) {
            stessoColore[i] = new HashSet<>(); //ogni elemento è un set di celle che hanno ricevuto lo stesso colore
        }
    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == n-1;
    }

    @Override
    protected boolean assegnabile(Integer pds, Colore scelta) {
        //il mio punto di scelta (una cella) può avere quel colore? devo verificare che non lo abbiano gli adiacenti
        Set<Integer> confinanti = this.confinanti[pds]; // prendo i confinanti di quella cella (che di 0 sono 1 e 4)
        Set<Integer> stessoColore = this.stessoColore[scelta.ordinal()]; // prendo tutte le celle che hanno lo stesso colore
        for(Integer conf : confinanti){
            //se almeno un confinante della cella, contiene quel colore
            if(stessoColore.contains(conf)) return false;
        }
        return true;
    }

    @Override
    protected void assegna(Integer ps, Colore scelta) {
        //vogliamo assegnare quel colore a quella cella
        int index = scelta.ordinal(); //prendo l'indice del colore
        stessoColore[index].add(ps);
        return;
    }

    @Override
    protected void deassegna(Integer ps, Colore scelta) {
        int index = scelta.ordinal(); //prendo l'indice del colore
        stessoColore[index].remove(ps);
    }

    @Override
    protected void scriviSoluzione(Integer integer) {
        numSol++;
        System.out.println("Soluzione numero "+numSol);
        for (int i = 0; i < stessoColore.length; i++) {
            System.out.print("Colore "+Colore.values()[i]+" ");
            for(Integer ind : stessoColore[i]){
                System.out.print(ind+" ");
            }
            System.out.println();
        }
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer>pds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pds.add(i);
        }
        return pds;
    }

    @Override
    protected Collection<Colore> scelte(Integer integer) {
        //per ogni mio punto di scelta, quali scelte ho? tutti i colori disponibili
        List<Colore> scelte = new ArrayList<>();
        for(Colore c : Colore.values()){
            scelte.add(c);
        }
        return scelte;
    }
    public static void main( String[] args ) throws IOException {
        int n=6; //esempio
        Set<Integer> conf[]=new HashSet[n];
//esempio di confinanze
        conf[0]=new HashSet<>( java.util.Arrays.asList(1,4));
        conf[1]=new HashSet<>( java.util.Arrays.asList(0,4,5,2));
        conf[2]=new HashSet<>( java.util.Arrays.asList(1,5,3));
        conf[3]=new HashSet<>( java.util.Arrays.asList(2));
        conf[4]=new HashSet<>( java.util.Arrays.asList(0,1,5));
        conf[5]=new HashSet<>( java.util.Arrays.asList(1,2,4));
        ColorazioneMappa cm=new ColorazioneMappa(conf);
        cm.risolvi();
    }//main
}
