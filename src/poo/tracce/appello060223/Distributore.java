package poo.tracce.appello060223;

import java.util.List;

public interface Distributore extends Iterable<Prodotto> {

    void aggiungiProdottoDaVendere(Prodotto p) throws IllegalArgumentException;

    void caricaQuantitaNelDistributore(Prodotto p, int quantita) throws IllegalArgumentException;

    void vendiProdotto(Prodotto p) throws IllegalArgumentException;

    void vendiProdotto(Prodotto p, int quantita) throws IllegalArgumentException;

    double incasso();

    int disponibilitaProdotto(Prodotto p);

    int numeroProdottiVenduti(Prodotto p);

    List<Prodotto> prodottiDisponibili();

}
