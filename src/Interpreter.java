import java.util.LinkedList;

public class Interpreter {

	// Linkedlists to store the code and variables for this program
	private LinkedList<String> lines = new LinkedList<String>();
	private LinkedList<Var> variables = new LinkedList<Var>();

	/**
	 * Interpreter constructor
	 * 
	 * @param lines the linkedlist of lines of code to interpret
	 */
	public Interpreter(LinkedList<String> lines) {

		this.lines = lines;

	}

	/**
	 * Goes through each line and executes it
	 * 
	 * @param lineNum current line to execute
	 * @param end     the last line in this block of code
	 */
	public void read(int lineNum, int end) {

		while (lineNum < end) {

			// If this line has a while then go to that method
			if (lines.get(lineNum).startsWith("while")) {

				lineNum = whileLoop(lineNum, end);

			} else {

				// If not do the simple command on that line then go to the next line
				execute(lines.get(lineNum));
				lineNum++;

			}

		}

	}

	/**
	 * Finds the beginning and end of the while and
	 * 
	 * @param lineNum current line to execute
	 * @param end     the last line in this block of code
	 * @return int lineNum
	 */
	private int whileLoop(int lineNum, int end) {

		int endIndex = lineNum;
		int depth = 0;

		// Finds the end of the while
		for (int i = lineNum + 1; i < end; i++) {

			if (lines.get(i).startsWith("end")) {

				// If there have been equal whiles started and ended then this is the index of
				// the end of the outer while
				if (depth == 0) {

					endIndex = i;

					// The end decreases the depth
				} else {

					depth--;

				}

				// If another while is started then the depth increases
			} else if (lines.get(i).startsWith("while")) {

				depth++;

			}

		}

		// Finds the var that is being checked in the while
		String varName = lines.get(lineNum).substring(lines.get(lineNum).indexOf("while") + 6,
				lines.get(lineNum).indexOf("not") - 1);
		int varIndex = getVarIndex(varName);

		int stopAt = Integer.parseInt(lines.get(lineNum).substring(lines.get(lineNum).indexOf("not") + 4,
				lines.get(lineNum).length() - 4));
		
		lineNum++;
		
		// Does the loop while the var found is not at the specified number
		while (variables.get(varIndex).getValue() != stopAt) {

			// Executes the code between the while and end
			read(lineNum, endIndex);

		}

		// Sets the current line number to be after the end statement
		lineNum = endIndex + 1;

		return lineNum;

	}

	/**
	 * Finds the command and variable and does one of the three basic functions on
	 * it
	 * 
	 * @param line the string of code to execute
	 */
	private void execute(String line) {

		// The variable name is always after the first space and before the semicolon
		String varName = line.substring(line.indexOf(" ") + 1, line.indexOf(";"));
		int index = getVarIndex(varName);

		if (line.startsWith("clear")) {

			variables.get(index).clear();

		} else if (line.startsWith("incr")) {

			variables.get(index).inc();

		} else if (line.startsWith("decr")) {

			variables.get(index).dec();

		}

	}

	/**
	 * Finds the index in the variables linkedlist of the variable with the given
	 * name, if there is no such variable a new one is created and added to the end
	 * of the linkedlist
	 * 
	 * @param varName the name of the variable to find
	 * @return int index of the variable
	 */
	private int getVarIndex(String varName) {

		boolean found = false;
		int index = 0;

		for (int i = 0; i < variables.size(); i++) {

			// Checks each Var name member against the varName parameter and if equal then
			// sets the index to that position in the linkedlist
			if (variables.get(i).getName().equals(varName)) {

				index = i;
				found = true;

			}

		}

		// If no matching var was found then a new one is created and the index is set
		// to the last position in the linkedlist
		if (!found) {

			variables.add(new Var(varName));
			index = variables.size() - 1;

		}

		return index;

	}

	/**
	 * Prints out each variable name and its value
	 */
	public void showVars() {

		System.out.println("Variables:");

		for (int i = 0; i < variables.size(); i++) {

			System.out.println(variables.get(i).toString());

		}

	}

}
