/**
 * Parent Class
 */
public abstract class PCParts {

    /**
     * Die Eigenschaften Marke und Model als String
     */
    protected String brand,model;

    /**
     * Der Preis as int
     */
    protected float price;

    /**
     * Der Super Constructor
     */
    PCParts(String brand,String model,float Price){
        this.brand=brand;
        this.model=model;
        this.price=Price;
    }

    @Override
    public String toString() {
        return String.format("Brand: %15s, Model: %10s, Price: %8.02f, ",brand,model,price);

    }

}
