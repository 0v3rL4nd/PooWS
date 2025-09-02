package poo.tracce.MagazzinoReperti;

import java.util.*;

public class MagazzinoRepertiImpl implements MagazzinoReperti {

    private final List<List<RepertoArcheologico>> cassette;

    public MagazzinoRepertiImpl(List<List<RepertoArcheologico>> cassette) {
        this.cassette = cassette;
    }

    @Override
    public int addReperto(RepertoArcheologico r) {
        String tipo = r.getNome();

        for(int i = 0; i<= cassette.size(); i++){
            List<RepertoArcheologico> lista = cassette.get(i);
            if(!lista.isEmpty() && lista.get(0).getNome().equals(tipo)){
                lista.add(r);
                return i;
            }
        }
        List<RepertoArcheologico> nuovaCassetta = new ArrayList<>();
        nuovaCassetta.add(r);
        return cassette.size()-1;
    }

    @Override
    public boolean removeReperto(int idReperto) {
        for(List<RepertoArcheologico> lista: cassette){
            Iterator<RepertoArcheologico> it = lista.iterator();
            while (it.hasNext()){
                RepertoArcheologico r = it.next();
                if(r.getId() == idReperto){
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void dammiSpecCassetta(int idCassetta) {

    }

    @Override
    public RepertoArcheologico getReperto(int idReperto) {
        for(List<RepertoArcheologico> lista: cassette){
            for(RepertoArcheologico r: lista){
                if(r.getId() == idReperto){
                    return r;
                }
            }
        }
        return null;
    }

    @Override
    public RepertoArcheologico getRepertiInCassetta(int idCassetta) {
        return null;
    }

    @Override
    public List<String> getListaTipi() {
        return List.of();
    }

    @Override
    public String getTipoCassetta(int idCassetta) {
        return "";
    }

    @Override
    public int getNumeroCassette() {
        return 0;
    }
}
