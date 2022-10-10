package td1;

import java.util.Arrays;
import java.util.Random;

public class InsSort {
    private static final Random rnd = new Random();
    public static void main(String[] args) {
        int[] tab = new int[30];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = rnd.nextInt(50);
        }
        System.out.println("Tableau avant tri :");
        for (int j : tab) {
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println("Tableau après tri : " + Arrays.toString(insSort(tab)));

    }

    public static int[] insSort(int[] table){
        /* Trie une table par ordre croissant par tri par insertion */
        int n = table.length;
        for (int i = 1; i < n; ++i) {
            int key = table[i];
            int j = i - 1;
            while (j >= 0 && table[j] > key) {
                table[j + 1] = table[j];
                j = j - 1;
            }
            table[j + 1] = key;
        }
        return table;
    }
}
