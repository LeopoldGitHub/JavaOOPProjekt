import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<PCParts> shopInventory = new ArrayList<>();

    public static void main(String[] args) {
        createTestData();


        while (true) {
            mainMenu();
            inputMainMenu();

        }


    }


    protected static void createTestData() {
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

                case 0 -> quitShop();
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
            case 1 -> addMotherboard();
            case 2 -> addDisplay();
            case 3 -> addKeyboard();
            case 4 -> addMice();
        }


    }

    protected static void addMotherboard() {
        while (true) {
            try {
                shopInventory.add(createMotherboard());
                if (anotherInput("Möchten sie noch ein Mainboard Hinzufügen?")) return;
            } catch (RuntimeException e) {
                break;
            }
        }

    }

    protected static void addDisplay() {
        while (true) {
            try {
                shopInventory.add(createDisplay());
                if (anotherInput("Möchten sie noch ein Monitor Hinzufügen?")) return;
            } catch (RuntimeException e) {
                break;
            }

        }
    }

    protected static void addKeyboard() {
        while (true) {
            try {
                shopInventory.add(createKeyboard());
                if (anotherInput("Möchten sie noch eine Tastatur Hinzufügen?")) return;
            } catch (RuntimeException e) {
                break;
            }
        }

    }

    protected static void addMice() {
        while (true) {
            try {
                shopInventory.add(createMice());
                if (anotherInput("Möchten sie noch eine Maus Hinzufügen?")) return;
            } catch (RuntimeException e) {
                break;
            }
        }
    }


    protected static Motherboard createMotherboard() {
        TrippleTuple temp = pcPartsInput();
        System.out.println("Bitte Chipset angeben:");
        String chipset = sc.nextLine().trim();
        if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || chipset.isEmpty()) {
            System.out.println("Fehlerhafte Eingabe");
            throw new RuntimeException();
        }
        return new Motherboard(chipset, temp.brand, temp.model, temp.price);

    }


    protected static Display createDisplay() {

        TrippleTuple temp = pcPartsInput();
        System.out.println("Bitte Display Größe in Zoll angeben:");
        int size = intInput();
        if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || size == 0) {
            System.out.println("Fehlerhafte Eingabe");
            throw new RuntimeException();
        }
        return new Display(size, temp.brand, temp.model, temp.price);


    }

    protected static Keyboard createKeyboard() {

        TrippleTuple temp = pcPartsInput();
        System.out.println("Bitte Keyboard Layout angeben:");
        String layout = sc.nextLine().trim();
        if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || layout.isEmpty()) {
            System.out.println("Fehlerhafte Eingabe");
            throw new RuntimeException();
        }
        return new Keyboard(layout, temp.brand, temp.model, temp.price);

    }


    protected static Mice createMice() {

        TrippleTuple temp = pcPartsInput();
        System.out.println("Bitte DPI vom Sensor Angeben:");
        int dpi = intInput();
        if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || dpi == 0) {
            System.out.println("Fehlerhafte Eingabe");
            throw new RuntimeException();
        }
        return new Mice(dpi, temp.brand, temp.model, temp.price);


    }


    protected static void changeProduct() {
        if (shopInventory.size() == 0) {
            System.out.println("Keine Produkte vorhanden");
            return;
        }
        while (true) {
            showProducts();
            System.out.println("Bitte Nummer des Zu bearbeitenden Objektes eingeben:");
            int input = intInput();
            if (input == 0) return;
            input--;
            try {


                switch (shopInventory.get(input).getClass().getName()) {
                    case "Display" -> shopInventory.set(input, createDisplay());
                    case "Keyboard" -> shopInventory.set(input, createKeyboard());
                    case "Mice" -> shopInventory.set(input, createMice());
                    case "Motherboard" -> shopInventory.set(input, createMotherboard());
                }
            } catch (RuntimeException e) {
                System.out.println("Produkt konnte aufgrund leerer Eingabewerte nicht gespeichert werden");
                return;
            }
            if (anotherInput("Möchten sie noch ein Produkt bearbeiten?")) return;
        }
    }



    protected static void searchProduct() {
        while (true) {
            System.out.println("Wonach möchten Sie suchen?");
            String input = sc.nextLine();
            int n = 0;
            for (PCParts part : shopInventory)
                if (part.toString().toLowerCase().contains(input.toLowerCase()))
                    System.out.printf("%3d.: %s\n", ++n, part);

            System.out.printf("%3d Übereinstimmungen gefunden.\n", n);
            if (anotherInput("Möchten sie noch weiter Suchen")) return;

        }

    }

    protected static void deleteProduct() {
        showProducts();
        System.out.println("Zu löschendes produkt auswählen:");
        int input = intInput();
        if (input == 0 || input > shopInventory.size()) {
            System.out.println("Fehlerhafte Eingabe");
            return;
        }
        input--;
        if (!anotherInput(String.format("Soll\n %s\n wirklich gelöscht werden?", shopInventory.get(input)))) {
            shopInventory.remove(input);
        }

    }

    protected static void quitShop(){
        if (!anotherInput("Soll das Programm wirklich beendet werden?")){
            System.out.println("„PC Shop wurde beendet");
            System.exit(0);
        }
        System.out.println("Fehlerhafte Eingabe");

    }
    private static void showProducts() {
        IntStream
                .range(0, shopInventory.size())
                .forEach(i -> System.out.printf("%3d Typ: %10s\t%s\n", i + 1, shopInventory.get(i).getClass().getName(), shopInventory.get(i)));
    }
    protected static int intInput() {
        String input = sc.nextLine().trim();

        if (input.matches("[0-9]+")) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }

    }

    protected static TrippleTuple pcPartsInput() {

        System.out.println("Bitte Marke angeben:");
        String brand = sc.nextLine().trim();
        System.out.println("Bitte Model angeben:");
        String model = sc.nextLine().trim();
        System.out.println("Bitte Preis angeben:");
        float price = Float.parseFloat(sc.nextLine().replace(",", "."));
        return new TrippleTuple(brand, model, price);

    }

    protected static boolean anotherInput(String question) {
        System.out.printf("%s (ja / nein)\n", question);
        String input = sc.nextLine().trim().toLowerCase();
        return !input.matches("ja|j|yes|y");
    }

}