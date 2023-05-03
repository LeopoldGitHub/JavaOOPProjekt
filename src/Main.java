import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<PCParts> shopInventory = new ArrayList<>();

    public static void main(String[] args) {
        createTestDatea();


        while (true) {
            mainMenu();
            inputMainMenu();
//            for (PCParts part :
//                    shopInventory) {
//                System.out.println(part);
//            }
        }


    }

    protected static void createTestDatea() {
        shopInventory.add(new Mice(2000, "Logitech", "G5", 90));
        shopInventory.add(new Mice(1000, "Logitech", "G3", 40));
        shopInventory.add(new Mice(20000, "Logitech", "PRO", 120));
        shopInventory.add(new Display(29, "Asus", "PB29AB", 400));
        shopInventory.add(new Display(29, "GIGABYTE", "GP29EH", 500));


    }

    public static void mainMenu() {
        System.out.println("""
                -------------------------------------------------------------------------------------
                PC-Shop Hauptmenü von: Jan Leopold
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

    protected static void inputMainMenu() {
        String input = sc.nextLine().trim();
        if (input.matches("[0-4]")) {
            switch (Integer.parseInt(input)) {

                case 0 -> System.exit(0);
                case 1 -> createProduct();
                case 2 -> changeProduct();
                case 3 -> searchProduct();
                case 4 -> deleteProduct();
            }
        } else {
            System.out.println("Fehlerhafte Eingabe");
        }
    }

    public static void createProduct() {
        System.out.println("""
                -------------------------------------------------------------------------------------
                Welches Produkt möchten sie Anlegen?
                -------------------------------------------------------------------------------------
                1) Motherboard
                2) Monitor
                3) Tastatur
                4) Maus
                0) Zurück zum Hauptmenü
                -------------------------------------------------------------------------------------
                Bitte wählen:
                """);


        switch (intInput()) {
            case 1 -> createMotherboard();
            case 2 -> createDisplay();
            case 3 -> createKeyboard();
            case 4 -> createMice();
        }


    }


    protected static void createMotherboard() {
        while (true) {

            TrippleTuple temp = pcPartsInput();
            System.out.println("Bitte Chipset angeben:");
            String chipset = sc.nextLine();
            if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || chipset.isEmpty()) {
                System.out.println("Fehlerhafte Eingabe");
                return;
            }
            shopInventory.add(new Motherboard(chipset, temp.brand, temp.model, temp.price));
            if (anotherInput("Möchten sie noch ein Product Hinzufügen?")) return;
        }
    }


    protected static void createDisplay() {
        while (true) {
            TrippleTuple temp = pcPartsInput();
            System.out.println("Bitte Display Größe in Zoll angeben:");
            int size = intInput();
            if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || size == 0) {
                System.out.println("Fehlerhafte Eingabe");
                return;
            }
            shopInventory.add(new Display(size, temp.brand, temp.model, temp.price));
            if (anotherInput("Möchten sie noch ein Product Hinzufügen?")) return;

        }

    }

    protected static void createKeyboard() {
        while (true) {
            TrippleTuple temp = pcPartsInput();
            System.out.println("Bitte Keyboard Layout angeben:");
            String layout = sc.nextLine();
            if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || layout.isEmpty()) {
                System.out.println("Fehlerhafte Eingabe");
                return;
            }
            shopInventory.add(new Keyboard(layout, temp.brand, temp.model, temp.price));
            if (anotherInput("Möchten sie noch ein Product Hinzufügen?")) return;

        }

    }

    protected static void createMice() {
        while (true) {
            TrippleTuple temp = pcPartsInput();
            System.out.println("Bitte DPI vom Sensor Angeben:");
            int dpi = intInput();
            if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || dpi == 0) {
                System.out.println("Fehlerhafte Eingabe");
                return;
            }
            shopInventory.add(new Mice(dpi, temp.brand, temp.model, temp.price));
            if (anotherInput("Möchten sie noch ein Product Hinzufügen?")) return;


        }
    }


    protected static void changeProduct() {
        if (shopInventory.size()==0) {
            System.out.println("Keine Produkte vorhanden");
            return;
        }
        IntStream
                .range(0,shopInventory.size())
                .forEach(i -> System.out.printf("Index: %3d Typ: %10s\t%s\n",i+1,shopInventory.get(i).getClass().getName(),shopInventory.get(i)));
        System.out.println("Bitte Nummer des Zu bearbeitenden Objektes eingeben:");
        int input = intInput();
        if (input==0) return;




        System.out.println("change Product");
    }

    protected static void searchProduct() {
        while (true) {
            System.out.println("Wonach möchten Sie suchen?");
            String input = sc.nextLine();
            int n = 0;
            for (PCParts part : shopInventory)
                if (part.toString().toLowerCase().contains(input.toLowerCase())) System.out.printf("%3d.: %s\n", ++n, part);

            System.out.printf("%3d Übereinstimmungen gefunden.\n",n);
            if (anotherInput("Möchten sie noch weiter Suchen")) return;

        }

    }

    protected static void deleteProduct() {
        System.out.println("delete Product");

    }

    protected static int intInput() {
        String input = sc.nextLine();

        if (input.trim().matches("[0-9]+")) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }

    }

    protected static TrippleTuple pcPartsInput() {

        System.out.println("Bitte Marke angeben:");
        String brand = sc.nextLine();
        System.out.println("Bitte Model angeben:");
        String model = sc.nextLine();
        System.out.println("Bitte Preis angeben:");
        float price = Float.parseFloat(sc.nextLine().replace(",", "."));
        return new TrippleTuple(brand, model, price);

    }

    protected static boolean anotherInput(String question) {
        System.out.printf("%s (ja / nein)\n", question);
        String input = sc.nextLine().toLowerCase();
        return !input.matches("ja|j|yes|y");
    }

}