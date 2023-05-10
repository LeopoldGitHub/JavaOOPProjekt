/**
 * the Subclass for saving a Mice
 */
public class Mice extends PCParts{
    protected int dpi;
    /**
     * constructor
     * @param dpi int dpi of the mice
     * @param brand string brand of the mice
     * @param model string model of the mice
     * @param price float price the mice
     */
    public Mice(int dpi,String brand,String model,float price){
        super(brand,model,price);
        this.dpi =dpi;
    }
    /**
     * overrides the toString method by calling its Superclass method and appending the dpi
     * @return a string of the object variables a with a description
     */
    @Override
    public String toString() {
        return super.toString()+String.format("DPI: %5d",dpi);
    }
}
