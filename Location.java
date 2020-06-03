package project3;

/**
 * This class represents a Location. It consists of one constructor. Each
 * Location has a name but a fun fact about the location is optional.
 * 
 * @author Udit Patel
 * @version 10/19/2018
 */
public class Location {
	private String location;
	private String funFacts;

	/**
	 * Constructs a new Location object with specified location and funFacts
	 * 
	 * @param location description of Location of Movie; should not be null or an
	 *                 empty String
	 * @param funFacts funFacts of Location of Movie
	 * @throws IllegalArgumentException if location is invalid
	 */
	public Location(String location, String funFacts) throws IllegalArgumentException {
		if (location == null || location.isEmpty()) {
			throw new IllegalArgumentException("Error: Expects a location");
		}

		if ((funFacts == null) || (funFacts.isEmpty())) {
			this.funFacts = null;
		} else {
			this.funFacts = funFacts;
		}

		this.location = location;
	}

	/**
	 * Returns the location of this Location object
	 * 
	 * @return the location of this Location object
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Returns the fun facts of this Location object
	 * 
	 * @return the fun facts of this Location object
	 */
	public String getFunFacts() {
		return funFacts;
	}
}
