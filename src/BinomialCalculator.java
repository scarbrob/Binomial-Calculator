import java.util.*;

/**
 * A class that calculates the binomial coefficient of two numbers.
 * @author Ben Scarbrough
 * @version 1.0
 *
 */
class BinomialCalculator {

	private static ArrayList<long[]> array;

	private BinomialCalculator() {
	}

	// Static Block to instantiate the array;
	static {
		array = new ArrayList<>();
	}

	/**
	 * The Method that calculates the BinomialCoefficient. It calculates the number of ways to choose numChosen out of numItems.
	 * @param numItems The number of total objects.
	 * @param numChosen The number of ways to choose numChosen out of NumItems.
	 * @return The binomial coefficient.
	 */
	public static long calcBinomialCoefficient(int numItems, int numChosen) {
		if (numItems < 0 || numChosen < 0)
			return 0;
		if (numChosen > numItems)
			return 0;
		if (numItems == 0)
			return 1;
		if (numChosen == 1)
			return numItems;
		if (numChosen == 0)
			return 1;

		if (numItems > array.size()) {
			for (int i = array.size(); i <= numItems; i++) {
				long[] newRow = new long[i + 1];
				newRow[0] = newRow[newRow.length - 1] = 1;

				for (int j = 1; j <= (newRow.length - 1) / 2; j++) {
					long[] lastRow = array.get(i - 1);
					long tempNum1 = lastRow[j];
					long tempNum2 = lastRow[j - 1];
					newRow[j] = newRow[newRow.length - j - 1] = tempNum1 + tempNum2;
				}
				array.add(newRow);
			}
		}
		long[] tempArray = array.get(numItems);
		return tempArray[numChosen];
	}
	
	//Main Method
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Testing the binomial coefficient calculator.");
		while(true) {
			try {
				System.out.println("Enter two numbers, or hit enter to quit: ");
				String nextLine = scan.nextLine();
				if(nextLine.length() == 0) {
					System.out.println("Goodbye!");
					break;
				}
				
				String[] tokens = nextLine.split(" +");
				if(tokens.length != 2) throw new InputMismatchException();
				
				int numItems = Integer.parseInt(tokens[0]);
				int numChosen = Integer.parseInt(tokens[1]);
				
				System.out.println("The binomian coefficient is " + calcBinomialCoefficient(numItems, numChosen));
				
			}
			catch(InputMismatchException wrongInput) {
				System.out.println("That is not a valid input. Enter two numbers, or hit enter to quit: ");
				scan.next();
				continue;
			}
		}
	}
}
