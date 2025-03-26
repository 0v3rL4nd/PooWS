package poo.tracce.appelloMAGAZZINO;

public class RepertoArcheologico {
    private final String tipo;
    private final double peso;

    public RepertoArcheologico(String tipo, double peso){
        this.tipo = tipo;
        this.peso = peso;
    }

    public RepertoArcheologico( RepertoArcheologico r ){
        this.tipo = r.tipo;
        this.peso = r.peso;
    }

    public String getTipo(){
        return tipo;
    }

    public double getPeso(){
        return peso;
    }

}