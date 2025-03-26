package poo.tracce.appelloSCARPE;

import java.util.Iterator;
import java.util.Set;

public interface NegozioScarpe {

    void aggiungi( String modelloScarpa, int misura);
    boolean vendi( String modelloScarpa, int misura) throws IllegalArgumentException;
    Set<Integer> misureDisponibili( String modelloScarpa);
    Set<String> scarpeDisponibili();
    boolean eScarpaDisponibile( String modelloScarpa, int misura);
    Iterator<String> scarpeDisponibile(int misura);

}
