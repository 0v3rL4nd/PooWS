package poo.tracce.AlberoEspressione_Permutazioni_Cruciverba;

import java.util.List;

interface Cruciverba {
    int getNumeroRighe();
    int getNumeroColonne();
    boolean contains(String parola);
    List<String> paroleOrizzontali();
    List<String> paroleVerticali();
}