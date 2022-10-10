import java.util.Random;

public class TD1 {
    private static final Random rnd  = new Random();
    public static void main(String[] args) {
        noPain();
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("fibonacci(5) = " + fibonacci(5));
        System.out.println("Ack(3, 4) = " + ack(3, 4));
        System.out.println("gcd(12, 18) = " + gcd(12, 18));
    }
    public static void noPain(){
        /* Optimisation de noPain */
        String[] list = new String[]{"M", "P", "G"};
        String res = list[rnd.nextInt(3)];
        System.out.println(res + "ain");
    }

    public static int factorial(int n){
        /* Calcule la factorielle d'un entier par récursivité */
        return (n == 1) ? 1 : n * factorial(n - 1);
    }

    public static int fibonacci(int n){
        /* Calcule le n-ième terme de la suite de Fibonacci */
        return (n == 1 || n == 2) ? 1 : fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int ack(int m, int n){
        /* Calcule la fonction d'Ackermann */
        return (m == 0) ? n + 1 : (n == 0) ? ack(m - 1, 1) : ack(m - 1, ack(m, n - 1));
    }

    public static int gcd(int a, int b){
        /* Calcule le PGCD de deux entiers par l'algorithme d'Euclide */
        return (b == 0) ? a : gcd(b, a % b);
    }
}