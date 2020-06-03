package project3;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a MovieList. It consists of one constructor. MovieList
 * objects are initially empty.
 * 
 * @author Udit Patel
 * @version 10/19/2018
 */
public class MovieList extends LinkedList<Movie> {
	/**
	 * Constructs a new empty MovieList object
	 */
	public MovieList() {
		new LinkedList<Movie>();
	}

	/**
	 * Iterates through the movies list to determine if any of the titles contain
	 * the keyword. If a movie does contain the keyword, it will be added to a
	 * separate MovieList object. That MovieList object containing the Movies with
	 * matching titles will be sorted and returned.
	 * 
	 * @param keyword String inputed by user that is being used to search through
	 *                the this MovieList object; should not be null or empty
	 * @return the sorted MovieList that contains all the Movies with titles that
	 *         contain the keyword
	 */
	public MovieList getMatchingTitles(String keyword) {
		// if keyword is null or empty, return null
		if ((keyword == null) || (keyword.isEmpty())) {
			return null;
		}

		// creates new MovieList to store the matching Movies
		MovieList subMovies = new MovieList();

		// iterates through this MovieList object and adds the movies that have titles
		// that contain the keyword
		for (Movie movie : this) {
			if (movie.getTitle().toUpperCase().contains(keyword.toUpperCase())) {
				subMovies.add(movie);
			}
		}

		// if no Movie titles match the keyword, return null
		if (subMovies.size() == 0) {
			return null;
		}

		// sorts the MovieList that contains all the Movies with Actors that contain the
		// keyword
		subMovies.sort();

		// returns the sorted MovieList that contains all the Movies with titles that
		// contain the keyword
		return subMovies;
	}

	/**
	 * Iterates through the movies list to determine if any of the Actors contain
	 * the keyword. If a Movie Actor does contain the keyword, that Movie that the
	 * Actor is in will be added to a separate MovieList object. That MovieList
	 * object containing the Movies with matching Actors will be sorted and
	 * returned.
	 * 
	 * @param keyword String inputed by user that is being used to search through
	 *                the this MovieList object; should not be null or empty
	 * @return the sorted MovieList that contains all the Movies with Actors that
	 *         contain the keyword
	 */
	public MovieList getMatchingActor(String keyword) {
		// if keyword is null or empty, return null
		if ((keyword == null) || (keyword.isEmpty())) {
			return null;
		}

		// creates new MovieList to store the matching Movies
		MovieList subMovies = new MovieList();

		// iterates through this MovieList object and adds the movies that have Actors
		// that contain the keyword
		for (Movie movie : this) {
			if (movie.getActor1().getName().toUpperCase().contains(keyword.toUpperCase())) {
				subMovies.add(movie);
			} else if ((movie.getActor2() != null)
					&& (movie.getActor2().getName().toUpperCase().contains(keyword.toUpperCase()))) {
				subMovies.add(movie);
			} else if ((movie.getActor3() != null)
					&& (movie.getActor3().getName().toUpperCase().contains(keyword.toUpperCase()))) {
				subMovies.add(movie);
			}
		}

		// if no Movie Actors match the keyword, return null
		if (subMovies.size() == 0) {
			return null;
		}

		// sorts the MovieList that contains all the Movies with Actors that contain the
		// keyword
		subMovies.sort();

		// returns the sorted MovieList that contains all the Movies with Actors that
		// contain the keyword
		return subMovies;
	}

	/**
	 * Iterates through the Movies in this MovieList object and prints them using
	 * the formatted toString() method in Movie.java
	 */
	public void printMovies() {
		System.out.println();
		for (Movie movie : this) {
			System.out.println(movie);
		}
	}

	/**
	 * Returns the string representation of this Movie.
	 * 
	 * @returns the string representation of this Movie object
	 */
	@Override
	public String toString() {
		// create empty String
		String movieTitles = "";

		// add Movie titles in this MovieList object
		for (int i = 0; i < this.size(); i++) {
			if (i == this.size() - 1) {
				movieTitles = movieTitles + this.get(i).getTitle();
			} else {
				movieTitles = movieTitles + this.get(i).getTitle() + "; ";
			}
		}

		// returns full String of Movie titles
		return movieTitles;
	}
}