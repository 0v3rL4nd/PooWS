package poo.tracce.ReteDiPetri;

import java.util.*;

class Transizione extends Entita {
    private List<Arco> preset;  // archi di ingresso
    private List<Arco> postset; // archi di uscita

    public Transizione(String nome, List<Arco> preset, List<Arco> postset) {
        super(nome);
        this.preset = preset != null ? new ArrayList<>(preset) : new ArrayList<>();
        this.postset = postset != null ? new ArrayList<>(postset) : new ArrayList<>();
    }

    public List<Arco> getPreset() {
        return new ArrayList<>(preset);
    }

    public List<Arco> getPostset() {
        return new ArrayList<>(postset);
    }

    // Verifica se la transizione è abilitata
    public boolean abilitata() {
        for (Arco arco : preset) {
            if (arco instanceof ArcoIn) {
                ArcoIn arcoIn = (ArcoIn) arco;
                if (arcoIn.getPosto().getMarcatura() < arcoIn.getPeso()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Fa scattare la transizione se abilitata
    public void sparo() {
        if (!abilitata()) {
            System.out.println("Transizione " + nome + " non abilitata!");
            return;
        }

        // Consuma token dai posti di input
        for (Arco arco : preset) {
            if (arco instanceof ArcoIn) {
                ArcoIn arcoIn = (ArcoIn) arco;
                Posto posto = arcoIn.getPosto();
                posto.setMarcatura(posto.getMarcatura() - arcoIn.getPeso());
            }
        }

        // Genera token nei posti di output
        for (Arco arco : postset) {
            if (arco instanceof ArcoOut) {
                ArcoOut arcoOut = (ArcoOut) arco;
                Posto posto = arcoOut.getPosto();
                posto.setMarcatura(posto.getMarcatura() + arcoOut.getPeso());
            }
        }

        System.out.println("Transizione " + nome + " scattata!");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transizione that = (Transizione) obj;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode()*31;
    }

    @Override
    public String toString() {
        return nome + " (abilitata: " + abilitata() + ")";
    }
}