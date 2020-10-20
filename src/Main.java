import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

	// A linkedlist to store the lines of code as Strings
	static LinkedList<String> lines = new LinkedList<String>();

	public static void main(String[] args) throws IOException {

		// Stores the code in the linkedlist
		lines = getText();

		// Creates an interpreter and starts reading the lines from line 0 to the end
		Interpreter interpreter = new Interpreter(lines);
		interpreter.read(0, lines.size());
		
		// Prints the variables and their values from the code
		interpreter.showVars();

	}

	/**
	 * Takes the txt file and reads it into a linkedlist of Strings where each line
	 * is an object in the list
	 * 
	 * @return The LinkedList of lines of code
	 * @throws IOException
	 */
	private static LinkedList<String> getText() throws IOException {

		LinkedList<String> linesList = new LinkedList<String>();

		// This is the relative file path to the text file wit the code
		File file = new File("./Data/BareBones.txt");

		// Creates a buffered reader to read the file
		BufferedReader br = new BufferedReader(new FileReader(file));

		do {

			// Adds each line from the text file as an element to the list
			linesList.add(br.readLine());

			// This continues until it has read nothing so the last element is null
		} while (linesList.getLast() != null);

		br.close();

		// Removes this null object from the end
		linesList.removeLast();

		// Trims the spaces before and after the text for each line
		for (int i = 0; i < linesList.size(); i++) {

			linesList.set(i, linesList.get(i).trim());

		}

		return linesList;

	}

}
