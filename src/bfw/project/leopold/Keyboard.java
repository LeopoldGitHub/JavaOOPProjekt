package bfw.project.leopold;

/**
 * the Subclass for saving a bfw.project.leopold.Keyboard
 */
class Keyboard extends PCParts{
    protected String layout;
    
    /**
     * constructor
     * @param layout string layout of the keyboard
     * @param brand string brand of the keyboard
     * @param model string model of the keyboard
     * @param price float price of the keyboard
     */
    public Keyboard(String layout,String brand,String model,float price){
        super(brand,model,price);
        this.layout=layout;
    }
    /**
     * overrides the toString method by calling its Superclass method and appending the layout
     * @return a string of the object variables a with a description
     */
    @Override
    public String toString() {
        return super.toString()+String.format("Layout:\t%s",layout);
    }
}
