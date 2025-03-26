package poo.tracce.appelloRoulette;

import java.util.*;

public class PermutazioniNoBacktracking {
    private int[] a;

    public PermutazioniNoBacktracking(int[] a) {
        if (haDuplicati(a)) {
            throw new RuntimeException("L'array contiene elementi duplicati");
        }
        this.a = a;
    }

    private boolean haDuplicati(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public void generaPermutazioni() {
        permuta(a, 0);
    }

    private void permuta(int[] array, int index) {
        if (index == array.length - 1) {
            System.out.println(Arrays.toString(array));
            return;
        }
        for (int i = index; i < array.length; i++) {
            scambia(array, index, i);
            permuta(array, index + 1);
            scambia(array, index, i);
        }
    }

    private void scambia(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }



    public static void main(String[] args) {
        int[] numeri = {1, 2, 3};
        Permutazioni permutazioni = new Permutazioni(numeri);
        //permutazioni.generaPermutazioni();
    }
}
