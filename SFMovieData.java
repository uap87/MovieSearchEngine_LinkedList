package project3;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class parses the input file and returns user queries. The program is
 * interactive. When the program is executed the name of the input file
 * containing the list of all the movies is provided as the program's single
 * command line argument. The data in this file serves as a database of all the
 * movies that are considered valid. In the interactive part, the user enters
 * "title" or "actor" followed by a keyword. The program responds by printing
 * the movies with their description that contain either the keyword in the
 * "title" or "actor" depending on the input (if at least one movie exists that
 * meets the conditions).
 * 
 * @author Udit Patel
 * @version 10/19/2018
 */
public class SFMovieData {

	private static final String ACTOR_PROGRAM_KEYWORD = "actor ";
	private static final String TITLE_PROGRAM_KEYWORD = "title ";
	private static final String QUIT_PROGRAM_KEYWORD = "quit";

	/**
	 * The main() method of this program.
	 * 
	 * @param args array of Strings provided on the command line when the program is
	 *             started; the first string should be the name of the input file
	 *             containing the list of movies.
	 */
	public static void main(String[] args) {
		// if no command line argument, print message in error stream and terminate
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as argument");
			System.exit(1);
		}

		// create file using the first command line argument
		File movieFile = new File(args[0]);

		// if file does not exist, print message in error stream and terminate
		if (!movieFile.exists()) {
			System.err.println("Error: the file " + movieFile.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}

		// if file cannot be read, print message in error stream and terminate
		if (!movieFile.canRead()) {
			System.err.println("Error: the file " + movieFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// create and instantiate a file Scanner
		Scanner inFile = null;

		// if file is not found, print message in error stream and terminate
		try {
			inFile = new Scanner(movieFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file " + movieFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// create and instantiate MovieList of movies in database, line that will be
		// read, and ArrayList of movieData for each line read
		MovieList movies = new MovieList();
		String line = null;
		ArrayList<String> movieData = null;

		// loop through the file while it still has another line
		while (inFile.hasNextLine()) {

			// try reading through lines, parsing them, and adding them to the Movie
			// database given they pass the conditions
			try {
				// assign the next line of the file to line
				line = inFile.nextLine();

				// call splitCSVLine method to parse the line
				movieData = splitCSVLine(line);

				// if movieData has less than 9 entries, movie is invalid, so skip line
				if (movieData.size() < 9) {
					continue;
				}

				// create and instantiate actor, containsMovie, and newMovie variables
				Actor actor1 = null;
				Actor actor2 = null;
				Actor actor3 = null;
				boolean containsMovie = false;
				Movie newMovie = null;

				// determine if movie already exists in movie database
				for (Movie movie : movies) {
					if (movie.getTitle().equalsIgnoreCase(movieData.get(0))) {
						containsMovie = true;
						newMovie = movie;
					}
				}

				// if movie does not exist in database, go through conditions in order to add
				// movie
				if (!containsMovie) {

					// create actor1 object if movieData length is greater than or equal to 9 and
					// actor1 value is not null and actor1 value not an empty String
					if (movieData.size() >= 9 && movieData.get(8) != null && (!(movieData.get(8).isEmpty()))) {
						actor1 = new Actor(movieData.get(8));
					}

					// create actor2 object if movieData length is greater than or equal to 10 and
					// actor2 value is not null and actor2 value not an empty String
					if (movieData.size() >= 10 && movieData.get(9) != null && (!(movieData.get(9).isEmpty()))) {
						actor2 = new Actor(movieData.get(9));
					}

					// create actor3 object if movieData length is greater than or equal to 11 and
					// actor3 value is not null and actor3 value not an empty String
					if (movieData.size() >= 11 && movieData.get(10) != null && (!(movieData.get(10).isEmpty()))) {
						actor3 = new Actor(movieData.get(10));
					}

					// if actor1 object is not null and location value is not null or not an empty
					// String, create Movie object using parameters, and add location to the movie
					if (actor1 != null) {
						if ((movieData.get(2) != null) && (!movieData.get(2).isEmpty())) {
							newMovie = new Movie(movieData.get(0), Integer.parseInt(movieData.get(1)), movieData.get(6),
									movieData.get(7), actor1, actor2, actor3);
							newMovie.addLocation(new Location(movieData.get(2), movieData.get(3)));
							movies.add(newMovie);
						}
					}
				}

				// if movie exists in database and if location value is not null and not an
				// empty String, add location to the existing Movie object in the database
				else {
					if ((movieData.get(2) != null) && (!movieData.get(2).isEmpty())) {
						newMovie.addLocation(new Location(movieData.get(2), movieData.get(3)));
					}
				}
			} catch (NoSuchElementException ex) {
				continue;
			} catch (IllegalArgumentException ex) {
				continue;
			}
		}

		// close file Scanner
		inFile.close();

		// Begin Interactive Mode
		System.out.println("Search the database by matching keywords to titles or actor names.\n"
				+ "  To search for matching titles, enter\n" + "\t title KEYWORKD\n"
				+ "  To search for matching actor names, enter\n" + "\t actor KEYWORD\n"
				+ "  To finish the program, enter\n" + "\t quit\n");

		// Create and instantiate variables and Scanner
		String response = null;
		boolean repeat = true;
		Scanner input = new Scanner(System.in);
		MovieList newMovieList = null;

		// loop through interactive mode until user enters "quit"
		while (repeat) {

			// prompts user for input
			System.out.println("Enter your search query:");
			System.out.println();
			response = input.nextLine();

			// if user enters "quit", then leave the while loop
			if (response.equalsIgnoreCase(QUIT_PROGRAM_KEYWORD)) {
				repeat = false;
			}

			// else if user enters "title KEYWORD", call getMatchingTitles method and print
			// matching Movies, if there are any
			else if (response.startsWith(TITLE_PROGRAM_KEYWORD)) {
				String searchValue = response.substring(6).trim();
				newMovieList = movies.getMatchingTitles(searchValue);
				if (newMovieList != null) {
					newMovieList.printMovies();
				} else {
					System.out.println();
					System.out.println("No matches found. Try again.");
					System.out.println();
				}
			}

			// else if user enters "actor KEYWORD", call getMatchingActor method and print
			// matching Movies, if there are any
			else if (response.startsWith(ACTOR_PROGRAM_KEYWORD)) {
				String searchValue = response.substring(6).trim();
				newMovieList = movies.getMatchingActor(searchValue);
				if (newMovieList != null) {
					newMovieList.printMovies();
				} else {
					System.out.println();
					System.out.println("No matches found. Try again.");
					System.out.println();
				}
			}

			// else the query is not valid
			else {
				System.out.println();
				System.out.println("This is not a valid query. Try again.");
				System.out.println();
			}
		}

		// close input Scanner
		input.close();
	}

	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may
	 * contain commas)
	 * 
	 * @author Joanna Klukowska
	 * @param textLine a line of text to be passed
	 * @return an Arraylist object containing all individual entries found on that
	 *         line
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {

		if (textLine == null)
			return null;

		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;

		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);

			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {

				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				} else {
					insideQuotes = true;
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if (insideQuotes || insideEntry) {
					// add it to the current entry
					nextWord.append(nextChar);
				} else { // skip all spaces between entries
					continue;
				}
			} else if (nextChar == ',') {
				if (insideQuotes) { // comma inside an entry
					nextWord.append(nextChar);
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}

		}
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list
		if (!nextWord.toString().equals(""))

		{
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}

}
