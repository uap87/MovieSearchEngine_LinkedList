package project3;

import java.util.ArrayList;

/**
 * This class represents a Movie. It consists of two constructors. Each Movie is
 * required(required to create a Movie Object) to have the following: - Title -
 * Year The following is optional for each Movie: - Director - Writer - Actors
 * 
 * @author Udit Patel
 * @version 10/19/2018
 */
public class Movie implements Comparable<Movie> {
	private String title;
	private int year;
	private String director;
	private String writer;
	private ArrayList<Actor> actorList = new ArrayList<Actor>();
	private Actor actor1;
	private Actor actor2;
	private Actor actor3;
	private ArrayList<Location> locations = new ArrayList<Location>();

	/**
	 * Constructs a new Movie object with specified title and year
	 * 
	 * @param title title of the Movie; cannot be null or an empty String
	 * @param year  year that the Movie was released; should be in the range of 1900
	 *              to 2020
	 * @throws IllegalArgumentException if title or year parameters are invalid
	 */
	public Movie(String title, int year) throws IllegalArgumentException {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Error: Expects a title for the movie");
		}

		if ((year < 1900) || (year > 2020)) {
			throw new IllegalArgumentException("Error: Expects a year between 1900 and 2020, inclusive");
		}

		this.title = title;
		this.year = year;
	}

	/**
	 * Constructs a new Movie object with specified title, year, director, writer,
	 * actor1, actor2, and actor3
	 * 
	 * @param title    title of the Movie; cannot be null or an empty string
	 * @param year     year that the Movie was released; should be in the range of
	 *                 1900 to 2020
	 * @param director director of the Movie
	 * @param writer   writer of the Movie
	 * @param actor1   first Actor in the Movie; should not be null
	 * @param actor2   second Actor in the Movie
	 * @param actor3   second Actor in the Movie
	 * @throws IllegalArgumentException if the title, year, or actor1 parameters are
	 *                                  invalid
	 */
	public Movie(String title, int year, String director, String writer, Actor actor1, Actor actor2, Actor actor3)
			throws IllegalArgumentException {
		this(title, year);

		if (actor1 == null) {
			throw new IllegalArgumentException("Error: Expects at least one Actor");
		}

		this.director = director;
		this.writer = writer;
		this.actor1 = actor1;
		this.actor2 = actor2;
		this.actor3 = actor3;
		actorList.add(actor1);
		if (actor2 != null) {
			actorList.add(actor2);
		}
		if (actor3 != null) {
			actorList.add(actor3);
		}
	}

	/**
	 * Returns the title of this Movie object
	 * 
	 * @return the title of this Movie object
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the year of this Movie object
	 * 
	 * @return the year of this Movie object
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns the director of this Movie object
	 * 
	 * @return the director of this Movie object
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Returns the writer of this Movie object
	 * 
	 * @return the writer of this Movie object
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * Returns the actor1 of this Movie object
	 * 
	 * @return the actor1 of this Movie object
	 */
	public Actor getActor1() {
		return actor1;
	}

	/**
	 * Returns the actor2 of this Movie object
	 * 
	 * @return the actor2 of this Movie object
	 */
	public Actor getActor2() {
		return actor2;
	}

	/**
	 * Returns the actor3 of this Movie object
	 * 
	 * @return the actor3 of this Movie object
	 */
	public Actor getActor3() {
		return actor3;
	}

	/**
	 * Returns the actorList of this Movie object
	 * 
	 * @return the actorList of this Movie object
	 */
	public ArrayList<Actor> getActorList() {
		return actorList;
	}

	/**
	 * Returns the locations of this Movie object
	 * 
	 * @return the locations of this Movie object
	 */
	public ArrayList<Location> getLocations() {
		return locations;
	}

	/**
	 * Searches through the current locations of this Movie object and if the
	 * location parameter is not in the list of locations, then it will be added to
	 * the locations of this Movie object
	 * 
	 * @param loc the location trying to be added to locations; should not be null
	 * @throws IllegalArgumentException if loc is invalid
	 */
	public void addLocation(Location loc) throws IllegalArgumentException {
		// if loc is null, throw IllegalArgumentException
		if (loc == null) {
			throw new IllegalArgumentException("Error: Expects a location");
		}

		// iterate through and test if loc is already in this Movie's locations
		boolean containsLocation = false;
		for (Location l : this.locations) {
			if (l.getLocation().equalsIgnoreCase(loc.getLocation())) {
				containsLocation = true;
			}
		}

		// if loc is not in locations, add it to this Movie's locations
		if (!containsLocation) {
			this.locations.add(loc);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Movie o) {
		if (this.getYear() == o.getYear()) {
			return this.getTitle().toUpperCase().compareTo(o.getTitle().toUpperCase());
		} else if (this.getYear() > o.getYear()) {
			return 1;
		} else {
			return -1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Movie other = (Movie) obj;
		if (!title.equals(other.title)) {
			return false;
		}
		if (year != other.year) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the string representation of this Movie.
	 * 
	 * @returns the string representation of this Movie object
	 */
	@Override
	public String toString() {
		// create StringBuilder
		StringBuilder formattedMovieStringBuilder = new StringBuilder();

		// begin appending
		formattedMovieStringBuilder.append(this.getTitle());
		formattedMovieStringBuilder.append(" (");
		formattedMovieStringBuilder.append(this.getYear());
		formattedMovieStringBuilder.append(") \n------------------------------------\n");
		formattedMovieStringBuilder.append(String.format("%-15s : %s%n", "director", this.getDirector()));
		formattedMovieStringBuilder.append(String.format("%-15s : %s%n", "writer", this.getWriter()));

		// append different lines depending on how many Actors are in this Movie
		if (this.getActorList().size() == 1) {
			formattedMovieStringBuilder.append(String.format("%-15s : %s%n", "starring", this.actor1.getName()));
		} else if (this.getActorList().size() == 2) {
			formattedMovieStringBuilder.append(
					String.format("%-15s : %s, %s%n", "starring", this.actor1.getName(), this.actor2.getName()));
		} else if (this.getActorList().size() == 3) {
			formattedMovieStringBuilder.append(String.format("%-15s : %s, %s, %s%n", "starring", this.actor1.getName(),
					this.actor2.getName(), this.actor3.getName()));
		}

		formattedMovieStringBuilder.append("filmed on location at: \n");

		// append different lines depending on if each loc in this Movie has a fun fact
		// or not
		for (Location loc : this.getLocations()) {
			if (loc.getFunFacts() != null && !loc.getFunFacts().isEmpty()) {
				formattedMovieStringBuilder
						.append(String.format("    %s (%s)%n", loc.getLocation(), loc.getFunFacts()));
			} else {
				formattedMovieStringBuilder.append(String.format("    %s%n", loc.getLocation()));
			}
		}

		// return the String formed by StringBuilder
		return formattedMovieStringBuilder.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
