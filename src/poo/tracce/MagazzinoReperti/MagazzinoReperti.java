package poo.tracce.MagazzinoReperti;

import java.util.List;

/* Il magazzino contiene i reperti archeologici divisi in cassette. Ogni cassetta è
   identificata da un ID numerico crescente che parte da zero. Una cassetta può ospitare una
   sola tipologia di reperti. NON deve essere introdotto il tipo CASSETTA. In caso di
   parametri scorretti si deve lanciare una eccezione che notifichi l'errore. Gli unici
   metodi che modificano il contenuto della struttura dati sono AGGIUNGIREPERTO e
   RIMUOVIREPERTO. */
public interface MagazzinoReperti {
    /* Aggiunge un reperto nel magazzino e restituisce l'ID della cassetta in cui è stato
       inserito. Se non esiste cassetta contenente il tipo di reperto, viene definita una nuova
       cassetta. In caso di parametri non validi lanciare eccezione */
    int addReperto(RepertoArcheologico r);

    /* Rimuove un reperto dalla cassetta che lo contiene. Dopo questa operazione la cassetta
       potrebbe rimanere o meno vuota. Restituisce un boolean legato al buon esito della
       rimozione */
    boolean removeReperto(int idReperto);

    /* Dato l'ID di una cassetta restituisce il tipo di reperto contenuto in una cassetta. La cassetta deve esistere */
    void dammiSpecCassetta(int idCassetta);

    /* Restituisce la cassetta (ID) corrispondente all'idReperto, si restituisce NULL se il reperto non esiste nel magazzino */
    RepertoArcheologico getReperto(int idReperto);

    /* Restituisce la lista dei reperti in una cassetta. L'idCassetta deve essere valido */
    RepertoArcheologico getRepertiInCassetta(int idCassetta);

    /* Restituisce la lista dei tipi dei reperti */
    List<String> getListaTipi();

    /* Restituisce il tipo di reperto contenuto nella cassetta idCassetta. L'idCassetta
       deve essere valido */
    String getTipoCassetta(int idCassetta);

    /* Restituisce il numero di cassette presenti nel magazzino */
    int getNumeroCassette();
}
