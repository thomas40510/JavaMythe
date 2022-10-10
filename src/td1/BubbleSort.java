package td1;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
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
        System.out.println("Tableau après tri : " + Arrays.toString(bubbleSort(tab)));

    }

    public static int[] bubbleSort(int[] table){
        /* Trie une table par ordre croissant par tri bulle */
        int n = table.length;
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (table[j] > table[j + 1]){
                    int temp = table[j];
                    table[j] = table[j + 1];
                    table[j + 1] = temp;
                }
            }
        }
        return table;
    }
}
