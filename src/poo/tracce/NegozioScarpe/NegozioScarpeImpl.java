package poo.tracce.NegozioScarpe;

import java.util.*;

public class NegozioScarpeImpl implements NegozioScarpe {
    public static class coppia{
        String modello;
        int misura;

        public coppia(String modello, int misura) {
            this.modello = modello;
            this.misura = misura;
        }

        public String getModello() {
            return modello;
        }

        public int getMisura() {
            return misura;
        }
    }

    List<coppia> Negozioscarpe = new ArrayList<>();

    public List<coppia> getNegozioScarpe() {
        return Negozioscarpe;
    }


    @Override
    public void aggiungi(String modelloScarpa, int misura) {
        for(coppia c: Negozioscarpe){
            if(c.getModello().equals(modelloScarpa) && c.getMisura()==misura) Negozioscarpe.add(new coppia(modelloScarpa, misura));
        }
        Negozioscarpe.add(new coppia(modelloScarpa, misura));
    }

    @Override
    public boolean vendi(String modelloScarpa, int misura) throws IllegalArgumentException {
        try{
        for(coppia c: Negozioscarpe){
            if(c.getModello().equals(modelloScarpa) && c.getMisura()==misura){
                Negozioscarpe.remove(new coppia(modelloScarpa, misura));
                return true;
            }
        }
        } catch (Exception e){
            throw new IllegalArgumentException("Scarpa non presente");
        }
        return false;
    }

    @Override
    public Set<String> misureDisponibili(String modelloScarpa) {
        List<String> modelli = new ArrayList<>();
        for(coppia c: Negozioscarpe){
            if(c.getModello().equals(modelloScarpa)){
                modelli.add(c.getModello());
            }
        }
        return Set.of(modelli.toString());
    }

    @Override
    public Set<String> scarpeDisponibili() {
        return Set.of(Negozioscarpe.toString());
    }

    @Override
    public boolean eScarpaDisponibile(String modelloScarpa, int misura) {
        for(coppia c: Negozioscarpe){
            if(c.getModello().equals(modelloScarpa) && c.getMisura()==misura) return true;
        }
        return false;
    }

    @Override
    public Iterator<String> scarpeDisponibili(int misura) {
        List<String> scarpe = new ArrayList<>();
        for(coppia c: Negozioscarpe){
            if(c.getMisura()==misura && !(scarpe.contains(c.getModello()))) scarpe.add(c.getModello());
        }
        return scarpe.iterator();
    }
}
