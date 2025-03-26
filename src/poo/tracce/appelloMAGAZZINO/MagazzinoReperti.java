package poo.tracce.appelloMAGAZZINO;

import java.util.List;

public interface MagazzinoReperti {

    int addReperto( RepertoArcheologico r);
    boolean removeReperto( int id );
    double dammiPesoCassetta( int id);
    RepertoArcheologico getReperto( int id);
    List<RepertoArcheologico> getRepertiInCassetta( int id );
    List<String> getTipi();
    String getTipoCassetta();
    int getNumeroCassetta();

}
