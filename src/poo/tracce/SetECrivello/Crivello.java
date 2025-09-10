package poo.tracce.SetECrivello;

class Crivello {

    public static Set<Integer> crivellobel(int N) {
        // Inizializza il crivello con tutti i numeri da 2 a N
        SetLL<Integer> crivello = new SetLL<>();
        for (int i = 2; i <= N; i++) {
            crivello.add(i);
        }

        // Per tutti i numeri x da 2 a sqrt(N)
        for (int x = 2; x <= Math.sqrt(N); x++) {
            if (crivello.contains(x)) {
                // Rimuovi tutti i multipli di x (eccetto x stesso)
                for (int multiplo = x * 2; multiplo <= N; multiplo += x) {
                    crivello.remove(multiplo);
                }
            }
        }

        return crivello;
    }

    // Main separato per testare solo il crivello
    public static void main(String[] args) {
        System.out.println("=== Test Crivello di Eratostene ===");

        // Test con diversi valori di N
        int[] valoriTest = {10, 20, 30, 50, 100};

        for (int N : valoriTest) {
            Set<Integer> primi = crivellobel(N);
            System.out.println("Numeri primi fino a " + N + ": " + primi);
            System.out.println("Totale numeri primi: " + primi.size());
            System.out.println();
        }
    }
}
