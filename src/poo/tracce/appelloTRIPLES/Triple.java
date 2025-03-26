package poo.tracce.appelloTRIPLES;

import java.util.*;

// Primo esercizio: Implementazione della classe Triple
class Triple {
    private int[] a;
    private int x;
    private List<int[]> solutions;
    private int[] b;

    public Triple(int[] a, int x) {
        if (a == null || a.length < 3 || !isUnique(a)) {
            throw new IllegalArgumentException("L'array deve contenere almeno 3 elementi unici.");
        }
        this.a = a;
        this.x = x;
        this.solutions = new ArrayList<>();
        this.b = new int[3];
        findTriples(0, 0, 0);
    }

    private boolean isUnique(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (!set.add(num)) {
                return false;
            }
        }
        return true;
    }

    private void findTriples(int index, int start, int sum) {
        if (index == 3) {
            if (sum == x) {
                solutions.add(b.clone());
            }
            return;
        }
        for (int i = start; i < a.length; i++) {
            b[index] = a[i];
            findTriples(index + 1, i + 1, sum + a[i]);
        }
    }

    public List<int[]> getSolutions() {
        return solutions;
    }
}