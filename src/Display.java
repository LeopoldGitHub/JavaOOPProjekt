/**
 * the Subclass for saving a Display
 */
public class Display extends PCParts{
    protected int size;
    
    /**
     * constructor
     * @param size int Size of the display
     * @param brand string brand of the display
     * @param model string model of the display
     * @param price float price of the display
     */
    public Display(int size,String brand,String model,float price){
        super(brand,model,price);
        this.size=size;
    }
    
    /**
     * overrides the toString method by calling its Superclass method and appending the size
     * @return a string of the object variables a with a description
     */
    @Override
    public String toString() {
        return super.toString()+String.format("Größe : %3d",size);
    }
}
