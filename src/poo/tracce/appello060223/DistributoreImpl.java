package poo.tracce.appello060223;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class DistributoreImpl implements Distributore{

    private List<Prodotto> prodotti = new ArrayList<>();
    private List<Prodotto> venduti = new ArrayList<>();

    @Override
    public void aggiungiProdottoDaVendere(Prodotto p) throws IllegalArgumentException {
        ListIterator<Prodotto> lt = prodotti.listIterator();
        while (lt.hasNext()){
            Prodotto prodotto = lt.next();
            if(prodotto.equals(p)){
                throw new IllegalArgumentException("Prodotto gi&agrave; presente");
            }
            prodotti.add(p);
        }

    }

    @Override
    public void caricaQuantitaNelDistributore(Prodotto p, int quantita) throws IllegalArgumentException {
        ListIterator<Prodotto> lt = prodotti.listIterator();
        while (lt.hasNext()){
            Prodotto prodotto = lt.next();
            if(prodotto.equals(p)){
                double quantitaAttuale = p.getQuantita();
                p.setQuantita(quantitaAttuale + quantita);
            }
            else{
                throw new IllegalArgumentException("Prodotto non presente");
            }
        }

    }

    @Override
    public void vendiProdotto(Prodotto p) throws IllegalArgumentException {
        ListIterator<Prodotto> lt = prodotti.listIterator();
        while (lt.hasNext()){
            lt.next();
            if(p.equals(lt)){
                double quantitaAttuale = p.getQuantita();
                if(quantitaAttuale > 0){
                    p.setQuantita(quantitaAttuale - 1);
                    venduti.add(p);
                }
                else{
                    throw new IllegalArgumentException("Prodotto non disponibile");
                }
            }
        }
        throw new IllegalArgumentException("Prodotto non presente");
    }

    @Override
    public void vendiProdotto(Prodotto p, int quantita) throws IllegalArgumentException {
        ListIterator<Prodotto> lt = prodotti.listIterator();
        while (lt.hasNext()) {
            lt.next();
            if (p.equals(lt)) {
                double quantitaAttuale = p.getQuantita();
                if (quantitaAttuale > quantita) {
                    p.setQuantita(quantitaAttuale - quantita);
                    Prodotto venduto = new Prodotto(p);
                    venduto.setQuantita(quantita);
                } else {
                    throw new IllegalArgumentException("Prodotto non disponibile");
                }
            }
        }
        throw new IllegalArgumentException("Prodotto non presente");
    }

    @Override
    public double incasso() {
        double incasso = 0;
        for(Prodotto p: venduti){
            incasso+=  p.getPrezzo() * p.getQuantita();
        }
        return incasso;
    }

    @Override
    public int disponibilitaProdotto(Prodotto p) {
        ListIterator<Prodotto>  lt = prodotti.listIterator();
        while (lt.hasNext()){
            lt.next();
            if(p.equals(lt)){
                return (int) p.getQuantita();
            }
        }
        return -1;
    }

    @Override
    public int numeroProdottiVenduti(Prodotto p) {
        int counter = 0;
        for(Prodotto venduto: venduti){
            counter+= venduto.getQuantita();
        }
        return counter;
    }

    @Override
    public List<Prodotto> prodottiDisponibili() {
        for(Prodotto p: prodotti){
            if(p.getQuantita() <= 0){
                prodotti.remove(p);
            }
        }
        return prodotti;
    }

    //non so cosa ci devo mettere
    @Override
    public Iterator<Prodotto> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Prodotto> action) {
        Distributore.super.forEach(action);
    }

    public static void main(String[] args) {
        DistributoreImpl distributore = new DistributoreImpl();
        Prodotto p1 = new Prodotto("p1", 10, 10);
        Prodotto p2 = new Prodotto("p2", 10, 10);
        Prodotto p3 = new Prodotto("p3", 10, 10);
        Prodotto p4 = new Prodotto("p4", 10, 10);
        Prodotto p5 = new Prodotto("p5", 10, 10);

        distributore.aggiungiProdottoDaVendere(p1);
        distributore.aggiungiProdottoDaVendere(p2);
        distributore.aggiungiProdottoDaVendere(p3);
        distributore.aggiungiProdottoDaVendere(p4);
        distributore.aggiungiProdottoDaVendere(p5);

        distributore.caricaQuantitaNelDistributore(p1, 10);
        distributore.caricaQuantitaNelDistributore(p2, 10);
        distributore.caricaQuantitaNelDistributore(p3, 10);
        distributore.caricaQuantitaNelDistributore(p4, 10);

        System.out.println(distributore.disponibilitaProdotto(p1));
        System.out.println(distributore.disponibilitaProdotto(p2));
    }
}
