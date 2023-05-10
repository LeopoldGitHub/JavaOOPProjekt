package bfw.project.leopold;

/**
 * a custom Class to simulate a Tuple to enable returning of a set of mixed Types while keeping the Class bfw.project.leopold.PCParts abstract
 */
class CustomTuple {
	protected String brand, model;
	protected float price;
	
	/**
	 *
	 * @param brand string should contain the brand of a part
	 * @param model string should contain the model of a part
	 * @param price float how much its cost
	 */
	
	public CustomTuple(String brand, String model, float price) {
		this.brand = brand;
		this.model = model;
		this.price = price;
	}
}
