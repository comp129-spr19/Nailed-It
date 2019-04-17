package main;

import java.io.IOException;
import java.util.ArrayList;

import JSON.JSONOperations;
import Layouts.DiffScreenRowLayout;
import Layouts.QuizScreenLayout;
import Scrapper.Scrapper;

/*
 * This class will be in charge of generating questions
 * and operations pertaining to questions.
 * 
 * It is a static class.
 * 
 */

public class GenerateQuestionScreens {
	public static ArrayList<QuizScreenLayout> generate(ArrayList<DiffScreenRowLayout> rows, MainStage main) throws IOException {

		ArrayList<QuizScreenLayout> questionScreens = new ArrayList<QuizScreenLayout>();

		for (DiffScreenRowLayout row : rows) {
			if (row.checkIfWebRow()) {
				addWebQuestions(row,questionScreens,main);
			} else {
				
				addQuestions(row, questionScreens, main);
			}
		}

		return questionScreens;

	}

	// add web questions
	private static void addWebQuestions(DiffScreenRowLayout row, ArrayList<QuizScreenLayout> questionScreens,
			MainStage main) throws IOException {
		boolean[] set = row.getDifficultySet();
		
		if (set[0]) {
			ArrayList<Question> questions = Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-shortest-paths-gq/");
			ArrayList<QuizScreenLayout> graphScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(graphScreens);
		}
		
		if (set[1]) {
			ArrayList<Question> questions = Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-gq/");
			ArrayList<QuizScreenLayout> algScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(algScreens);
		}
		
		if (set[2]) {
			ArrayList<Question> questions = Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/searching-and-sorting-gq/");
			ArrayList<QuizScreenLayout> sortingScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(sortingScreens);
		}
		
	}

	private static void addQuestions(DiffScreenRowLayout row, ArrayList<QuizScreenLayout> questionScreens,
			MainStage main) {
		boolean[] set = row.getDifficultySet();
		// add easy questions
		if (set[0]) {
			ArrayList<Question> questions = JSONOperations.getQuestions(row.getCategory(), "easy");
			ArrayList<QuizScreenLayout> easyScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(easyScreens);
		}

		// add medium questions
		if (set[1]) {
			ArrayList<Question> questions = JSONOperations.getQuestions(row.getCategory(), "medium");
			ArrayList<QuizScreenLayout> medScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(medScreens);
		}
		// add hard questions
		if (set[2]) {
			ArrayList<Question> questions = JSONOperations.getQuestions(row.getCategory(), "hard");
			ArrayList<QuizScreenLayout> hardScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(hardScreens);
		}
	}

	private static ArrayList<QuizScreenLayout> convertQuestionToScreens(ArrayList<Question> questions, MainStage main) {
		ArrayList<QuizScreenLayout> screens = new ArrayList<QuizScreenLayout>();

		for (Question q : questions) {
			screens.add(new QuizScreenLayout(q, main));
		}
		return screens;
	}
}
