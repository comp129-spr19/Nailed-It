package JSON;


import java.io.*;
import java.util.*;
import org.json.*;
import main.*;


public abstract class JSONEditor {

	/*
	 * Takes in updates to a question along with question category and difficulty
	 * Changes JSON file accordingly
	 */
	public static boolean updateQuestion(String categoryStr, String difficultyStr, Question question) {
		//getting the needed JSON Objects
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		JSONObject category = file.getJSONObject(categoryStr);
		JSONObject difficulty = category.getJSONObject(difficultyStr);
		JSONObject editQ = difficulty.getJSONObject(question.getName());
		
		//altering the question object
		editQ.put("topic", question.getTopic());
		editQ.put("question", question.getQuestion());
		editQ.put("answer_a", question.getAnswerA());
		editQ.put("answer_b", question.getAnswerB());
		editQ.put("answer_c", question.getAnswerC());
		editQ.put("answer_d", question.getAnswerD());
		editQ.put("correct_answer", question.getCorrectAnswer().toString());
		editQ.put("hint", question.getHint());
		
		//placing the edited question into the full file text
		difficulty.put(question.getName(), editQ);
		category.put(difficultyStr, difficulty);
		file.put(categoryStr, category);
		
		try {
			//rewriting the full file text to the JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean addQuestion(String categoryStr, String difficultyStr, Question question) {
		//getting the needed JSON Objects
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		JSONObject category = file.getJSONObject(categoryStr);
		JSONObject difficulty = category.getJSONObject(difficultyStr);
		JSONObject newQ = new JSONObject();
		
		//finding out what new question's name is
		String name = "q" + (difficulty.length() + 1);
		
		//filling the question object
		newQ.put("topic", question.getTopic());
		newQ.put("question", question.getQuestion());
		newQ.put("answer_a", question.getAnswerA());
		newQ.put("answer_b", question.getAnswerB());
		newQ.put("answer_c", question.getAnswerC());
		newQ.put("answer_d", question.getAnswerD());
		newQ.put("correct_answer", question.getCorrectAnswer().toString());
		newQ.put("hint", question.getHint());
		
		//placing the new question into the full file text
		difficulty.put(name, newQ);
		category.put(difficultyStr, difficulty);
		file.put(categoryStr, category);
		
		try {
			//rewriting the full file text to the JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteQuestion(String categoryStr, String difficultyStr, Question question) {
		//getting the needed JSON Objects
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		JSONObject category = file.getJSONObject(categoryStr);
		JSONObject difficulty = category.getJSONObject(difficultyStr);
		
		//removing the question requested
		//difficulty.remove(question.getName());
		
		//placing the altered file text back into the file object
		category.put(difficultyStr, difficulty);
		file.put(categoryStr, category);
		
		try {
			//rewriting the new file text to the JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean reloadBackupFile() {
		JSONObject file = JSONOperations.createJSONObject(Constants.BACKUP_FILENAME);
		
		try {
			//reloading the full file text to the Project2 JSON file
			FileWriter file2 = new FileWriter(Constants.FILENAME);
			file2.write(file.toString());
			file2.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
