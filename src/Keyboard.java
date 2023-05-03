public class Keyboard extends PCParts{
    protected String layout;

    public Keyboard(String layout,String brand,String model,float price){
        super(brand,model,price);
        this.layout=layout;
    }

    @Override
    public String toString() {
        return super.toString()+String.format("Layout: %10s",layout);
    }
}
