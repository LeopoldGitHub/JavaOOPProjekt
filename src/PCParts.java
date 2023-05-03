public abstract class PCParts {
    protected String brand,model;
    protected float price;

    PCParts(String brand,String model,float Price){
        this.brand=brand;
        this.model=model;
        this.price=Price;
    }


    @Override
    public String toString() {
        return String.format("Brand: %15s, Model: %10s, Price: %8.02f ",brand,model,price);

    }
}
