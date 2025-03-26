package Cruciverba;

import java.util.List;

public interface Cruciverba{
    int getNumeroRighe();
    int getNumeroColonne();
    default boolean contains( String parola ){
        return paroleOrizzontali().contains(parola) || paroleVerticali().contains(parola);
    }
    List<String> paroleOrizzontali();
    List<String> paroleVerticali();
}
