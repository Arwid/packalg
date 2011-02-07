package packalg;
/**
 * PackAlg
 * By Arwid Bancewicz, October 19, 2009
 */

// Imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * PackAlg.java - Main class gathers user input and runs the packing algorithm.
 * 
 * @author 	Arwid Bancewicz
 * @version 1.1, (09/19/09)
 * @see 	PackingAlgorithm
 * @see 	Box
 */
public class PackAlg {
	
	/**
	 * Applies the packing algorithm to user input (no input validation).
	 * <p>
	 * The input is formatted as follows:
	 * <pre>
	 * (container width) (container length) (container height)
	 * (number of boxes n)
	 * [for i : 1..n]
	 * (box i width) (box i length) (box i height)
	 * </pre>
	 * An example:
	 * <pre>
	 * 10 12 4 
	 * 2 
	 * 3 4 5 
	 * 4 6 8
	 * </pre>
	 * See {@link PackingAlgorithm#statPack(Shape, List)} for output format.
	 * @see PackingAlgorithm
	 */
	public static void main(String[] args) throws IOException {
		// Set up input reader
		Scanner loInput = new Scanner(System.in);

		// Read container dimensions
		int liCntWidth = loInput.nextInt();
		int liCntLength = loInput.nextInt();
		int liCntHeight = loInput.nextInt();
		
		// Create container
		Shape loContainer = new Box(liCntWidth, liCntLength, liCntHeight);

		// Read box count
		int liBoxCount = loInput.nextInt();

		// Compile list of boxes from input
		List<Shape> loBoxes = new ArrayList<Shape>();
		while (liBoxCount-- > 0) {
			int liBoxWidth = loInput.nextInt();
			int liBoxLength = loInput.nextInt();
			int liBoxHeight = loInput.nextInt();
			loBoxes.add(new Box(liBoxWidth, liBoxLength, liBoxHeight));
		}

		// Apply packing algorithm to input
		PackingAlgorithm loAlg = new PackingAlgorithm();
		loAlg.statPack(loContainer, loBoxes);
	}
}
