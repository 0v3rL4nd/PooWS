package poo.tracce.SaggioMusicale;

import java.util.Set;

abstract class SaggioMusicaleAbstract implements SaggioMusicale {

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Set<String> musicisti =  this.musicisti();
        for(String musicista: musicisti){
            sb.append(musicista);
            sb.append(": ");
            sb.append(this.dammiStrumenti(new String[]{musicista}));
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaggioMusicale)) return false;
        SaggioMusicale other = (SaggioMusicale) o;
        Set<String> musicistiThis = this.musicisti();
        Set<String> musicistiOther = other.musicisti();
            for(String musicistaThis: musicistiThis){
                for(String musicistaOther: musicistiOther){
                    if(musicistaOther != musicistaThis) return false;
                    String[] strumentiThis = this.dammiStrumenti(new String[]{musicistaThis}).toArray(new String[0]);
                    String[] strumentiOther = other.dammiStrumenti(new String[]{musicistaOther}).toArray(new String[0]);
                    if((suonaComePrimoStrumento(musicistaThis, strumentiThis[0]) != suonaComePrimoStrumento(musicistaOther, strumentiOther[0])) ||
                            (suonaComeSecondoStrumento(musicistaThis, strumentiThis[1])) != suonaComeSecondoStrumento(musicistaOther, strumentiOther[1])) return false;
            }
        }
            return true;
    }
    public int hashCode(){
        int hash = 31;
        Set<String> musicisti = musicisti();
        Set<String> strumenti = strumenti();
        for(String musicista: musicisti){
            for(String strumento: strumenti){
                hash *= musicista.hashCode()*strumento.hashCode();
            }
        }
        return hash;
    }
}
