package utilities;
public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public void e(String message){
        System.out.println(ANSI_RED + "[Log/E]: " + message + ANSI_RESET);
    }
    public void i(String message){
        System.out.println("[Log/I]: " + message);
    }
    public void d(String message){
        System.out.println(ANSI_BLUE + "[Log/D]: " + message + ANSI_RESET);
    }

}

