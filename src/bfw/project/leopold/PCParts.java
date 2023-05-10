package bfw.project.leopold;

/**
 * Superclass for our PCShop that defines the basic structure of our Objects
 */
abstract class PCParts {
	protected String brand, model;
	protected float price;
	
	/**
	 * the Constructor that the Subclasses will call with super()
	 *
	 * @param brand the brand as a string
	 * @param model the model as a string
	 * @param price the price as a string
	 */
	PCParts(String brand, String model, float price) {
		this.brand = brand;
		this.model = model;
		this.price = price;
	}
	
	/**
	 * @return a string of the object variables a with a description
	 */
	@Override
	public String toString() {
		return String.format("Brand: %15s, Model: %10s, Price: %8.02f, ", brand, model, price);
		
	}
}
