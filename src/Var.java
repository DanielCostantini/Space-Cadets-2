
public class Var {

	private String name;
	private int value = 0;

	/**
	 * Initialises the Var by setting its name
	 * 
	 * @param name
	 */
	public Var(String name) {

		this.name = name;

	}

	/**
	 * Sets the value to 0
	 */
	public void clear() {

		value = 0;

	}

	/**
	 * Increases the value by 1
	 */
	public void inc() {

		value++;

	}

	/**
	 * Decreases the value by 1
	 */
	public void dec() {

		value--;

	}

	/**
	 * Prints the Var name and current value
	 * 
	 * @return String
	 */
	public String toString() {

		return name + ": " + Integer.toString(value);

	}

	/**
	 * Returns the name
	 * 
	 * @return String name
	 */
	public String getName() {

		return name;

	}

	/**
	 * Returns the value
	 * 
	 * @return int value
	 */
	public int getValue() {

		return value;

	}

}
