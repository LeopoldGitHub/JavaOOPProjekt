public class Mice extends PCParts{
    protected int dpi;
    public Mice(int dpi,String brand,String model,float price){
        super(brand,model,price);
        this.dpi =dpi;
    }
    @Override
    public String toString() {
        return super.toString()+String.format("DPI: %5d",dpi);
    }
}
