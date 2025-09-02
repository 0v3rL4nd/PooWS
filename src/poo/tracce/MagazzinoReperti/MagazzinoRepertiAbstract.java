package poo.tracce.MagazzinoReperti;

import java.util.*;

public abstract class MagazzinoRepertiAbstract implements MagazzinoReperti{

    public String toString(){
        StringBuilder sb = new StringBuilder();
        List<RepertoArcheologico> reperti = new ArrayList<>();

        for( int i = 0; i <= this.getNumeroCassette(); i++ ) {
            sb.append("Cassetta ").append(i).append(": \n");
            sb.append("Tipo: ").append(getTipoCassetta(i)).append("\n");
            reperti.add(getRepertiInCassetta(i));
            for (RepertoArcheologico r : reperti) {
                sb.append(r.toString()).append("\n");
            }
        }
        return sb.toString() ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;             // stesso oggetto
        if (!(obj instanceof MagazzinoReperti)) return false;

        MagazzinoReperti other = (MagazzinoReperti) obj;

        // Controllo numero di cassette
        if (this.getNumeroCassette() != other.getNumeroCassette())
            return false;

        // Confronto i contenuti di ciascuna cassetta
        for (int i = 0; i < getNumeroCassette(); i++) {
            List<RepertoArcheologico> lista1 = new ArrayList<>((Collection) this.getRepertiInCassetta(i));
            List<RepertoArcheologico> lista2 = new ArrayList<>((Collection) other.getRepertiInCassetta(i));

            // Ordiniamo le liste per garantire confronto coerente
            Collections.sort(lista1, Comparator.comparing(RepertoArcheologico::getId));
            Collections.sort(lista2, Comparator.comparing(RepertoArcheologico::getId));

            if (!lista1.equals(lista2)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for(int i = 0; i <= getNumeroCassette(); i++){
            List<RepertoArcheologico> lista1 = new ArrayList<>((Collection) this.getRepertiInCassetta(i));
            Collections.sort(lista1, Comparator.comparing(RepertoArcheologico::getId));
            result = 31 * result + lista1.hashCode();
        }
        return result;
    }
}
