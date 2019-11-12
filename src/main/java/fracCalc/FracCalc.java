package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		String fractions = userInput.nextLine();
		while (!(fractions.toUpperCase()).equals("QUIT")) {
			System.out.println(produceAnswer(fractions));
			fractions = userInput.nextLine();
		}
		// TODO: Read the input from the user and call produceAnswer with an equation

	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		String operator;
		int numsBefore;
		int a = input.indexOf(" + ");
		int b = input.indexOf(" - ");
		int c = input.indexOf(" * ");
		int d = input.indexOf(" / ");
		if (a != -1) {
			numsBefore = a;
			operator = "+";
		} else if (b != -1) {
			numsBefore = b;
			operator = "-";
		} else if (c != -1) {
			numsBefore = c;
			operator = "*";
		} else {
			numsBefore = d;
			operator = "/";
		}
		String firstFraction = "";
		String secondFraction = "";
		for (int i = 0; i < numsBefore; i++) {
			firstFraction = firstFraction + input.charAt(i);
		}
		for (int i = 0; i < input.length() - 3 - numsBefore; i++) {
			secondFraction = secondFraction + input.charAt(3 + numsBefore + i);
		}
		return "Second fraction:" + secondFraction;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

}
