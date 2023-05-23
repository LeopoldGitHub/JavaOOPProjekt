package bfw.project.leopold;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * a small programm to manage an inventory of a PCShop
 */
public class Main {
	static Scanner sc = new Scanner(System.in);
	/**
	 * ArrayList to save all the shop inventory.
	 */
	static ArrayList<PCParts> shopInventory = new ArrayList<>();
	
	public static void main(String[] args) {
		createTestData();
		while (true) {
			mainMenu();
		}
	}
	
	/**
	 * inputs some testdata into the ArrayList to enable easier testing.
	 */
	protected static void createTestData() {
		shopInventory.add(new Mice(2000, "Logitech", "G5", 90));
		shopInventory.add(new Mice(1000, "Logitech", "G3", 40));
		shopInventory.add(new Mice(20000, "Logitech", "PRO", 120));
		shopInventory.add(new Display(29, "Asus", "PB29AB", 400));
		shopInventory.add(new Display(29, "GIGABYTE", "GP29EH", 500));
		shopInventory.add(new Motherboard("Z99", "Asus", "RampageV", 349.99f));
		shopInventory.add(new Keyboard("ISO", "Logitech", "G15", 99.99f));
		
	}
	
	/**
	 * Main menu of the programm
	 */
	protected static void mainMenu() {
		try {
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
					Bitte wählen:""");
			switch (intInput()) {
				case 0 -> quitShop();
				case 1 -> createProduct();
				case 2 -> changeProduct();
				case 3 -> searchProduct();
				case 4 -> deleteProduct();
				default -> throw new InputMismatchException();
			}
		} catch (EmptyFieldException e) {
			System.out.println(e.getMessage() + " konnte aufgrund leerer Eingabewerte nicht gespeichert werden");
		} catch (InputMismatchException | NumberFormatException e) {
			System.out.println("Fehlerhafte Eingabe");
		}
	}
	
	/**
	 * methode to create a new object
	 * will repeat until an exception is thrown or the user says no when asked
	 * @throws EmptyFieldException Exception for the case of an empty field in an object
	 */
	protected static void createProduct() throws EmptyFieldException {
		while (true) {
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
					Bitte wählen:""");
			switch (intInput()) {
				case 0 -> {
					return;
				}
				case 1 -> shopInventory.add(createMotherboard());
				case 2 -> shopInventory.add(createDisplay());
				case 3 -> shopInventory.add(createKeyboard());
				case 4 -> shopInventory.add(createMice());
				default -> throw new InputMismatchException();
			}
			if (anotherInput("Möchten sie noch ein Produkt anlegen?")) {
				break;
			}
		}
	}
	
	/**
	 * methode to create a new motherboard object
	 * @return a new Motherboard object
	 * @throws EmptyFieldException in case of an empty field
	 */
	protected static Motherboard createMotherboard() throws EmptyFieldException {
		CustomTuple temp = pcPartsInput();
		System.out.println("Bitte Chipset angeben:");
		String chipset = sc.nextLine().trim();
		if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || chipset.isEmpty()) {
			throw new EmptyFieldException("Motherboard");
		}
		return new Motherboard(chipset, temp.brand, temp.model, temp.price);
	}
	
	/**
	 * methode to create a new display object
	 * @return a new Display object
	 * @throws EmptyFieldException in case of an empty field
	 */
	protected static Display createDisplay() throws EmptyFieldException {
		CustomTuple temp = pcPartsInput();
		System.out.println("Bitte Display Größe in Zoll angeben:");
		int size = intInput();
		if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || size == 0) {
			throw new EmptyFieldException("Monitor");
		}
		return new Display(size, temp.brand, temp.model, temp.price);
	}
	
	/**
	 * methode to create a new keyboard object
	 * @return a new Keyboard object
	 * @throws EmptyFieldException in case of an empty field
	 */
	protected static Keyboard createKeyboard() throws EmptyFieldException {
		CustomTuple temp = pcPartsInput();
		System.out.println("Bitte Keyboard Layout angeben:");
		String layout = sc.nextLine().trim();
		if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || layout.isEmpty()) {
			throw new EmptyFieldException("Tastatur");
		}
		return new Keyboard(layout, temp.brand, temp.model, temp.price);
	}
	
	/**
	 * methode to create a new mice object
	 * @return a new Mice object
	 * @throws EmptyFieldException in case of an empty field
	 */
	protected static Mice createMice() throws EmptyFieldException {
		CustomTuple temp = pcPartsInput();
		System.out.println("Bitte DPI vom Sensor Angeben:");
		int dpi = intInput();
		if (temp.model.isEmpty() || temp.brand.isEmpty() || temp.price == 0 || dpi == 0) {
			throw new EmptyFieldException("Maus");
		}
		return new Mice(dpi, temp.brand, temp.model, temp.price);
	}
	
	/**
	 * used to replace an object with another object of the same type with newly inserted value,s
	 * @throws EmptyFieldException propagates the EmptyFieldException from the createMethods
	 */
	protected static void changeProduct() throws EmptyFieldException {
		if (shopInventory.size() == 0) {
			System.out.println("Keine Produkte vorhanden");
			return;
		}
		while (true) {
			showProducts();
			System.out.println("Bitte Nummer des Zu bearbeitenden Objektes eingeben:");
			int input = intInput();
			if (input < 1 || input > shopInventory.size()) throw new InputMismatchException();
			switch (shopInventory.get(--input).getClass().getName()) {
				case "bfw.project.leopold.Display" -> shopInventory.set(input, createDisplay());
				case "bfw.project.leopold.Keyboard" -> shopInventory.set(input, createKeyboard());
				case "bfw.project.leopold.Mice" -> shopInventory.set(input, createMice());
				case "bfw.project.leopold.Motherboard" -> shopInventory.set(input, createMotherboard());
			}
			if (anotherInput("Möchten sie noch ein Produkt bearbeiten?")) return;
		}
	}
	
	/**
	 * used to search all the products for a match to the provided input
	 */
	protected static void searchProduct() {
		while (true) {
			System.out.println("Wonach möchten Sie suchen?");
			String input = sc.nextLine();
			int n = 0;
			for (PCParts part : shopInventory)
				if (part.toString().contains(input)||part.getClass().getName().contains(input))
					System.out.printf("%3d.: %s\n", ++n, part);
			System.out.printf("%3d Übereinstimmungen gefunden.\n", n);
			if (anotherInput("Möchten sie noch weiter Suchen")) return;
			
		}
		
	}
	
	/**
	 * to delete an object from the ArrayList
	 */
	protected static void deleteProduct() {
		showProducts();
		System.out.println("Zu löschendes produkt auswählen:");
		int input = intInput();
		if (input < 1 || input > shopInventory.size()) throw new InputMismatchException();
		if (!anotherInput(String.format("Soll\n %s\n wirklich gelöscht werden?", shopInventory.get(--input)))) {
			shopInventory.remove(input);
		}
		
	}
	
	/**
	 * quits the shop after asking for a conformation
	 */
	protected static void quitShop() {
		if (!anotherInput("Soll das Programm wirklich beendet werden?")) {
			System.out.println("„PC Shop wurde beendet");
			System.exit(0);
		}
	}
	
	/**
	 * loops through the shop inventory and prints its content in the console
	 */
	private static void showProducts() {
		IntStream
				.range(0, shopInventory.size())
				.forEach(i -> System.out.printf("%3d Type: %12s, %s\n", i + 1, shopInventory.get(i).getClass().getName().replace("bfw.project.leopold.", ""), shopInventory.get(i)));
	}
	
	/**
	 *
	 * @return input as integer
	 */
	protected static int intInput() {
		return Integer.parseInt(sc.nextLine().trim());
	}
	
	/**
	 * gets the inputs that are shared between all Products.
	 * @return the 3 variables that all subclasses need to be created. (String brand, String model, Float price)
	 */
	protected static CustomTuple pcPartsInput() {
		System.out.println("Bitte Marke angeben:");
		String brand = sc.nextLine().trim();
		System.out.println("Bitte Model angeben:");
		String model = sc.nextLine().trim();
		System.out.println("Bitte Preis angeben:");
		float price = Float.parseFloat(sc.nextLine().replace(",", "."));
		return new CustomTuple(brand, model, price);
		
	}
	
	/**
	 *
	 * @param question what should be asked.
	 * @return true if the answer was no, false if yes
	 */
	protected static boolean anotherInput(String question) {
		System.out.printf("%s (ja / nein)\n", question);
		String input = sc.nextLine().trim().toLowerCase();
		if (!input.matches("ja|j|yes|y|no|n|nein")) throw new InputMismatchException();
		return !input.matches("ja|j|yes|y");
	}
	
}