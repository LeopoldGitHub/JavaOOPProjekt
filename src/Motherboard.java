/**
 * the Subclass for saving a Motherboard
 */
public class Motherboard extends PCParts {
	protected String chipset;
	
	/**
	 * constructor
	 *
	 * @param chipset string chipset of the mice
	 * @param brand   string brand of the mice
	 * @param model   string model of the mice
	 * @param price   float price the mice
	 */
	public Motherboard(String chipset, String brand, String model, float price) {
		super(brand, model, price);
		this.chipset = chipset;
	}
	
	/**
	 * overrides the toString method by calling its Superclass method and appending the chipset
	 *
	 * @return a string of the object variables a with a description
	 */
	@Override
	public String toString() {
		return super.toString() + String.format("Chipset: %5s", chipset);
	}
	
}
