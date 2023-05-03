import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
        inputMainMenu();


    }

    public static void mainMenu() {
        System.out.println("""
                -------------------------------------------------------------------------------------
                PC-Shop Hauptmenü von: [Ihr Vor - und Nachname]
                -------------------------------------------------------------------------------------
                1) Produkt anlegen
                2) Produkt bearbeiten
                3) Produkt suchen
                4) Produkt löschen
                0) Shop beenden
                -------------------------------------------------------------------------------------
                Bitte wählen:
                """);

    }

    public static void inputMainMenu() {
        String input = sc.next().trim();
        if (input.matches("[0-4]")) {
            switch (Integer.parseInt(input)) {

                case 0 -> System.exit(0);
                case 1 -> ;
                case 2 -> ;
                case 3 -> ;
                case 4 -> ;
            }
        } else {
            System.out.println("Fehlerhafte Eingabe");
            mainMenu();
        }
    }

}