package main;
import java.util.ArrayList;

import Layouts.QuizScreenLayout;

/*
 * This class will be in charge of generating questions
 * and operations pertaining to questions.
 * 
 * It is a static class.
 * 
 */

public class Questions {
	public static ArrayList<QuizScreenLayout> generate(boolean[] difficulty, MainStage main) {
		// should be sending the easy medium hard toggled bool array
		if (difficulty.length != 3) {
			return null;
		}
		ArrayList<QuizScreenLayout> questions = new ArrayList<QuizScreenLayout>();
		boolean easy = difficulty[0];
		boolean medium = difficulty[1];
		boolean hard = difficulty[2];

		if (easy) {
			questions.add(new QuizScreenLayout("What is the time and space complexity of the quicksort algorithm?",
					"time: O(n^2); space: O(log(n))", "time: O(n^2); space: O(nlog(n))",
					"time: O(log(n)); space: O(n^2)", "time: O(nlog(n)); space: O(n)", Answer.ANSWER_A, main));
			// solution: a) time: O(n^2); space: O(log(n))

		}

		if (medium) {
			questions.add(new QuizScreenLayout("What is the formula for the runtime of a recursive function",
					"O(depth^branches)", "O(log(n))", "O(branches^depth)", "O(branches*depth)", Answer.ANSWER_C,main));
			// solution: c) O(branches^depth)
		}

		if (hard) {
			questions.add(new QuizScreenLayout(
					"What is the time complexity of the following code?\n" + "\n" + "		boolean isPrime(int n) {\n"
							+ "			if (n <= 0) return 0;\n" + "			else if (n == 1) return 1;\n"
							+ "			return fib(n - 1) + fib(n - 2);\n" + "		}",
					"O(log(n))", " O(n)", "O(2^n)", "O(n^2)",Answer.ANSWER_C ,main));
			// solution: c) O(2^n)
		}

		return questions;
	}
}
