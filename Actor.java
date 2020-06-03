package project3;

/**
 * This class represents an Actor. 
 * It consists of one constructor.
 * Each Actor has a name.
 * 
 * @author Udit Patel
 * @version 10/19/2018
 */
public class Actor {
	private String name;

	/**
	 * Constructs a new Actor object with specified name
	 * 
	 * @param name name of Actor; should not be null or an empty String
	 * @throws IllegalArgumentException if name is invalid
	 */
	public Actor(String name) throws IllegalArgumentException {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Error: Expects a name for the Actor");
		}
		
		this.name = name;
	}

	/**
	 * Returns the name of this Actor object
	 * @return the name of this Actor object
	 */
	public String getName() {
		return name;
	}
}
