public class Motherboard extends PCParts{
    protected String chipset;

    public Motherboard(String chipset,String brand,String model,int price){
        super(brand,model,price);
        this.chipset=chipset;
    }
}
