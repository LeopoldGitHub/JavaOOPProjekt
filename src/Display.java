public class Display extends PCParts{
    protected int size;

    public Display(int size,String brand,String model,float price){
        super(brand,model,price);
        this.size=size;
    }
    @Override
    public String toString() {
        return super.toString()+String.format("Größe : %3d",size);
    }
}
