package JSON;

import org.json.*;
import main.*;


public abstract class JSONEditor {

	/*
	 * Dummy temporary method
	 * Will take in updates to a question and change JSON file accordingly
	 * Currently prints question to console for verification of question input & call
	 */
	public static boolean updateQuestion(Question question) {
		System.out.println("Question: " + question.getQuestion());
		System.out.println("Hint: " + question.getHint());
		System.out.println("A: " + question.getAnswerA());
		System.out.println("B: " + question.getAnswerB());
		System.out.println("C: " + question.getAnswerC());
		System.out.println("D: " + question.getAnswerD());
		System.out.println("Answer: " + question.getCorrectAnswer());
		
		return true;
	}

	public static boolean addQuestion() {
		return false;
	}
	
	public static boolean deleteQuestion() {
		return false;
	}
	
}
