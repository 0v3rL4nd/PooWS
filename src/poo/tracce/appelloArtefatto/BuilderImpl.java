package poo.tracce.appelloArtefatto;

import java.util.Iterator;
import java.util.List;

public class BuilderImpl extends BuilderAbt{

    List<Artefatto> artefattiCompletati, artefattiInCostruzione;

    @Override
    public void iniziaCostruzioneArtefatto(String nome, String descrizione) throws IllegalStateException {

        boolean flag = false;

        for(Artefatto a: artefattiInCostruzione){
            if(a.getNome().equals(nome)) flag = true;
            if(flag){
                throw new IllegalStateException();
            }else{
                artefattiInCostruzione.add(new Artefatto(nome, descrizione, List.of()));
            }
        }

    }

    @Override
    public void artefattoCompletato(String nome) throws IllegalStateException {

        boolean flag = false;

        for(Artefatto a: artefattiInCostruzione){
            if(a.getNome().equals(nome)) flag = true;
            if(flag){
                artefattiInCostruzione.remove(a);
                artefattiCompletati.add(a);
            } else{
                throw new IllegalStateException();
            }
        }
    }

    @Override
    public void aggiungiComponente(String nome, String componente) throws IllegalStateException {

        boolean flag = false;

        for(Artefatto a: artefattiInCostruzione) {
            for (String s : a.getComponenti()) {
                if (a.getNome().equals(nome) && !(s.equals(componente))) flag = true;
                if (flag) {
                    a.getComponenti().add(componente);
                }
                else{
                    throw new IllegalStateException();
                }
            }
        }
    }

    @Override
    public Artefatto dammiArtefatto(String nome) {
        for(Artefatto a: artefattiCompletati){
            if(a.getNome().equals(nome)){
                return a;
            }
        }
        return null;
    }

    @Override
    public List<String> elencoArtefattiCompleti() {
        List<String>  artefatti = List.of();
        for(Artefatto a: artefattiCompletati){
            artefatti.add(a.getNome());
        }
        return artefatti;
    }

    @Override
    public List<String> elencoArtefattiInCostruzione() {
        List<String>  artefatti = List.of();
        for(Artefatto a: artefattiInCostruzione){
            artefatti.add(a.getNome());
        }
        return artefatti;
    }

    @Override
    public boolean esisteArtefatto(String nome) {
        boolean flag1 = false;
        boolean flag2 = false;

        for(Artefatto a: artefattiCompletati){
            if(a.getNome().equals(nome)) flag1 = true;
        }
        for(Artefatto a: artefattiInCostruzione){
            if(a.getNome().equals(nome)) flag2 = true;
        }
        return flag1 || flag2;

    }

    @Override
    public boolean rimuoviArtefatto(String nome) {
        boolean flag1 = artefattiCompletati.removeIf(a -> a.getNome().equals(nome));
        boolean flag2 = artefattiInCostruzione.removeIf(a -> a.getNome().equals(nome));
        return flag1 || flag2;
    }


    @Override
    public Iterator<Artefatto> iterator() {
        return null;
    }




}
