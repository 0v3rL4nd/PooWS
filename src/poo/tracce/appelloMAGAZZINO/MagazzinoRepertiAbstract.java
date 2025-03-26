package poo.tracce.appelloMAGAZZINO;

import java.util.List;


public  abstract class MagazzinoRepertiAbstract implements MagazzinoReperti {
    List<RepertoArcheologico> cassetta;
    @Override
    public int addReperto(RepertoArcheologico r) {
        if( cassetta.isEmpty()){
            cassetta.add(r);
            return 0;
        }
        else{
            if( cassetta.get(0).getTipo().equals(r.getTipo()) ){
                cassetta.add(r);
                return 0;
            }
        }
        return -1;
    }

    @Override
    public boolean removeReperto(int id) {
        return false;
    }

    @Override
    public double dammiPesoCassetta(int id) {
        return 0;
    }

    @Override
    public RepertoArcheologico getReperto(int id) {
        return null;
    }

    @Override
    public List<RepertoArcheologico> getRepertiInCassetta(int id) {
        return List.of();
    }

    @Override
    public List<String> getTipi() {
        return List.of();
    }

    @Override
    public String getTipoCassetta() {
        return "";
    }

    @Override
    public int getNumeroCassetta() {
        return 0;
    }
}
