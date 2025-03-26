package BACKTRACKING_CON_ECCEZIONE_PER_FILE;

import java.io.IOException;
import java.util.*;

public class Verifica extends Backtracking<Integer, String> {
    private String[] aux;
    private String s;
    private Set<String> dizionario;
    private List<String> appoggio = new ArrayList<>();
    private boolean fine;
    public Verifica(Set<String> dizionario, String s) {
        aux = new String[dizionario.size()];
        this.s = s;
        this.dizionario = new TreeSet<>();
        for (String p : dizionario){
            this.dizionario.add(p);
        }
    }

    @Override
    protected boolean esisteSoluzione(Integer integer) {
        return integer == aux.length-1;
    }

    @Override
    protected boolean assegnabile(Integer integer, String s) {
        String supporto = "";
        for (int i = 0; i < s.length();i++){
            supporto+=s.charAt(i);
            if(dizionario.contains(supporto)){
                fine = true;
                System.out.println(supporto);
                return true;
            }
            s.substring(i+1);
        }
        fine = false;
        return false;
    }

    @Override
    protected void assegna(Integer ps, String s) {
        appoggio.set(ps,s);
    }

    @Override
    protected void deassegna(Integer ps, String s) {

    }

    @Override
    protected void scriviSoluzione(Integer integer) {
        if(!fine){
            System.out.println("Fine verifica senza frammentazione");
            return;
        }
        System.out.println(appoggio);
        System.out.println("Fine verifica correttamente");
        return;
    }

    @Override
    protected List<Integer> puntiDiScelta() {
        List<Integer> pds = new ArrayList<>();
        for (int i = 0; i < aux.length; i++) {
            pds.add(i);
        }
        return pds;
    }

    @Override
    protected Collection<String> scelte(Integer integer) {
        Collection<String> scelte = new HashSet<>();
        scelte.addAll(dizionario);
        return scelte;
    }

    public static void main(String[] args) throws IOException {
        Set<String> dict = new TreeSet<>();
        dict.add("il");
        dict.add("dado");
        dict.add("la");
        dict.add("cane");
        dict.add("corre");
        dict.add("zebra");
        dict.add("veloce");
        dict.add("gatto");
        dict.add("grigio");
        dict.add("treno");
        dict.add("salta");
        System.out.println(dict);
        String s = "ilgattovelocesaltailcane";
        Verifica v = new Verifica(dict,s);
        v.risolvi();

    }
}
