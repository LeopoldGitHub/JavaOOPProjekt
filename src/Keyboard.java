public class Keyboard extends PCParts{
    protected String layout;

    public Keyboard(String layout,String brand,String model,int price){
        super(brand,model,price);
        this.layout=layout;
    }
}
