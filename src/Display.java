/**
 * Klasse zur Speicherung von Monitoren im Programm
 */
public class Display extends PCParts{
    /**
     * Größe der Monitore als integer (zoll der Diagonale)
     */
    protected int size;

    /**
     * Constructor von Display
     */
    public Display(int size,String brand,String model,float price){
        super(brand,model,price);
        this.size=size;
    }

    /**
     * Als string formatierte rückgabe des Objektes zur ausgabe
     * @return "Brand: _, Model: _, Price: _, Grõße: _"
     */
    @Override
    public String toString() {
        return super.toString()+String.format("Größe : %3d",size);
    }
}
