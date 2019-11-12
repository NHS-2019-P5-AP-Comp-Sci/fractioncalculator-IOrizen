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
		
		int firstWhole = Integer.parseInt(whole(firstFraction));
		int secondWhole = Integer.parseInt(whole(secondFraction));
		int firstNumerator = Integer.parseInt(numerator(firstFraction));
		int secondNumerator = Integer.parseInt(numerator(secondFraction));
		int firstDenominator = Integer.parseInt(denominator(firstFraction));
		int secondDenominator = Integer.parseInt(denominator(secondFraction));

		return "whole:" + secondWhole + " numerator:" +secondNumerator + " denominator:"+secondDenominator;
	}
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

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

}
