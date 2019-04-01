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
	public static ArrayList<QuestionScreenLayout> generate(boolean[] difficulty,MainStage main){
		// should be sending the easy medium hard toggled bool array
		if (difficulty.length != 3) {
			return null;
		}
		ArrayList<QuestionScreenLayout> questions = new ArrayList<QuestionScreenLayout>();
		boolean easy = difficulty[0];
		boolean medium = difficulty[1];
		boolean hard = difficulty[2];
		
		if (easy) {
			questions.add(new QuestionScreenLayout(
					"EASY QUESTION","A","B","C","D",main));
					
		}
		
		if (medium) {
			questions.add(new QuestionScreenLayout(
					"MEDIUM QUESTION","A","B","C","D",main));
		}
		
		if (hard) {
			questions.add(new QuestionScreenLayout(
					"HARD QUESTION","A","B","C","D",main));
		}
		
		return questions;
	}
}
