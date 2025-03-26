package poo.tracce.appelloSCARPE;

import java.io.*;

public class CampionarioScarpee {

    //Dato un negozio di scarpe, il metodo provvede a costruire un file testuale in cui vengono poste, per ogni linea
    // il nome del modello di scarpe presenti nel negozio, seguito dalle misure disponibili per quel modello
    public static void SalvaCampionario( NegozioScarpe n, File f ) throws IOException {
        FileWriter fw = new FileWriter(f);
        try{
            for ( String ms : n.scarpeDisponibili() ) {
                fw.write( ms + " " + n.misureDisponibili(ms).toString() + "\n");
            }
        }
        catch ( IOException e ) {
            System.out.println(" ?? ");
        }
        finally {
            fw.close();
        }
    }

    //Legge un file testuale sorgente in cui è presente un campionario e produce in output un secondo file sedtale in cui
    //su ogni linea sono presenti, per le misure comprese fr MIN E MAX, la specifica misura seguita dall'elenco dei modelli di
    //scarpe disponibili
    public static void ElaboraCampionario( File src, File dest, int minNum, int maxNum ) throws IOException {
        FileReader s = new FileReader( src );
        FileWriter d = new FileWriter( dest );
        BufferedReader  br = new BufferedReader( s );
        BufferedWriter bw = new BufferedWriter( d );
        
        String line;
        
        try{
            while ( (line = br.readLine()) != null ) {
                String[] split = line.split(" ");
                
                String modello = split[0];
                String misure = split[1];
                
                String[] misureSplit = misure.split(",");
                
                for ( String misura : misureSplit ) {
                    int num = Integer.parseInt( misura );
                    if ( num >= minNum && num <= maxNum ) {
                        bw.write( misura + " " + modello + "\n");
                    }
                }
            }

        }
        catch ( IOException e ) {
            System.out.println(" ?? ");
        }
        finally {
            s.close();
            d.close();
        }

    }

}
