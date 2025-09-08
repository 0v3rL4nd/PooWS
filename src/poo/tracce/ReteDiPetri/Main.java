package poo.tracce.ReteDiPetri;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Costruiamo la rete di esempio mostrata nell'immagine

        // Creazione dei posti con marcature iniziali
        Map<String, Posto> M = new HashMap<>();
        M.put("p1", new Posto("p1", 1)); // p1 ha 1 token inizialmente
        M.put("p2", new Posto("p2", 0));
        M.put("p3", new Posto("p3", 0));
        M.put("p4", new Posto("p4", 0));

        // Creazione delle transizioni
        LinkedList<Transizione> T = new LinkedList<>();

        // Transizione t1: p1 -> p2
        List<Arco> preset_t1 = Arrays.asList(new ArcoIn(M.get("p1")));
        List<Arco> postset_t1 = Arrays.asList(new ArcoOut(M.get("p2")));
        Transizione t1 = new Transizione("t1", preset_t1, postset_t1);
        T.add(t1);

        // Transizione t2: p2 -> p3
        List<Arco> preset_t2 = Arrays.asList(new ArcoIn(M.get("p2")));
        List<Arco> postset_t2 = Arrays.asList(new ArcoOut(M.get("p3")));
        Transizione t2 = new Transizione("t2", preset_t2, postset_t2);
        T.add(t2);

        // Transizione t3: p2 -> p4
        List<Arco> preset_t3 = Arrays.asList(new ArcoIn(M.get("p2")));
        List<Arco> postset_t3 = Arrays.asList(new ArcoOut(M.get("p4")));
        Transizione t3 = new Transizione("t3", preset_t3, postset_t3);
        T.add(t3);

        // Transizione t4: p3 -> (nessun posto di output nell'esempio)
        List<Arco> preset_t4 = Arrays.asList(new ArcoIn(M.get("p3")));
        List<Arco> postset_t4 = new ArrayList<>(); // vuoto
        Transizione t4 = new Transizione("t4", preset_t4, postset_t4);
        T.add(t4);

        // Transizione t5: p4 -> (nessun posto di output nell'esempio)
        List<Arco> preset_t5 = Arrays.asList(new ArcoIn(M.get("p4")));
        List<Arco> postset_t5 = new ArrayList<>(); // vuoto
        Transizione t5 = new Transizione("t5", preset_t5, postset_t5);
        T.add(t5);

        // Mostra stato iniziale
        System.out.println("=== STATO INIZIALE DELLA RETE ===");
        System.out.println("Posti:");
        for (Posto posto : M.values()) {
            System.out.println("  " + posto);
        }
        System.out.println("Transizioni:");
        for (Transizione trans : T) {
            System.out.println("  " + trans);
        }

        // Simulazione dell'evoluzione della rete
        System.out.println("\n=== SIMULAZIONE DELL'EVOLUZIONE ===");

        // Primo sparo: t1
        System.out.println("\n1. Facendo sparare t1:");
        t1.sparo();
        mostraStatoRete(M, T);

        // Secondo sparo: scegliere tra t2 e t3 (entrambi abilitati)
        System.out.println("\n2. Ora t2 e t3 sono entrambi abilitati. Facendo sparare t2:");
        t2.sparo();
        mostraStatoRete(M, T);

        // Reset per testare l'altro percorso
        System.out.println("\n=== RESET E PROVA ALTERNATIVA ===");
        resetRete(M);
        System.out.println("Rete resettata allo stato iniziale");

        System.out.println("\n3. Facendo sparare t1 poi t3:");
        t1.sparo();
        t3.sparo();
        mostraStatoRete(M, T);

        // Verifica possibili deadlock
        System.out.println("\n=== VERIFICA DEADLOCK ===");
        verificaDeadlock(T);
    }

    private static void mostraStatoRete(Map<String, Posto> M, LinkedList<Transizione> T) {
        System.out.println("Stato corrente:");
        for (Posto posto : M.values()) {
            System.out.println("  " + posto);
        }
        System.out.println("Transizioni abilitate:");
        for (Transizione trans : T) {
            if (trans.abilitata()) {
                System.out.println("  " + trans.getNome());
            }
        }
    }

    private static void resetRete(Map<String, Posto> M) {
        M.get("p1").setMarcatura(1);
        M.get("p2").setMarcatura(0);
        M.get("p3").setMarcatura(0);
        M.get("p4").setMarcatura(0);
    }

    private static void verificaDeadlock(LinkedList<Transizione> T) {
        boolean nessunaAbilitata = true;
        for (Transizione trans : T) {
            if (trans.abilitata()) {
                nessunaAbilitata = false;
                break;
            }
        }

        if (nessunaAbilitata) {
            System.out.println("DEADLOCK: Nessuna transizione è abilitata!");
        } else {
            System.out.println("Nessun deadlock rilevato.");
        }
    }
}