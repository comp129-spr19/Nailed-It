package main;

import java.util.ArrayList;

/*
 * This class will be in charge of generating questions
 * and operations pertaining to questions.
 * 
 * It is a static class.
 * 
 */

public class Questions {
	public static ArrayList<QuestionScreenLayout> generate(boolean[] difficulty, MainStage main) {
		// should be sending the easy medium hard toggled bool array
		if (difficulty.length != 3) {
			return null;
		}
		ArrayList<QuestionScreenLayout> questions = new ArrayList<QuestionScreenLayout>();
		boolean easy = difficulty[0];
		boolean medium = difficulty[1];
		boolean hard = difficulty[2];

		if (easy) {
			questions.add(new QuestionScreenLayout("What is the time and space complexity of the quicksort algorithm?",
					"time: O(n^2); space: O(log(n))", "time: O(n^2); space: O(nlog(n))",
					"time: O(log(n)); space: O(n^2)", "time: O(nlog(n)); space: O(n)", main));

		}

		if (medium) {
			questions.add(new QuestionScreenLayout("What is the formula for the runtime of a recursive function",
					"O(depth^branches)", "O(log(n))", "O(branches^depth)", "O(branches*depth)", main));
		}

		if (hard) {
			questions.add(new QuestionScreenLayout(
					"What is the time complexity of the following code?\n" + "\n" + "		boolean isPrime(int n) {\n"
							+ "			if (n <= 0) return 0;\n" + "			else if (n == 1) return 1;\n"
							+ "			return fib(n - 1) + fib(n - 2);\n" + "		}",
					"O(log(n))", " O(n)", "O(2^n)", "O(n^2)", main));
		}

		return questions;
	}
}
