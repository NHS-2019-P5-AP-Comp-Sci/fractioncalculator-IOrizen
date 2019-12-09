package fracCalc;

import java.util.*;

public class FracCalc {

	// Prompts user for fraction input
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		String fractions = userInput.nextLine();
		while (!(fractions.toUpperCase()).equals("QUIT")) {
			System.out.println(produceAnswer(fractions));
			fractions = userInput.nextLine();
		}
		userInput.close();

	}

	public static String produceAnswer(String input) {
		String operator;
		int numsBefore;
		// Finds what type of operation the user wants
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
		// Splits the expression into two fractions
		String firstFraction = "";
		String secondFraction = "";
		for (int i = 0; i < numsBefore; i++) {
			firstFraction = firstFraction + input.charAt(i);
		}
		for (int i = 0; i < input.length() - 3 - numsBefore; i++) {
			secondFraction = secondFraction + input.charAt(3 + numsBefore + i);
		}
// Finds the parts of both fractions (whole, numerator, denominator)
		int firstWhole = Integer.parseInt(whole(firstFraction));
		int secondWhole = Integer.parseInt(whole(secondFraction));
		int firstNumerator = Integer.parseInt(numerator(firstFraction));
		int secondNumerator = Integer.parseInt(numerator(secondFraction));
		int firstDenominator = Integer.parseInt(denominator(firstFraction));
		int secondDenominator = Integer.parseInt(denominator(secondFraction));

// Sets up fractions for the calculation
		if (firstFraction.indexOf("-") != -1 && firstFraction.indexOf("_") != -1) {
			firstNumerator = (firstWhole * firstDenominator - firstNumerator);
			if (operator.equals("+") || operator.equals("-")) {
				firstNumerator *= secondDenominator;
			}
		} else if (operator.equals("+") || operator.equals("-")) {
			firstNumerator = (firstNumerator + firstWhole * firstDenominator) * secondDenominator;
		} else {
			firstNumerator = (firstNumerator + firstWhole * firstDenominator);
		}

		if (secondFraction.indexOf("-") != -1 && secondFraction.indexOf("_") != -1) {
			secondNumerator = (secondWhole * secondDenominator - secondNumerator);
			if (operator.equals("+") || operator.equals("-")) {
				secondNumerator *= firstDenominator;
			}
		} else if (operator.equals("+") || operator.equals("-")) {
			secondNumerator = (secondNumerator + secondWhole * secondDenominator) * firstDenominator;
		} else {
			secondNumerator = (secondNumerator + secondWhole * secondDenominator);
		}
		if (operator.equals("+") || operator.equals("-")) {
			firstDenominator *= secondDenominator;
			secondDenominator = firstDenominator;
		}

// Performs operations and gives out unsimplified improper fraction
		int answerNumerator = 0;
		int answerDenominator = 0;
		if (operator.equals("+")) {
			answerNumerator = firstNumerator + secondNumerator;
			answerDenominator = firstDenominator;
		} else if (operator.equals("-")) {
			answerNumerator = firstNumerator - secondNumerator;
			answerDenominator = firstDenominator;
		} else if (operator.equals("*")) {
			answerNumerator = firstNumerator * secondNumerator;
			answerDenominator = firstDenominator * secondDenominator;
		} else if (operator.equals("/")) {
			int tempNumerator = secondNumerator;
			secondNumerator = secondDenominator;
			secondDenominator = tempNumerator;
			answerNumerator = firstNumerator * secondNumerator;
			answerDenominator = firstDenominator * secondDenominator;
		}
		// Returns simplified answer
		return simplify(answerNumerator, answerDenominator);
	}

// Finds the whole number in a mixed or whole number
	public static String whole(String fraction) {
		String whole = "";
		if (fraction.indexOf("/") == -1) {
			whole = fraction;
		} else if (fraction.indexOf("_") != -1) {
			int numsBeforeUnderscore = fraction.indexOf("_");
			for (int i = 0; i < numsBeforeUnderscore; i++) {
				whole = whole + fraction.charAt(i);
			}
		} else if (fraction.indexOf("_") == -1) {
			whole = "0";
		}
		return whole;
	}

// Finds the numerator of a fraction
	public static String numerator(String fraction) {
		String numerator = "";
		if (fraction.indexOf("/") == -1) {
			numerator = "0";
		} else if (fraction.indexOf("_") != -1) {
			int numsBeforeSlash = fraction.indexOf("/");
			int numsBeforeUnderscore = fraction.indexOf("_");
			for (int i = 0; i < numsBeforeSlash - numsBeforeUnderscore - 1; i++) {
				numerator = numerator + fraction.charAt(numsBeforeUnderscore + 1 + i);
			}
		} else if (fraction.indexOf("_") == -1) {
			int numsBeforeSlash = fraction.indexOf("/");
			for (int i = 0; i < numsBeforeSlash; i++) {
				numerator = numerator + fraction.charAt(i);
			}
		}
		return numerator;
	}

// Finds the denominator of a fraction
	public static String denominator(String fraction) {
		String denominator = "";
		if (fraction.indexOf("/") == -1) {
			denominator = "1";
		} else if (fraction.indexOf("_") != -1) {
			int numsBeforeSlash = fraction.indexOf("/");
			for (int i = 0; i < fraction.length() - numsBeforeSlash - 1; i++) {
				denominator = denominator + fraction.charAt(numsBeforeSlash + 1 + i);
			}
		} else if (fraction.indexOf("_") == -1) {
			int numsBeforeSlash = fraction.indexOf("/");
			for (int i = 0; i < fraction.length() - numsBeforeSlash - 1; i++) {
				denominator = denominator + fraction.charAt(numsBeforeSlash + 1 + i);
			}
		}
		return denominator;
	}

// Returns a simplified fraction back to produceAnswer
	public static String simplify(int numerator, int denominator) {
		if (denominator < 0 && numerator > 0) {
			numerator = 0 - numerator;
			denominator = Math.abs(denominator);
		}
		if (numerator < 0 && denominator < 0) {
			numerator = Math.abs(numerator);
			denominator = Math.abs(denominator);
		}
		if (numerator == 0) {
			return "0";
		} else if (denominator == 1) {
			return "" + numerator;
		} else if (numerator % denominator == 0) {
			return "" + numerator / denominator;
		}
		int GCF = Math.abs(numerator + denominator);
		while (numerator % GCF != 0 || denominator % GCF != 0) {
			GCF--;
		}
		numerator /= GCF;
		denominator /= GCF;
		if (Math.abs(numerator) > denominator) {
			int whole = numerator / denominator;
			numerator = numerator % denominator;
			if (numerator < 0) {
				numerator = Math.abs(numerator);
				return whole + "_" + numerator + "/" + denominator;
			} else {
				return whole + "_" + numerator + "/" + denominator;
			}
		} else {
			return numerator + "/" + denominator;
		}

	}
}
