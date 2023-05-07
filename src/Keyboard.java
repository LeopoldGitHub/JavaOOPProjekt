/**
 * Klasse zur Speicherung von Tastaturen im Programm
 */
public class Keyboard extends PCParts {
    /**
     * Layout der Tastatur als String (ISO DE,...)
     */
    protected String layout;

    /**
     * Constructor
     *
     * @param layout Layout der Tastatur
     * @param brand  Hersteller
     * @param model  Modell der Tastatur
     * @param price  preis der Tastatur
     */
    public Keyboard(String layout, String brand, String model, float price) {
        super(brand, model, price);
        this.layout = layout;
    }

    /**
     * Als string formatierte rückgabe des Objektes zur ausgabe
     *
     * @return "Brand: _, Model: _, Price: _, Layout: _"
     */
    @Override
    public String toString() {
        return super.toString() + String.format("Layout: %10s", layout);
    }
}
