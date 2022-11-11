package td4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the file to read:");
        String filename = in.next();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while(line != null){
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e){
            System.out.println("Error: " + e.getMessage());
            ReadFile.main(args);
        }
    }
}
