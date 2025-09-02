package poo.tracce.NegozioScarpe;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNegozioDiScarpe implements NegozioScarpe {

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("==Negozio scarpe==");
        List<String> scarpe = new ArrayList<>(scarpeDisponibili());
        for(String scarpa: scarpe){
            sb.append(scarpa).append(misureDisponibili(scarpa)).append("\n");
        }
        return sb.toString();
    }

    public boolean equals(Object obj){
        if(obj==this) return true;
        if(!(obj instanceof NegozioScarpe)) return false;
        NegozioScarpe other = (NegozioScarpe) obj;
        List<String> scarpeThis = new ArrayList<>(this.scarpeDisponibili());
        List<String> scarpeOther = new ArrayList<>(other.scarpeDisponibili());
        if(scarpeThis.size()!=(scarpeOther.size())) return false;
        for(String scarpathis: scarpeThis){
           for(String scapraother: scarpeOther){
               if(scarpathis.equals(scapraother)){
                   if(!this.misureDisponibili(scarpathis).equals(other.misureDisponibili(scarpathis))) return false;
               }
               return false;
           }
           return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int res = 1;
        List<String> scarpe = new ArrayList<>(scarpeDisponibili());
        for(String scarpa: scarpe){
            res = 31 * res * scarpa.hashCode() * misureDisponibili(scarpa).hashCode();
        }
        return res;
    }
}
