package td1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Temp {
    public static void main(String[] args) {
        String[] cities = new String[]{"Brest", "Paris", "Rennes",
                "Toulouse", "Nice", "Lyon"};

        String city = cities[menu(cities) - 1];
        System.out.println("Vous avez choisi " + city);
        double temp = selectTemp();

        writeFile("./src/td1/"+city+".txt", temp);
    }
    public static int menu(String[] cities){
        /* Affiche un menu de choix de ville et renvoie l'indice de la ville choisie */
        System.out.println("Choisissez une ville :");

        System.out.println("0 : Exit");
        for (int i = 0; i < cities.length; i++){
            System.out.println(i+1 + " : " + cities[i]);
        }
        System.out.println("\nVotre choix : ");
        int input = new Scanner(System.in).nextInt();
        System.out.println("Vous avez choisi " + input);
        while (input < 0 || input > cities.length){
            System.out.println("Choix invalide, veuillez réessayer.");
            input = menu(cities);
        }
        return input;
    }

    public static double selectTemp(){
        /* Demande à l'utilisateur de saisir une température et la renvoie */
        System.out.println("Saisissez une température : ");
        return new Scanner(System.in).nextDouble();
    }

    public static void writeFile(String path, double temp){
        /* Écrit dans un fichier la température de la ville choisie */
        try {
            FileWriter fw = new FileWriter(path, true);
            // get current date time with Date()
            Date date = new Date();
            fw.write(date + " : " + temp + "°C" + "\n");
            fw.close();
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
