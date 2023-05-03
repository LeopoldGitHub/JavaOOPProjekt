public class Motherboard extends PCParts{
    protected String chipset;

    public Motherboard(String chipset,String brand,String model,float price){
        super(brand,model,price);
        this.chipset=chipset;
    }




    @Override
    public String toString() {
        return super.toString()+String.format("Chipset: %5s",chipset);
    }

}
